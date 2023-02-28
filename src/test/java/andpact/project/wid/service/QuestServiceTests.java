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
                    .member("member" + i)
                    .date(LocalDate.now())
                    .category("serCreateTest" + i)
                    .title("serCreateTest" + i)
                    .start(LocalTime.now())
                    .finish(LocalTime.now())
//                    .degree(0)
                    .build();
            questService.create(newQuestDTO);
        });
    }
    @Test
    public void readTest() {
        Long qno = 68L;
        QuestDTO questDTO = questService.read(qno);
        log.info(questDTO);
    }
    @Test
    public void readAllTest() {
        List<QuestDTO> questDTOList = questService.readAll();
    }
    @Test
    public void updateTest() {
        Long qno = 38L;
        QuestDTO questDTO = questService.read(qno);
        questDTO.setTitle("serUpdateTest");
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
