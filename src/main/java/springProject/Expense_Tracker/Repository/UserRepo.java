package springProject.Expense_Tracker.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import springProject.Expense_Tracker.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface  UserRepo extends JpaRepository<User, UUID> {
    // custom queries

    // Find the user by username
    @Query("SELECT u from User u where u.username = :username")
    Optional<User> findUserByUsername(@Param("username")String username);

}
