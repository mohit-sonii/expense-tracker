package springProject.Expense_Tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.*;

@SpringBootApplication
public class ExpenseTrackerApplication {


	public static void main(String[] args) {
			SpringApplication.run(ExpenseTrackerApplication.class, args);
			System.out.println("Application started");

	}
}
//		String databaseName= System.getenv("POSTGRES_DB");
//		String name = System.getenv("POSTGRES_USER");
//		String password = System.getenv("POSTGRES_PASSWORD");
//		String url = "jdbc:postgresql://localhost:5432/"+databaseName;
//		try{
//			DriverManager.getConnection(url,name,password);

//		}catch(Exception e){
//			e.printStackTrace();
//		}
