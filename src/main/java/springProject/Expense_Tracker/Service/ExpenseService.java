package springProject.Expense_Tracker.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import springProject.Expense_Tracker.Entities.Expense;
import springProject.Expense_Tracker.Entities.User;
import springProject.Expense_Tracker.Repository.ExpenseRepo;
import springProject.Expense_Tracker.Repository.UserRepo;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ExpenseService {
    @Autowired
    private ExpenseRepo expenseRepo;

    @Autowired
    private UserRepo userRepo;

    public ResponseEntity<String> saveExpense(Expense data, UUID id){
        try{
            User foundUser = userRepo.findById(id).orElseThrow();
            data.setUser(foundUser);
            foundUser.getExpenseIds().add(data);
            userRepo.save(foundUser);
            return new ResponseEntity<>("Expense Added Successfully ",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Unexpected Server Error "+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
