package springProject.Expense_Tracker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springProject.Expense_Tracker.Entities.Expense;

import java.util.UUID;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, UUID> {

}
