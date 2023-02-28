package andpact.project.wid.controller;

import andpact.project.wid.dto.MemberDTO;
import andpact.project.wid.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(value = "/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

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
    public String join(@RequestBody MemberDTO memberDTO) {
        log.info("join:" + memberDTO);
        memberService.create(memberDTO);
        return "redirect:/login";
    }
}
