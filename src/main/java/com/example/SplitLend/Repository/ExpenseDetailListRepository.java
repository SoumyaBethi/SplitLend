package com.example.SplitLend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.SplitLend.Entity.ExpenseDetailList;
import com.example.SplitLend.Entity.User;

public interface ExpenseDetailListRepository extends JpaRepository<ExpenseDetailList, Long>{

    List<ExpenseDetailList> findByUserAndIsSetteledFalse(User user);

    @Query("""
    SELECT ed FROM ExpenseDetailList ed
    WHERE ed.user.id = :fromUser
      AND ed.expense.paidBy.id = :toUser
      AND ed.isSetteled = false
    ORDER BY ed.id
    """)
    List<ExpenseDetailList> pendingDebts(Long fromUser,Long toUser);
}
