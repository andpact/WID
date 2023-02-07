package andpact.project.wid.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestDTO {

    private Long qno;
    private String user;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Seoul")
    private LocalDate date;
    private String category;
    private String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HHmm", timezone = "Asia/Seoul")
    private LocalTime start;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HHmm", timezone = "Asia/Seoul")
    private LocalTime finish;
    private int degree;
}
