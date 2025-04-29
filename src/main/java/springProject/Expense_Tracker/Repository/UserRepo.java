package springProject.Expense_Tracker.Repository;

import springProject.Expense_Tracker.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface  UserRepo extends JpaRepository<User, UUID> {
//    Optional<User> findByUsername(String username);
}
