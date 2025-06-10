package springProject.Expense_Tracker.Service.SalaryServiceTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import springProject.Expense_Tracker.Entities.Balance;
import springProject.Expense_Tracker.Entities.Income;
import springProject.Expense_Tracker.Entities.Salary;
import springProject.Expense_Tracker.Entities.User;
import springProject.Expense_Tracker.Repository.BalanceRepo;
import springProject.Expense_Tracker.Repository.IncomeRepo;
import springProject.Expense_Tracker.Repository.SalaryRepo;
import springProject.Expense_Tracker.Service.SalaryService;

@ExtendWith(MockitoExtension.class)
public class SalaryServiceTest {

    @InjectMocks
    private SalaryService salaryService;
    @Mock
    private IncomeRepo incomeRepo;
    @Mock
    private BalanceRepo balanceRepo;

    @Mock
    private SalaryRepo salaryRepo;

    @Test
    public void addIfFirstTest(){
        User user = new User();
        Salary salary=new Salary();

        salary.setBasePay(25000);
        salary.setBonus(1205);

        user.setUsername("Rohit");
        user.setPassword("asdfkajsdhfkjahdsfkjasd");

        user.setBalance(null);
        when(balanceRepo.save(any(Balance.class))).thenAnswer(invocation->invocation.getArgument(0));

        when(incomeRepo.save(any(Income.class))).thenAnswer(invocation->invocation.getArgument(0));

        ResponseEntity<String> result =salaryService.addIfFirst(user,salary);

        assertEquals(HttpStatus.OK,result.getStatusCode());
        assertEquals("Salary Added Successfully",result.getBody());

        verify(balanceRepo).save(any(Balance.class));
        verify(incomeRepo).save(any(Income.class));
    }

    @Test
    public void addIfDontText(){
        User user= new User();
        Salary salary = new Salary();

        salary.setBasePay(21450);
        salary.setBonus(2140);

        user.setBalance(null);

        Income income = new Income();

        when(salaryRepo.save(any(Salary.class))).thenAnswer((invocation -> invocation.getArgument(0)));

        ResponseEntity<String> result = salaryService.addIfDont(user,salary,income);

        assertEquals(HttpStatus.CREATED,result.getStatusCode());
        assertEquals("Salary Added Successfully",result.getBody());

        verify(salaryRepo).save(any(Salary.class));

    }
}
