package springProject.Expense_Tracker.Entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.engine.internal.Cascade;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID user_id;

    @Column(nullable = false, unique = true, name = "user_name")
    private String username;

    @Column(nullable = false, name = "password")
    private String password;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private Income income;

    @OneToOne
    @JoinColumn(name = "bal_id")
    private Balance balance;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Expense> expenses;
}

// The user field in the Expense entity owns this relationship
// CasCade -> any operation done on User will be automatically cascade in Expense table. If you save a user, expense will also save. if you delete a user their expense will delete as well.
// OrphanRemoval -> if you remove an Expense from the user.getExpenses() list, and save the user, that expense will be deleted from the database as well.