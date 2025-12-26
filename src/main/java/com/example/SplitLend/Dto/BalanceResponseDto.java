package com.example.SplitLend.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceResponseDto {
    
    private Long userId;
    private String userName;
    private Double amount;
    private Long expenseId;
    private String expenseDescription;
}