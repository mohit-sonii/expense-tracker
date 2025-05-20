package springProject.Expense_Tracker.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Entity
@Table(name="balance")
@Getter
@Setter
@Component
public class Balance {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID bal_id;

    @Column(name="balance_amount")
    private double balAmount=0;

    @OneToOne(mappedBy = "balance")
    private User user;

}
