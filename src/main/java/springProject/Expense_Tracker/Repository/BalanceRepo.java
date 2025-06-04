package springProject.Expense_Tracker.Repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import springProject.Expense_Tracker.Entities.Balance;

import java.util.UUID;

@Repository
public interface BalanceRepo extends JpaRepository<Balance, UUID> {

    // query to update the balance amount;. insteadof creating a new one
    @Transactional
    @Modifying
    @Query("UPDATE Balance b set b.balAmount = :updatedAmount where b.bal_id=:bal_id")
    public void updateBalAmount(@Param("bal_id") UUID bal_id,@Param("updatedAmount")Double updatedAmount);

}
