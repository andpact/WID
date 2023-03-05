package andpact.project.wid.controller;

import andpact.project.wid.dto.MemberDTO;
import andpact.project.wid.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @PostMapping(value = "/join", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String join(@RequestBody MemberDTO newMemberDTO) {
        // front에서 넘어온 정보가 DTO로 변환이 안된다.
        log.info("join:" + newMemberDTO);
        memberService.create(newMemberDTO);
        return "Join Successful";
    }
}
