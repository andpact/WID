package andpact.project.wid.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
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
    @NotEmpty
    private String user;
    @NotEmpty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Seoul")
    private LocalDate date;
    @NotEmpty
    @Size(max = 20)
    private String category;
    @NotEmpty
    @Size(max = 20)
    private String title;
    @NotEmpty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HHmm", timezone = "Asia/Seoul")
    private LocalTime start;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HHmm", timezone = "Asia/Seoul")
    @NotEmpty
    private LocalTime finish;
    @PositiveOrZero
    @Max(value = 100)
    private int degree;
}
