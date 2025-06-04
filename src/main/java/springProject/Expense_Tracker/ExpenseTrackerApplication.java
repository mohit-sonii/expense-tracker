package springProject.Expense_Tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpenseTrackerApplication {

	public static void main(String[] args) {
			SpringApplication.run(ExpenseTrackerApplication.class, args);
			System.out.println("Application started");
	}
}

