package springProject.Expense_Tracker.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import springProject.Expense_Tracker.Entities.Balance;
import springProject.Expense_Tracker.Entities.Income;
import springProject.Expense_Tracker.Entities.Salary;
import springProject.Expense_Tracker.Entities.User;
import springProject.Expense_Tracker.Repository.BalanceRepo;
import springProject.Expense_Tracker.Repository.IncomeRepo;
import springProject.Expense_Tracker.Repository.SalaryRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SalaryService {

    @Autowired
    private IncomeRepo incomeRepo;

    @Autowired
    private SalaryRepo salaryRepo;

    @Autowired
    private BalanceRepo balanceRepo;

    public ResponseEntity<String> addIfFirst(User user, Salary salary) {
        try {
            Income incomeEntity = new Income();
            List<Salary> list = new ArrayList<>();

            double total_salary_amount = salary.getBasePay() + salary.getBonus();
            double total_balance = user.getBalance()==null ? total_salary_amount :user.getBalance().getBalAmount() + total_salary_amount;
            Balance bal = new Balance();
            bal.setBalAmount(total_balance);

            user.setBalance(bal);

            incomeEntity.setUser(user);
            incomeEntity.setTotal_income(incomeEntity.getTotal_income() + total_salary_amount);

            salary.setIncome(incomeEntity);
            list.add(salary);
            user.setIncome(incomeEntity);

            balanceRepo.save(bal);
            incomeEntity.setSalary(list);
            incomeRepo.save(incomeEntity);

            return new ResponseEntity<>("Salary Added Successfully", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("UnExpected Error while adding your data" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> addIfDont(User user, Salary salary, Income income) {
        try {
            double total_salary = salary.getBasePay() + salary.getBonus();

            Balance balance = user.getBalance();
            if(balance==null){
                balance = new Balance();
            }
            balance.setBalAmount(balance.getBalAmount() + total_salary);

            user.setBalance(balance);

            income.setTotal_income(income.getTotal_income() + total_salary);
            salary.setIncome(income);

            salaryRepo.save(salary);

            return new ResponseEntity<>("Salary Added Successfully", HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("Unexpected Error occurred" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

