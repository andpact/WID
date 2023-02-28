package andpact.project.wid.service;

import andpact.project.wid.domain.Member;
import andpact.project.wid.domain.MemberRole;
import andpact.project.wid.dto.MemberDTO;
import andpact.project.wid.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberService {

    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void create(MemberDTO memberDTO) {
        log.info("----------Member Service----------");
        String mID = memberDTO.getMID();
        if (memberRepository.existsById(mID)) {
            log.info("mID is existed");
            return;
        }
        Member newMember = modelMapper.map(memberDTO, Member.class);
        newMember.changePw(passwordEncoder.encode(memberDTO.getMPW()));
        newMember.addRole(MemberRole.USER);
        log.info("new Member:" + newMember);
        log.info("new Member Role:" + newMember.getRoleSet());
        memberRepository.save(newMember);
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
