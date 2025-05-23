package springProject.Expense_Tracker.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import springProject.Expense_Tracker.Entities.Expense;
import springProject.Expense_Tracker.Entities.User;
import springProject.Expense_Tracker.Repository.ExpenseRepo;
import springProject.Expense_Tracker.Repository.UserRepo;


import java.util.*;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepo expenseRepo;

    @Autowired
    private UserRepo userRepo;

    public ResponseEntity<String> saveExpense(Expense data, UUID id){
        try{
            User foundUser = userRepo.findById(id).orElseThrow();
            data.setUser(foundUser);
            foundUser.getExpenses().add(data);
            userRepo.save(foundUser);
            return new ResponseEntity<>("Expense Added Successfully ",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Unexpected Server Error "+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public record ExpenseDTO(
            UUID id,
            String category,
            long expenseAmount,
            String description,
            String expenseDate,
            String paymentType,
            double balance_left
    ){}
    public List<ExpenseDTO> getExpenses(UUID id){
        try{
            User user = userRepo.findById(id).orElseThrow();
            return user.getExpenses().stream().map(e->new ExpenseDTO(
                    e.getExp_id(),
                    e.getCategory(),
                    e.getXpense(),
                    e.getDescription(),
                    e.getExpenseDate(),
                    e.getPaymentType(),
                    e.getBalanceLeft()
            )).toList();

        }catch(Exception e){
            throw new Error("Internal Server Error");
        }
    }
}
