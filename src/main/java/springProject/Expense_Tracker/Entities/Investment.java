package springProject.Expense_Tracker.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="investments")
public class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name="name",nullable = false)
    private String investmentName;

    @Column(name="return_in_per",nullable = false)
    private double rateOfReturn;

    @Column(name="purchased_on",nullable = false)
    private String purchaseDate;

    @Column(name="sold_on",nullable = false)
    private String SoldDate;

    @Column(name="type",nullable = false)
    private String investmentType; // bonds, stocks, crypto

    @Column(name="risk",nullable = false)
    private String riskLevel;

    @Column(name="description",nullable = false)
    private String description;
}
