package andpact.project.wid.service;

import andpact.project.wid.dto.QuestDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class QuestServiceTest {

    @Autowired
    private QuestService questService;

    @Test
    public void createTest() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            QuestDTO newQuestDTO = QuestDTO.builder()
                    .title("serCreateTest" + i)
                    .user("user" + i)
                    .dueDate(LocalDate.of(2022, (i % 12) + 1, (i % 30) + 1))
                    .finished(false)
                    .build();
            questService.create(newQuestDTO);
        });
    }
    @Test
    public void readTest() {
        Long qno = 14L;
        QuestDTO questDTO = questService.read(qno);
        log.info(questDTO);
    }
    @Test
    public void readAllTest() {
        List<QuestDTO> questDTOList = questService.readAll();
    }
    @Test
    public void updateTest() {
        Long qno = 14L;
        QuestDTO questDTO = questService.read(qno);
        questDTO.setTitle("serUpdateTest");
        questDTO.setDueDate(LocalDate.now());
        questDTO.setFinished(true);
        questService.update(questDTO);
        log.info(questDTO);
    }
    @Test
    public void deleteTest() {
        Long qno = 14L;
        questService.delete(qno);
    }
}
