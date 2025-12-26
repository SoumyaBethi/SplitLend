package com.example.SplitLend.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SplitLend.Dto.UserExpenseSummaryDto;
import com.example.SplitLend.Service.UserExpenseSummaryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserExpenseSummaryController {

    private final UserExpenseSummaryService userExpenseSummaryService;

    @GetMapping("/summary/{id}")
    public ResponseEntity<UserExpenseSummaryDto> getUserById(@PathVariable("id") Long id) {
        
        return ResponseEntity.ok(userExpenseSummaryService.getUserExpenseSummary(id));
    }

}
