package andpact.project.wid.service;

import andpact.project.wid.dto.RecordDTO;
import andpact.project.wid.entity.Record;
import andpact.project.wid.repository.RecordRepository;
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
public class RecordService {
    private final ModelMapper modelMapper;
    private final RecordRepository recordRepository;

    public Long create(RecordDTO newRecordDTO) {
        Record newRecord = modelMapper.map(newRecordDTO, Record.class);
        Long rno = recordRepository.save(newRecord).getRno();
        return rno;
    }

    public RecordDTO read(Long rno) {
        Optional<Record> result = recordRepository.findById(rno);
        Record record = result.orElseThrow();
        RecordDTO recordDTO = modelMapper.map(record, RecordDTO.class);
        return recordDTO;
    }

    public List<RecordDTO> readAll() {
        List<Record> recordList = recordRepository.findAll();
        List<RecordDTO> recordDTOList = recordList.stream().map(record -> modelMapper.map(record, RecordDTO.class)).collect(Collectors.toList());
        return recordDTOList;
    }

    public void update(RecordDTO recordDTO) {
        Optional<Record> result = recordRepository.findById(recordDTO.getRno());
        Record record = result.orElseThrow();
        record.changeFinish(recordDTO.getFinish());
        record.changeTitle(recordDTO.getTitle());
        record.changeStart(recordDTO.getStart());
        recordRepository.save(record);
    }

    public void delete(Long rno) {
        recordRepository.deleteById(rno);
    }
}
