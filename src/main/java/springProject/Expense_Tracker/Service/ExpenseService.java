package springProject.Expense_Tracker.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import springProject.Expense_Tracker.Entities.Expense;
import springProject.Expense_Tracker.Entities.User;
import springProject.Expense_Tracker.Repository.BalanceRepo;
import springProject.Expense_Tracker.Repository.ExpenseRepo;
import springProject.Expense_Tracker.Repository.UserRepo;

import java.util.*;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepo expenseRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BalanceRepo balanceRepo;

    public ResponseEntity<String> saveExpense(Expense data, UUID id) {
        try {
            User foundUser = userRepo.findById(id).orElseThrow();
            data.setUser(foundUser);
            expenseRepo.save(data);
            return new ResponseEntity<>("Expense Added Successfully ", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Unexpected Server Error " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Expense> getExpenses(UUID id) {
        try {
            User user = userRepo.findById(id).orElseThrow();
            return user.getExpenses();
        } catch (Exception e) {
            throw new Error("Internal Server Error");
        }
    }

    public ResponseEntity<String> updateBalance(UUID bal_id, Double updatedAmount) {
        try {
            balanceRepo.updateBalAmount(bal_id, updatedAmount);
            return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


//    public record ExpenseDTO(
//            UUID id,
//            String category,
//            long expenseAmount,
//            String description,
//            String expenseDate,
//            String paymentType,
//            double balance_left
//    ){}


//                    stream().map(e->new ExpenseDTO(
//                    e.getExp_id(),
//                    e.getCategory(),
//                    e.getXpense(),
//                    e.getDescription(),
//                    e.getExpenseDate(),
//                    e.getPaymentType(),
//                    e.getBalanceLeft()
//            )).toList();