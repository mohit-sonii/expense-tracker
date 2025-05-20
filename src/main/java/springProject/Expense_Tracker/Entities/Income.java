package springProject.Expense_Tracker.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "incomes")
@Getter
@Setter

public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID income_id;

    @Column(name = "total_incomes", nullable = false)
    private double total_income=0;

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private User user;

    @OneToMany(mappedBy = "income", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Salary> salary;

    @OneToMany(mappedBy = "income", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Investment> investment;
}
