package com.example.SplitLend.Service.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.SplitLend.Dto.SettleRequestDto;
import com.example.SplitLend.Dto.SettleResponseDto;
import com.example.SplitLend.Entity.ExpenseDetailList;
import com.example.SplitLend.Entity.Settlement;
import com.example.SplitLend.Entity.User;
import com.example.SplitLend.Repository.ExpenseDetailListRepository;
import com.example.SplitLend.Repository.SettlementRepository;
import com.example.SplitLend.Repository.UserRepository;
import com.example.SplitLend.Service.SettlementService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SettleServiceImpl implements SettlementService{

    private final ExpenseDetailListRepository expenseDetailListRepository;
    private final SettlementRepository settlementRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    

    @Override
    public SettleResponseDto create(SettleRequestDto settleRequestDto) {

        User from_user = userRepository.findById(settleRequestDto.getFromUser()).orElseThrow(() -> new IllegalArgumentException());
        User to_user = userRepository.findById(settleRequestDto.getToUser()).orElseThrow(() -> new IllegalArgumentException());
       
        List<ExpenseDetailList> pending = expenseDetailListRepository.pendingDebts(settleRequestDto.getFromUser(), settleRequestDto.getToUser());
        
        Double remaining = settleRequestDto.getAmountPaid();

        if(pending.isEmpty()){

            throw new IllegalArgumentException("No Balance");
        }

        for(ExpenseDetailList expense : pending){

            if(expense.getAmount() <= remaining){

                remaining = remaining - expense.getAmount();
                expense.setAmount(0.0);
                expense.setIsSetteled(true);
            }
            else{

                expense.setAmount(expense.getAmount() - remaining);
                remaining = 0.0;
                break;
            }

            expenseDetailListRepository.save(expense);

        }

        Settlement newsettle = new Settlement();
        newsettle.setAmountPaid(settleRequestDto.getAmountPaid());
        newsettle.setCreatedAt(LocalDateTime.now());
        newsettle.setFromUser(from_user);
        newsettle.setToUser(to_user);
        
        settlementRepository.save(newsettle);

        SettleResponseDto settleResponseDto = new SettleResponseDto();
        settleResponseDto.setAmountPaid(settleRequestDto.getAmountPaid());
        settleResponseDto.setFromUser(settleRequestDto.getFromUser());
        settleResponseDto.setToUser(settleRequestDto.getToUser());
        settleResponseDto.setCreatedAt(LocalDateTime.now());
        settleResponseDto.setId(newsettle.getId());
        
        return settleResponseDto;
    }

}
