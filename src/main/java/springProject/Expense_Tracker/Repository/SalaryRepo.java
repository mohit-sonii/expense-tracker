package springProject.Expense_Tracker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import springProject.Expense_Tracker.Entities.Income;
import springProject.Expense_Tracker.Entities.Salary;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SalaryRepo extends JpaRepository<Salary, UUID> {

    // query to find the income id
    @Query("SELECT s from Salary s where s.income.income_id=:income_id")
    Optional<Income> findTheIncome(@Param("income_id") UUID income_id);
}
