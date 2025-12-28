package com.example.SplitLend.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateGroupRequestDto {

    private String name;
    private Long createdBy;
    private List<Long> members;
}
