package andpact.project.wid.controller;

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
public class QuestControllerTests {
    @Autowired
    private QuestController questController;

    @Test
    public void createTest() {
        IntStream.rangeClosed(1, 3).forEach(i -> {
            QuestDTO newQuestDTO = QuestDTO.builder()
                    .mID("member" + i)
                    .date(LocalDate.now())
                    .category("conCreateTest" + i)
                    .title("conCreateTest" + i)
                    .description("conCreateTest" + i)
                    .start(LocalTime.now())
                    .finish(LocalTime.now())
//                    .degree(0)
                    .build();
            questController.create(newQuestDTO);
        });
    }
    @Test
    public void readTest() {
        Long qno = 88L;
        QuestDTO questDTO = questController.read(qno);
        log.info(questDTO);
    }

    @Test
    public void readAllByDateAndMIDTest() {
        String strDate = "2023-03-04";
        List<QuestDTO> questDTOList = questController.readAllByDateAndMID(strDate);
        log.info(questDTOList);
    }
    @Test
    public void readAllByMIDTest() {
        List<QuestDTO> questDTOList = questController.readAllByMID();
        log.info(questDTOList);
    }
    @Test
    public void updateTest() {
        Long qno = 88L;
        QuestDTO questDTO = questController.read(qno);
        questDTO.setTitle("conUpdateTest");
        questDTO.setDescription("conUpdateTest");
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
