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
public class Member extends BaseEntity {
    @Id
    private String mID;
    private String mPW;
    private String email;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    public void changePw(String newMPW) {
        this.mPW = newMPW;
    }
    public void changeEmail(String newEmail) {
        this.email = newEmail;
    }

    public void addRole(MemberRole memberRole) {
        this.roleSet.add(memberRole);
    }

    public void clearRole() {
        this.roleSet.clear();
    }
}
