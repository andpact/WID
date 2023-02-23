package andpact.project.wid.domain;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class User {
    @Id
    private String userID;
    private String userPW;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<UserRole> roleSet = new HashSet<>();

    public void changePw(String newUserPW) {
        this.userPW = newUserPW;
    }

    public void addRole(UserRole userRole) {
        this.roleSet.add(userRole);
    }

    public void clearRole() {
        this.roleSet.clear();
    }
}
