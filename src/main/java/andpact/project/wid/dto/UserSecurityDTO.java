package andpact.project.wid.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
@ToString
public class UserSecurityDTO extends User { // 인증(로그인)을 위한 DTO
    private String userID;
    private String userPW;

    public UserSecurityDTO(String username, String password, Collection<GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.userID = username;
        this.userPW = password;
    }
}
