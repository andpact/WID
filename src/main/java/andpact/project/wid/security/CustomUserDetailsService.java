package andpact.project.wid.security;

import andpact.project.wid.domain.Member;
import andpact.project.wid.dto.UserSecurityDTO;
import andpact.project.wid.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String mID) throws UsernameNotFoundException {
        log.info("----------Load Member By Member name:" + mID + "----------");
        Optional<Member> result = memberRepository.getWithRoles(mID);
        Member member = result.orElseThrow(() -> new UsernameNotFoundException("Cannot find mid"));
        log.info("----------CustomUserDetailsService----------");
        UserSecurityDTO userSecurityDTO = new UserSecurityDTO(member.getMID(), member.getMPW(),
                member.getRoleSet().stream().map(userRole ->
                        new SimpleGrantedAuthority("ROLE_" + userRole.name())).collect(Collectors.toList()));
        log.info("UserSecurityDTO:" + userSecurityDTO);
        return userSecurityDTO;
    }
}
