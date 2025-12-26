package com.example.SplitLend.Service;

import com.example.SplitLend.Dto.AddExpenseDto;
import com.example.SplitLend.Dto.ExpenseResponseDto;

public interface ExpenseService {

    
    ExpenseResponseDto CreateExpense(AddExpenseDto addExpenseDto);

}
