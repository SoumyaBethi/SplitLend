package com.example.SplitLend.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SplitLend.Dto.SettleRequestDto;
import com.example.SplitLend.Dto.SettleResponseDto;
import com.example.SplitLend.Service.SettlementService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/settle")
public class SettlementController {

    private final SettlementService settlementService;

    @PostMapping("/create")
    public ResponseEntity<SettleResponseDto> create(@RequestBody @Valid SettleRequestDto settleRequestDto) {
        
        return ResponseEntity.status(HttpStatus.CREATED).body(settlementService.create(settleRequestDto));
    }
}
