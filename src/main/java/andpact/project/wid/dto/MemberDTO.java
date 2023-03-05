package andpact.project.wid.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO { // 회원가입을 위한 DTO

    @NotEmpty
    @Size(min = 3, max = 14)
    private String mID;
    @NotEmpty
    @Size(min = 3, max = 14)
    private String mPW;
    @Email(regexp = "example@gmail.com")
    private String email;
}
