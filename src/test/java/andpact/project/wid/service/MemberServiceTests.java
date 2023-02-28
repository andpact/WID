package andpact.project.wid.service;

import andpact.project.wid.dto.MemberDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MemberServiceTests {

    @Autowired
    private MemberService memberService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void createTest() {
        IntStream.rangeClosed(1, 7).forEach(i -> {
            MemberDTO newMemberDTO = MemberDTO.builder()
                    .mID("TestMember" + i)
                    .mPW("1111")
                    .build();
            memberService.create(newMemberDTO);
        });
    }
}
