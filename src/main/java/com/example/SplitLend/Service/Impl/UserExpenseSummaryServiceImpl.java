package com.example.SplitLend.Service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.SplitLend.Dto.BalanceResponseDto;
import com.example.SplitLend.Dto.UserExpenseSummaryDto;
import com.example.SplitLend.Entity.Expense;
import com.example.SplitLend.Entity.ExpenseDetailList;
import com.example.SplitLend.Entity.User;
import com.example.SplitLend.Repository.ExpenseDetailListRepository;
import com.example.SplitLend.Repository.ExpenseRepository;
import com.example.SplitLend.Repository.UserRepository;
import com.example.SplitLend.Service.UserExpenseSummaryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserExpenseSummaryServiceImpl implements UserExpenseSummaryService{

    private final ExpenseRepository expenseRepository;
    private final ExpenseDetailListRepository expenseDetailListRepository;
    private final UserRepository userRepository;

    @Override
    public UserExpenseSummaryDto getUserExpenseSummary(Long id) {
        
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());

        List<ExpenseDetailList> allExpenses = expenseDetailListRepository.findByUserAndIsSetteledFalse(user);
        List<Expense> UserPaidExpenses = expenseRepository.findByPaidBy(user);

        UserExpenseSummaryDto userExpenseSummaryDto = new UserExpenseSummaryDto();

        List<BalanceResponseDto> ToPay = new ArrayList<>();
        List<BalanceResponseDto> ToGet = new ArrayList<>();

        Double sum = 0.0;

        for(ExpenseDetailList expense : allExpenses){

            BalanceResponseDto temp = new BalanceResponseDto();

            temp.setUserId(expense.getExpense().getPaidBy().getId());
            temp.setUserName(expense.getExpense().getPaidBy().getUsername());
            temp.setAmount(expense.getAmount());
            temp.setExpenseId(expense.getExpense().getId());
            temp.setExpenseDescription(expense.getExpense().getDescription());

            sum += expense.getAmount();

            ToPay.add(temp);
        }

        userExpenseSummaryDto.setToPay(ToPay);
        userExpenseSummaryDto.setTotalAmtToPay(sum);

        sum = 0.0;

        for(Expense expense : UserPaidExpenses){

            List<ExpenseDetailList> listt = expense.getExpenseDetail();

            for(ExpenseDetailList l : listt){

                if(l.getUser().getId() != expense.getPaidBy().getId() && !l.getIsSetteled()){

                    BalanceResponseDto temp = new BalanceResponseDto();
                    temp.setAmount(l.getAmount());
                    temp.setUserId(l.getUser().getId());
                    temp.setUserName(l.getUser().getUsername());
                    sum += l.getAmount();

                    temp.setExpenseId(expense.getId());
                    temp.setExpenseDescription(expense.getDescription());
                    ToGet.add(temp);
                }
            }
        }

        userExpenseSummaryDto.setToGet(ToGet);
        userExpenseSummaryDto.setTotalAmtToGet(sum);

        return userExpenseSummaryDto;
    }

}
