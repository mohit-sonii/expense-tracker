package springProject.Expense_Tracker.Entities;

import org.hibernate.annotations.UuidGenerator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name="expenses")
@Getter
@Setter
public class Expense {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID exp_id;

    @Column(name="category",nullable=false)
    private String category;

    @Column(name="amount",nullable=false)
    private long xpense;

    @Column(name="description",nullable=false)
    private String description;

    @Column(name="expense_date",nullable=false)
    private String expenseDate;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user;
}
