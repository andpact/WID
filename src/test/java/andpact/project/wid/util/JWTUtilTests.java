package andpact.project.wid.util;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
@Log4j2
public class JWTUtilTests {
    @Autowired
    private JWTUtil jwtUtil;

    @Test
    public void generateTokenTest() {
        log.info("----------Generate Token Test----------");
        Map<String, Object> claimMap = Map.of("mID", "ABCDE");
        String jwtStr = jwtUtil.generateToken(claimMap, 1);
        log.info("JwtStr:" + jwtStr);
    }

    @Test
    public void validateTokenTest() {
        log.info("----------Validate Token Test----------");

        // 유효 기간이 지난 Token
        String jwtStr = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NzYwMTc3MTMsIm1pZCI6IkFCQ0RFIiwiaWF0IjoxNjc2MDE3MTEzfQ.x6lVbTSCYDvQpejrqR5gD7KHV5h7gd_LaKkmdFNsnMs";

        Map<String, Object> claim = jwtUtil.validateToken(jwtStr);
        log.info("Claim:" + claim);
    }

    @Test
    public void generateValidateTokenTest() {
        log.info("----------Generate Validate Token Test----------");
        String jwtStr = jwtUtil.generateToken(Map.of("mID", "TokenTestUser", "email", "andpact@gmail.com"), 1);
        log.info(jwtStr);

        Map<String, Object> claim = jwtUtil.validateToken(jwtStr);
        log.info("MID: " + claim.get("mID"));
        log.info("Email: " + claim.get("email"));
    }
}


