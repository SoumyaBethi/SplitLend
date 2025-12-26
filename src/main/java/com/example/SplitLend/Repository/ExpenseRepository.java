package com.example.SplitLend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SplitLend.Entity.Expense;
import com.example.SplitLend.Entity.User;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>{

    List<Expense> findByPaidBy(User user);
}
