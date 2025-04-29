package springProject.Expense_Tracker.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false,unique = true,name = "user_name")
    private String username;

    @OneToOne
    @JoinColumn(name="income_id",referencedColumnName = "id")
    private Income incomeId;

    @OneToOne
    @JoinColumn(name="expense_id",referencedColumnName = "id")
    private Expense expenseId;

}
