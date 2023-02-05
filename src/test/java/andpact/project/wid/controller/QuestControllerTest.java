package andpact.project.wid.controller;

import andpact.project.wid.dto.QuestDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class QuestControllerTest {
    @Autowired
    private QuestController questController;

    @Test
    public void createTest() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            QuestDTO newQuestDTO = QuestDTO.builder()
                    .title("conCreateTest" + i)
                    .user("user" + i)
                    .dueDate(LocalDate.of(2022, (i % 12) + 1, (i % 30) + 1))
                    .finished(false)
                    .build();
            questController.create(newQuestDTO);
        });
    }
    @Test
    public void readTest() {
        Long qno = 14L;
        QuestDTO questDTO = questController.read(qno);
        log.info(questDTO);
    }
    @Test
    public void readAllTest() {
        questController.readAll();
    }
    @Test
    public void updateTest() {
        Long qno = 14L;
        QuestDTO questDTO = questController.read(qno);
        questDTO.setTitle("conUpdateTest");
        questDTO.setDueDate(LocalDate.now());
        questDTO.setFinished(true);
        questController.update(qno, questDTO);
        log.info(questDTO);
    }
    @Test
    public void deleteTest() {
        Long qno = 14L;
        questController.delete(qno);
    }
}
