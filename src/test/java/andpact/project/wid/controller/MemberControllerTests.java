package andpact.project.wid.controller;

import andpact.project.wid.dto.MemberDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MemberControllerTests {
    @Autowired
    private MemberController memberController;

    @Test
    public void createTest() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            MemberDTO newMemberDTO = MemberDTO.builder()
                    .mID("TestMember" + i)
                    .mPW("1111")
                    .build();
            memberController.join(newMemberDTO);
        });
    }
}
