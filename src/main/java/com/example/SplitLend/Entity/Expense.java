package com.example.SplitLend.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;

    @NotNull
    private String description;
    
    // User who Paid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paid_by_user_id", nullable = false)
    private User paidBy;

    @NotNull
    private Double amountPaid;

    @Enumerated(EnumType.STRING)
    private SplitType splitType;

    
    @OneToMany(
        mappedBy = "expense",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    List<ExpenseDetailList> expenseDetail = new ArrayList<>();;
}
