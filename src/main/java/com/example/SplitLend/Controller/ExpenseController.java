package com.example.SplitLend.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SplitLend.Dto.AddExpenseDto;
import com.example.SplitLend.Dto.ExpenseResponseDto;
import com.example.SplitLend.Service.ExpenseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping("/create")
    public ResponseEntity<ExpenseResponseDto> createConnection(@RequestBody @Valid AddExpenseDto addExpenseDto) {
        
        return ResponseEntity.status(HttpStatus.CREATED).body(expenseService.CreateExpense(addExpenseDto));
       
    }
}
