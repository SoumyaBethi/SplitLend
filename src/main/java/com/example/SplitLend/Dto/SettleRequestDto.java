package com.example.SplitLend.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SettleRequestDto {

    @NotNull
    private Long fromUser;

    @NotNull
    private Long toUser;

    @NotNull
    private Double amountPaid;
}
