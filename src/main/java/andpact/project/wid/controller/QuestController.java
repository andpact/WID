package andpact.project.wid.controller;

import andpact.project.wid.dto.QuestDTO;
import andpact.project.wid.service.QuestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/quest")
@RequiredArgsConstructor
@Log4j2
public class QuestController {
    private final QuestService questService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long create(@RequestBody QuestDTO newQuestDTO) {
        log.info(newQuestDTO);
        Long qno = questService.create(newQuestDTO);
        return qno;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/{qno}", produces = MediaType.APPLICATION_JSON_VALUE)
    public QuestDTO read(@PathVariable(value = "qno") Long qno) {
        log.info("read qno:" + qno);
        QuestDTO questDTO = questService.read(qno);
        log.info(questDTO);
        return questDTO;
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<QuestDTO> readAllByDateAndMID(@PathVariable(value = "date")String strDate) {
        LocalDate date = LocalDate.parse(strDate);
//        for test
//        String mID = SecurityContextHolder.getContext().getAuthentication() == null ? "member1"
//                : SecurityContextHolder.getContext().getAuthentication().getName();
        String mID = SecurityContextHolder.getContext().getAuthentication().getName();
        List<QuestDTO> questDTOList = questService.readAllByDateAndMID(date, mID);
        return questDTOList;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<QuestDTO> readAllByMID() {
//        for test
//        String mID = SecurityContextHolder.getContext().getAuthentication() == null ? "member1"
//                : SecurityContextHolder.getContext().getAuthentication().getName();
        String mID = SecurityContextHolder.getContext().getAuthentication().getName();
        List<QuestDTO> questDTOList = questService.readAllByMID(mID);
        log.info(questDTOList);
        return questDTOList;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping(value = "/{qno}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable(value = "qno") Long qno, @RequestBody QuestDTO questDTO) {
        questDTO.setQno(qno);
        questService.update(questDTO);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping(value = "/{qno}")
    public void delete(@PathVariable(value = "qno") Long qno) {
        questService.delete(qno);
    }
}