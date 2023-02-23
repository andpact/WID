package andpact.project.wid.config;

import andpact.project.wid.security.CustomUserDetailsService;
import andpact.project.wid.security.filter.LoginFilter;
import andpact.project.wid.security.filter.RefreshTokenFilter;
import andpact.project.wid.security.filter.TokenCheckFilter;
import andpact.project.wid.security.handler.LoginSuccessHandler;
import andpact.project.wid.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Log4j2
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
public class CustomSecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;
    private final JWTUtil jwtUtil;

    @Bean // 필터를 모아놓는 체인
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        log.info("----------Filter Chain----------");

        // 인증 매니저 빌더
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        // 매니저가 사용할 서비스 객체, 비밀번호 인코더 등록
        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());

        // 인증 매니저 생성 및 설정
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
        http.authenticationManager(authenticationManager);

        // 로그인 필터 생성 및 필터 내부 매니저 설정
        LoginFilter loginFilter = new LoginFilter("/generateToken");
        loginFilter.setAuthenticationManager(authenticationManager);

        // 로그인 필터 순서 지정
        http.addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class);

        // 로그인 성공 핸들러 생성 및 필터 내부 핸들러로 설정
        LoginSuccessHandler loginSuccessHandler = new LoginSuccessHandler(jwtUtil);
        loginFilter.setAuthenticationSuccessHandler(loginSuccessHandler);

        // 토큰 확인 필터 순서 지정
        http.addFilterBefore(tokenCheckFilter(jwtUtil, customUserDetailsService), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new RefreshTokenFilter("/refreshToken", jwtUtil), TokenCheckFilter.class);

//        http.cors(httpSecurityCorsConfigurer -> {
//            httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource());
//        });

//        http.formLogin();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
//        log.info("----------web configure----------");

        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
//        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
//        configuration.setAllowCredentials(true);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

    private TokenCheckFilter tokenCheckFilter(JWTUtil jwtUtil, CustomUserDetailsService customUserDetailsService) {
        return new TokenCheckFilter(jwtUtil, customUserDetailsService);
    }



}
