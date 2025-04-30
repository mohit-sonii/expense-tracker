package springProject.Expense_Tracker.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserControllers {
    @GetMapping
    private String GetUser (){
        return "User is Getting";
    }
}
