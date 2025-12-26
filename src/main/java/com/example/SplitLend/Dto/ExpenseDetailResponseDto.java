package com.example.SplitLend.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDetailResponseDto {

    private Long userId;
    private Double amount;
    private Boolean isSettled;
}
