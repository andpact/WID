package andpact.project.wid.service;

import andpact.project.wid.domain.User;
import andpact.project.wid.domain.UserRole;
import andpact.project.wid.dto.UserDTO;
import andpact.project.wid.dto.UserSecurityDTO;
import andpact.project.wid.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void create(UserDTO userDTO) {
        log.info("----------User Service----------");
        String mid = userDTO.getUserID();
        if (userRepository.existsById(mid)) {
            log.info("UserID is existed");
            return;
        }
        User newUser = modelMapper.map(userDTO, User.class);
        newUser.changePw(passwordEncoder.encode(userDTO.getUserPW()));
        newUser.addRole(UserRole.USER);
        log.info("new User:" + newUser);
        log.info("new User Role:" + newUser.getRoleSet());
        userRepository.save(newUser);
    }
//    public void read() {
//
//    }
//    public void update() {
//
//    }
//    public void delete() {
//
//    }
}
