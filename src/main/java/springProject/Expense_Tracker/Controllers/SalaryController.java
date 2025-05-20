package springProject.Expense_Tracker.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springProject.Expense_Tracker.Entities.Income;
import springProject.Expense_Tracker.Entities.Salary;
import springProject.Expense_Tracker.Entities.User;
import springProject.Expense_Tracker.Repository.UserRepo;
import springProject.Expense_Tracker.Service.SalaryService;

import java.util.UUID;

@RestController
@RequestMapping("/c/in/{id}/sal")
public class SalaryController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SalaryService salaryService;

    @PostMapping("/add")
    public ResponseEntity<String> create_salary_field(@RequestBody Salary salary, @PathVariable UUID id) {
            try {
                User user = userRepo.findById(id).orElse(null);
                if (user == null) return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
                Income income = user.getIncome();

                if (income == null) {
                   return salaryService.addIfFirst(user,salary);
                } else {
                    return salaryService.addIfDont(user,salary,income);
                }
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
