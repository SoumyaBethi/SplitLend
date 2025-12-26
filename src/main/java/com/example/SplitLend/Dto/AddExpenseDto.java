package com.example.SplitLend.Dto;

import java.util.HashMap;

import com.example.SplitLend.Entity.SplitType;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddExpenseDto {

    private String Description;

    @NotNull
    private Long PaidBy;

    @NotNull
    private Double AmountPaid;

    private SplitType splitType;

    HashMap<Long, Double> Splitss = new HashMap<>();
}
