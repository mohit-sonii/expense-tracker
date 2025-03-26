package springProject.Expense_Tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.*;

@SpringBootApplication
public class ExpenseTrackerApplication {

	public static void main(String[] args) {
		String databaseName= System.getenv("POSTGRES_DB");
		String name = System.getenv("POSTGRES_USER");
		String password = System.getenv("POSTGRES_PASSWORD");
		String url = "jdbc:postgresql://localhost:5432/"+databaseName;
		try{
//			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(url,name,password);
			System.out.print(connection);
		}catch(SQLException e){
			e.printStackTrace();
		}

//		SpringApplication.run(ExpenseTrackerApplication.class, args);
	}

}
