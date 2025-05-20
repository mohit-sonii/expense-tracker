package springProject.Expense_Tracker.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springProject.Expense_Tracker.Entities.Balance;
import springProject.Expense_Tracker.Entities.Expense;
import springProject.Expense_Tracker.Service.ExpenseService;

import java.util.*;

@RestController
@RequestMapping("/c/ex/{id}")
public class ExpenseControllers {
    @Autowired private ExpenseService expenseService;
    @Autowired private Balance balance;

    @GetMapping
    public ResponseEntity<String> check(){
        return new ResponseEntity<>("Working fine",HttpStatus.OK);
    }

    @PostMapping("/create")
    public  ResponseEntity<String> create(@PathVariable UUID id ,@RequestBody Expense data){
        if(data==null){
            return new ResponseEntity<>("Please Provide enough data",HttpStatus.BAD_REQUEST);
        }
//        double actualBalance = balance.getBalAmount();
//        if(actualBalance<data.getXpense()){
//            return new ResponseEntity<>("Not Sufficient Balance",HttpStatus.INSUFFICIENT_STORAGE);
//        }
//        double afterExpense  = actualBalance-data.getXpense();
//        balance.setBalAmount(afterExpense);
        data.setBalanceLeft(250);
        return expenseService.saveExpense(data,id);
    }

    @GetMapping("/get-expenses")
    public ResponseEntity<?> getExpenseList(@PathVariable UUID id){
        try{
            List<ExpenseService.ExpenseDTO> result = expenseService.getExpenses(id);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Internal Server Error" + e,
                    HttpStatus.NOT_FOUND);
        }
    }
}
