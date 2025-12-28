package com.example.SplitLend.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(
    name = "user_group"
)
public class Group {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;

    private String name;

    // User who created group
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_user", nullable = false)
    private User createdBy;

    @OneToMany(
        mappedBy = "group",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<GroupMembers> members = new ArrayList<>();

    @OneToMany(mappedBy = "group")
    private List<Expense> expenses = new ArrayList<>();
}
