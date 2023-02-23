package andpact.project.wid.security;

import andpact.project.wid.domain.User;
import andpact.project.wid.dto.UserSecurityDTO;
import andpact.project.wid.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userID) throws UsernameNotFoundException {
        log.info("----------Load User By User name:" + userID + "----------");
        Optional<User> result = userRepository.getWithRoles(userID);
        User user = result.orElseThrow(() -> new UsernameNotFoundException("Cannot find mid"));
        log.info("----------CustomUserDetailsService----------");
        UserSecurityDTO userSecurityDTO = new UserSecurityDTO(user.getUserID(), user.getUserPW(),
                user.getRoleSet().stream().map(userRole ->
                        new SimpleGrantedAuthority("ROLE_" + userRole.name())).collect(Collectors.toList()));
        log.info("UserSecurityDTO:" + userSecurityDTO);
        return userSecurityDTO;
    }
}
