package com.example.SplitLend.Service;

import com.example.SplitLend.Dto.SettleRequestDto;
import com.example.SplitLend.Dto.SettleResponseDto;

public interface SettlementService {

    
    SettleResponseDto create(SettleRequestDto settleRequestDto);


}
