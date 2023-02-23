package andpact.project.wid.controller;

import andpact.project.wid.dto.UserDTO;
import andpact.project.wid.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping(value = "/member")
@Log4j2
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    @GetMapping(value = "/login")
//    public void login() {
//
//    }

//    @PostMapping(value = "/login")
//    public String loginPost() {
//        return "login success";
//    }

//    @PostMapping(value = "/logout")
//    public void logout() {
//
//    }

    @PostMapping(value = "/join", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String join(@RequestBody UserDTO userDTO) {
        log.info("join:" + userDTO);
        userService.create(userDTO);
        return "redirect:/login";
    }
}
