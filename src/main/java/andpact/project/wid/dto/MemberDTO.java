package andpact.project.wid.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO { // 회원가입을 위한 DTO
    private String mID;
    private String mPW;
}
