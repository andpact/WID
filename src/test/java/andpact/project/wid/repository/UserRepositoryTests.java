package andpact.project.wid.repository;

import andpact.project.wid.domain.User;
import andpact.project.wid.domain.UserRole;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertTest() {
        IntStream.rangeClosed(1, 5).forEach(i -> {
            User newUser = User.builder()
                    .userID("TestUser" + i)
                    .userPW(passwordEncoder.encode("1111"))
                    .build();
            newUser.addRole(UserRole.USER);
            if (3 < i) newUser.addRole(UserRole.ADMIN);
            userRepository.save(newUser);
        });
    }

    @Test
    public void selectTest() {
        Optional<User> result = userRepository.getWithRoles("TestUser5");
        User user = result.orElseThrow();
        log.info("User:" + user);
        log.info("UserRole:" + user.getRoleSet());
    }

//    @Test
//    public void updateTest() {
//        String mid = "TestUser1";
//        Optional<User> result = userRepository.findById(mid);
//        User user = result.orElseThrow();
//        user.changePw(passwordEncoder.encode("2222"));
//        userRepository.save(user);
//    }

//    @Test
//    public void deleteTest() {
//        String mid = "TestUser1";
//        userRepository.deleteById(mid);
//    }

}
