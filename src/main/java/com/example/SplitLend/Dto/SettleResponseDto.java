package com.example.SplitLend.Dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SettleResponseDto {

    private Long Id;

    @NotNull
    private Long fromUser;

    @NotNull
    private Long toUser;

    @NotNull
    private Double amountPaid;

    private LocalDateTime createdAt;
}
