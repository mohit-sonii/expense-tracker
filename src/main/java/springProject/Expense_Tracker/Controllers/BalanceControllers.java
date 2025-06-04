package springProject.Expense_Tracker.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springProject.Expense_Tracker.Entities.User;
import springProject.Expense_Tracker.Service.BalanceService;
import springProject.Expense_Tracker.Service.UserService;

import java.util.UUID;

@RequestMapping("/c/{user_id}/bal")
@RestController
public class BalanceControllers {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getBalance(@RequestParam(value = "user_id")String id){
        // find the user -> get the balance amount
        UUID user_id = UUID.fromString(id);
        User user = userService.findUserById(user_id);
        if(user==null){
            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user.getBalance().getBalAmount(), HttpStatus.OK);
    }
}
