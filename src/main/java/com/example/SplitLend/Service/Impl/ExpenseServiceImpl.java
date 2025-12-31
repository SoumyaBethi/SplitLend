package com.example.SplitLend.Service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.SplitLend.Dto.AddExpenseDto;
import com.example.SplitLend.Dto.ExpenseDetailResponseDto;
import com.example.SplitLend.Dto.ExpenseResponseDto;
import com.example.SplitLend.Entity.Expense;
import com.example.SplitLend.Entity.ExpenseDetailList;
import com.example.SplitLend.Entity.Group;
import com.example.SplitLend.Entity.User;
import com.example.SplitLend.Repository.ExpenseRepository;
import com.example.SplitLend.Repository.GroupRepository;
import com.example.SplitLend.Repository.UserRepository;
import com.example.SplitLend.Service.ExpenseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService{


    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;


    @Override
    public ExpenseResponseDto CreateExpense(AddExpenseDto addExpenseDto) {
       
        HashMap<Long, Double> Splitss = addExpenseDto.getSplitss();

        Long PaidBy = addExpenseDto.getPaidBy();
        Double Amount = addExpenseDto.getAmountPaid();
        Double Sum = 0.0;

        Long GroupId = addExpenseDto.getGroupId();

        User MainUser = userRepository.findById(PaidBy)
                    .orElseThrow(() -> new RuntimeException("User not found"));

        if(CheckUsers(MainUser,Splitss)){

            
            for (Double amt : Splitss.values()) {

                Sum += amt;
            }

            if(Double.compare(Sum, Amount) == 0){

                Expense newExpense = new Expense();
                newExpense.setAmountPaid(Amount);
                newExpense.setDescription(addExpenseDto.getDescription());
                newExpense.setPaidBy(MainUser);
                newExpense.setSplitType(addExpenseDto.getSplitType());

                List<ExpenseDetailList> list = new ArrayList<>();

                Splitss.forEach((key, value) -> {
                    
                    ExpenseDetailList expenseDetailList = new ExpenseDetailList();
                    expenseDetailList.setAmount(value);

                    if(key == PaidBy){

                        expenseDetailList.setIsSetteled(true);
                    }
                    else{

                        expenseDetailList.setIsSetteled(false);
                    }
                    
                    User tempUser = userRepository.findById(key)
                    .orElseThrow(() -> new RuntimeException("User not found"));

                    expenseDetailList.setUser(tempUser);
                    expenseDetailList.setExpense(newExpense);
                    list.add(expenseDetailList);
                });
 
                newExpense.setExpenseDetail(list);

                if(GroupId != 0){

                    Group group = groupRepository.findById(GroupId).orElseThrow(() -> new RuntimeException("Group not found"));
                    newExpense.setGroup(group);
                }
                expenseRepository.save(newExpense);

                return mappToResponse(newExpense);
            }
            else{

                throw new IllegalArgumentException("Total Amount is not equal to Split Amount");
            }
        }
        else{

            throw new IllegalArgumentException("User not Found");
        }

    }

    public Boolean CheckUsers(User PaidBy,HashMap<Long, Double> Splitss){


        for (Long user : Splitss.keySet()) {

            if(!userRepository.existsById(user)){

                return false;
            }
        }

        Set<Long> connections = PaidBy.getConnected_Users();
        
        for (Long user : Splitss.keySet()) {

            if(user == PaidBy.getId()){

                continue;
            }
            else if(!connections.contains(user)){

                return false;
            }
        }

        return true;
    }

    public ExpenseResponseDto mappToResponse(Expense expense){

        ExpenseResponseDto expenseResponseDto = new ExpenseResponseDto();

        expenseResponseDto.setId(expense.getId());
        expenseResponseDto.setAmountPaid(expense.getAmountPaid());
        expenseResponseDto.setDescription(expense.getDescription());
        expenseResponseDto.setPaidBy(expense.getPaidBy().getId());
        expenseResponseDto.setSplitType(expense.getSplitType());
        expenseResponseDto.setGroupId(expense.getGroup().getId());

        List<ExpenseDetailList> splits = expense.getExpenseDetail();
        List<ExpenseDetailResponseDto> expenseDetail = new ArrayList<>();

        for( ExpenseDetailList split : splits){

             ExpenseDetailResponseDto temp = new ExpenseDetailResponseDto();

             temp.setAmount(split.getAmount());
             temp.setIsSettled(split.getIsSetteled());
             temp.setUserId(split.getUser().getId());

             expenseDetail.add(temp);
        }
            
        expenseResponseDto.setExpenseDetail(expenseDetail);
        return expenseResponseDto;
    }

}
