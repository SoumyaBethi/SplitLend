package com.example.SplitLend.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserExpenseSummaryDto {

    private Double totalAmtToGet;

    private Double totalAmtToPay;

    List<BalanceResponseDto> ToPay;

    List<BalanceResponseDto> ToGet;
}
