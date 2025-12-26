package com.example.SplitLend.Dto;

import java.util.List;

import com.example.SplitLend.Entity.SplitType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseResponseDto {

    private Long Id;

    private String Description;

    private Long PaidBy;

    private Double AmountPaid;

    private SplitType splitType;

    List<ExpenseDetailResponseDto> expenseDetail;
}
