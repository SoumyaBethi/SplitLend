package com.example.SplitLend.Service;

import java.util.List;

import com.example.SplitLend.Dto.AddUserRequestDto;
import com.example.SplitLend.Dto.UserDto;

public interface  UserService {

    UserDto CreateUser(AddUserRequestDto addUserRequestDto);

    UserDto getUserById(Long id);

    List<UserDto> getUsers();
   
    void deleteUserById(Long id);
}
