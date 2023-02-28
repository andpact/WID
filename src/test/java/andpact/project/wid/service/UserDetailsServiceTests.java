package andpact.project.wid.service;

import andpact.project.wid.security.CustomUserDetailsService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest
@Log4j2
public class UserDetailsServiceTests {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Test
    public void readTest() {
        String mID = "TestMember1";
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(mID);
        log.info(userDetails);
    }
}
