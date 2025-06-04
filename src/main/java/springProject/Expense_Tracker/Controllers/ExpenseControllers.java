package springProject.Expense_Tracker.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springProject.Expense_Tracker.Entities.Balance;
import springProject.Expense_Tracker.Entities.Expense;
import springProject.Expense_Tracker.Entities.User;
import springProject.Expense_Tracker.Service.ExpenseService;
import springProject.Expense_Tracker.Service.UserService;

import java.util.*;

@RestController
@RequestMapping("/c/ex/{user_id}")
public class ExpenseControllers {

    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private Balance balance;
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@PathVariable UUID user_id, @RequestBody Expense data) {
        if (data == null) {
            return new ResponseEntity<>("Please Provide enough amount", HttpStatus.BAD_REQUEST);
        }
        User user = userService.findUserById(user_id);
        if (user == null) {
            return new ResponseEntity<>("User not Found !!", HttpStatus.NOT_FOUND);
        }
        Balance balance = user.getBalance();
        double updatedExpense = balance.getBalAmount() - data.getXpense();

        ResponseEntity<String> result = expenseService.updateBalance(balance.getBal_id(), updatedExpense);
        if (result.getStatusCode().value() != 200) {
            return new ResponseEntity<>("Addition to the table unsuccessful", HttpStatus.NOT_MODIFIED);
        }

        data.setBalanceLeft(updatedExpense);
        return expenseService.saveExpense(data, user_id);
    }

    @GetMapping("/get-expenses")
    public ResponseEntity<?> getExpenseList(@PathVariable UUID id) {
        try {
            List<Expense> result = expenseService.getExpenses(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error" + e,
                    HttpStatus.NOT_FOUND);
        }
    }
}
