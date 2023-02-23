package andpact.project.wid.repository;

import andpact.project.wid.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    @EntityGraph(attributePaths = "roleSet")
    @Query("select u from User u where u.userID = :userID")
    Optional<User> getWithRoles(String userID);
}
