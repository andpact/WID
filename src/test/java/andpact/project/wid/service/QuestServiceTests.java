package andpact.project.wid.service;

import andpact.project.wid.dto.QuestDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class QuestServiceTests {
    @Autowired
    private QuestService questService;

    @Test
    public void createTest() {
        IntStream.rangeClosed(1, 3).forEach(i -> {
            QuestDTO newQuestDTO = QuestDTO.builder()
                    .mID("member" + i)
                    .date(LocalDate.now())
                    .category("serCreateTest" + i)
                    .title("serCreateTest" + i)
                    .description("serCreateTest" + i)
                    .start(LocalTime.now())
                    .finish(LocalTime.now())
//                    .degree(0)
                    .build();
            questService.create(newQuestDTO);
        });
    }
    @Test
    public void readTest() {
        Long qno = 88L;
        QuestDTO questDTO = questService.read(qno);
        log.info(questDTO);
    }

    @Test
    public void readAllByDateAndMIDTest() {
        LocalDate date = LocalDate.of(2023, 3, 4);
        String mID = "member1";
        List<QuestDTO> questDTOList = questService.readAllByDateAndMID(date, mID);
        log.info(questDTOList);
    }
    @Test
    public void readAllByMIDTest() {
        String mID = "member1";
        List<QuestDTO> questDTOList = questService.readAllByMID(mID);
        log.info(questDTOList);
    }
    @Test
    public void updateTest() {
        Long qno = 88L;
        QuestDTO questDTO = questService.read(qno);
        questDTO.setTitle("serUpdateTest");
        questDTO.setDescription("serUpdateTest");
        questDTO.setStart(LocalTime.now());
        questDTO.setFinish(LocalTime.now());
        questDTO.setDegree(100);
        questService.update(questDTO);
        log.info(questDTO);
    }
    @Test
    public void deleteTest() {
        Long qno = 14L;
        questService.delete(qno);
    }
}
