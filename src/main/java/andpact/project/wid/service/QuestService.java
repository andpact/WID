package andpact.project.wid.service;

import andpact.project.wid.dto.QuestDTO;
import andpact.project.wid.domain.Quest;
import andpact.project.wid.repository.QuestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class QuestService {
    private final ModelMapper modelMapper;
    private final QuestRepository questRepository;

    public Long create(QuestDTO newQuestDTO) {
        Quest newQuest = modelMapper.map(newQuestDTO, Quest.class);
        Long qno = questRepository.save(newQuest).getQno();
        return qno;
    }
    public QuestDTO read(Long qno) {
        Optional<Quest> result = questRepository.findById(qno);
        Quest quest = result.orElseThrow();
        QuestDTO questDTO = modelMapper.map(quest, QuestDTO.class);
        return questDTO;
    }
    public List<QuestDTO> readAll() {
        List<Quest> questList = questRepository.findAll();
        List<QuestDTO> questDTOList = questList.stream().map(quest -> modelMapper.map(quest, QuestDTO.class)).collect(Collectors.toList());
        return questDTOList;
    }
    public void update(QuestDTO questDTO) {
        Optional<Quest> result = questRepository.findById(questDTO.getQno());
        Quest quest = result.orElseThrow();
        quest.changeTitle(questDTO.getTitle());
        quest.changeStart(questDTO.getStart());
        quest.changeFinish(questDTO.getFinish());
        quest.changeDegree(questDTO.getDegree());
        questRepository.save(quest);
    }
    public void delete(Long qno) {
        questRepository.deleteById(qno);
    }
}
