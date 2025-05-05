package springProject.Expense_Tracker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springProject.Expense_Tracker.Entities.Expense;

import java.util.UUID;

public interface ExpenseRepo extends JpaRepository<Expense, UUID> {


}
