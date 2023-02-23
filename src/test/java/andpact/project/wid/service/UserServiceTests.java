package andpact.project.wid.service;

import andpact.project.wid.security.CustomUserDetailsService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest
@Log4j2
public class UserServiceTests {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Test
    public void readTest() {
        String mid = "TestUser1";
        UserDetails apiUserDTO = customUserDetailsService.loadUserByUsername(mid);
        log.info(apiUserDTO);
    }
}
