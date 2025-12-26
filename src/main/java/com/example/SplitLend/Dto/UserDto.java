package com.example.SplitLend.Dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long Id;

    private String email;

    private String password;

    private String Username;

    private String mobileNumber;

    private Set<Long> Connected_Users;

}
