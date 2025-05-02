package springProject.Expense_Tracker.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/c/ex")
public class ExpenseControllers {
    @GetMapping
    public  ResponseEntity<String> checking(){
        return new ResponseEntity<>("reached", HttpStatus.OK);
    }
}
