package andpact.project.wid.repository;

import andpact.project.wid.domain.Member;
import andpact.project.wid.domain.MemberRole;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertTest() {
        IntStream.rangeClosed(1, 5).forEach(i -> {
            Member newMember = Member.builder()
                    .mID("RepoTestMember" + i)
                    .mPW(passwordEncoder.encode("1111"))
                    .build();
            newMember.addRole(MemberRole.USER);
            if (3 < i) newMember.addRole(MemberRole.ADMIN);
            memberRepository.save(newMember);
        });
    }

    @Test
    public void selectTest() {
        Optional<Member> result = memberRepository.getWithRoles("TestMember5");
        Member member = result.orElseThrow();
        log.info("Member:" + member);
        log.info("MemberRole:" + member.getRoleSet());
    }

    @Test
    public void updateTest() {
        String mID = "TestMember1";
        Optional<Member> result = memberRepository.findById(mID);
        Member member = result.orElseThrow();
        member.changePw(passwordEncoder.encode("2222"));
        memberRepository.save(member);
    }

    @Test
    public void deleteTest() {
        String mID = "TestMember1";
        memberRepository.deleteById(mID);
    }

}
