package andpact.project.wid.controller;

import andpact.project.wid.dto.QuestDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class QuestControllerTests {
    @Autowired
    private QuestController questController;

    @Test
    public void createTest() {
        IntStream.rangeClosed(1, 3).forEach(i -> {
            QuestDTO newQuestDTO = QuestDTO.builder()
                    .member("member" + i)
                    .date(LocalDate.now())
                    .category("conCreateTest" + i)
                    .title("conCreateTest" + i)
                    .start(LocalTime.now())
                    .finish(LocalTime.now())
//                    .degree(0)
                    .build();
            questController.create(newQuestDTO);
        });
    }
    @Test
    public void readTest() {
        Long qno = 68L;
        QuestDTO questDTO = questController.read(qno);
        log.info(questDTO);
    }
    @Test
    public void readAllTest() {
        questController.readAll();
    }
    @Test
    public void updateTest() {
        Long qno = 68L;
        QuestDTO questDTO = questController.read(qno);
        questDTO.setTitle("conUpdateTest");
        questDTO.setStart(LocalTime.now());
        questDTO.setFinish(LocalTime.now());
        questDTO.setDegree(100);
        questController.update(qno, questDTO);
        log.info(questDTO);
    }
    @Test
    public void deleteTest() {
        Long qno = 14L;
        questController.delete(qno);
    }
}
