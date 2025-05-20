package springProject.Expense_Tracker.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="salary")
public  class Salary{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID sal_id;
    @Column(name = "base_pay")
    private long basePay;
    @Column(name = "bonus")
    private long bonus;
    @Column(name = "salary_date")
    private String salaryDate;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name="income_id",nullable = false)
    private Income income;
}