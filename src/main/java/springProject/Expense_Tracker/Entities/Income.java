package springProject.Expense_Tracker.Entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="incomes")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    // it will be monthly and will addition of salary + investment return - expense
    private long total_income;

    @OneToOne
    @JoinColumn(name="salary_id",referencedColumnName = "id")
    private Salary salary;

    @OneToOne
    @JoinColumn(name="investment_id",referencedColumnName = "id")
    private Investment investment;

}
