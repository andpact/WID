package andpact.project.wid.security.filter;

import andpact.project.wid.security.CustomUserDetailsService;
import andpact.project.wid.security.exception.AccessTokenException;
import andpact.project.wid.util.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
public class TokenCheckFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();

        if (!path.startsWith("/quest/")) {
            filterChain.doFilter(request, response);
            return;
        }
        log.info("----------Token Check Filter----------");
        log.info("JWTUtil : " + jwtUtil);

        try {
            Map<String, Object> payload = validateAccessToken(request);
            String mID = (String)payload.get("mID");
            log.info("mID:" + mID);
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(mID);
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (AccessTokenException accessTokenException) {
            accessTokenException.sendResponseError(response);
        }

    }
    private Map<String, Object> validateAccessToken(HttpServletRequest request) throws AccessTokenException {
        log.info("----------ValidateAccessToken----------");
        String headerStr = request.getHeader("Authorization"); // Authorization을 지정 해주어야한다?

        if (headerStr == null  || headerStr.length() < 8) {
            log.info("----------AccessTokenException.UNACCEPT----------");
            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.UNACCEPT);
        }

        // Bearer 생략
        String tokenType = headerStr.substring(0, 6);

        if (!tokenType.equalsIgnoreCase("Bearer")) {
            log.info("----------AccessTokenException.BADTYPE----------");
            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.BADTYPE);
        }

        String tokenStr =  headerStr.substring(7);

        try{
            Map<String, Object> values = jwtUtil.validateToken(tokenStr);

            return values;
        } catch (MalformedJwtException malformedJwtException) {
            log.error("----------MalformedJwtException----------");
            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.MALFORM);
        } catch (SignatureException signatureException) {
            log.error("----------SignatureException----------");
            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.BADSIGN);
        } catch (ExpiredJwtException expiredJwtException) {
            log.error("----------ExpiredJwtException----------");
            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.EXPIRED);
        }
    }
}
