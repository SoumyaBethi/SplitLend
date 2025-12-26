package com.example.SplitLend.Service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.SplitLend.Dto.AddUserRequestDto;
import com.example.SplitLend.Dto.UserDto;
import com.example.SplitLend.Entity.User;
import com.example.SplitLend.Repository.UserRepository;
import com.example.SplitLend.Service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto CreateUser(AddUserRequestDto addUserRequestDto) {
        
        // Student newstudent = modelMapper.map(addStudentRequestdto,Student.class);

        // Student student = studentRepository.save(newstudent);

        // return modelMapper.map(student,Studentdto.class);

        User newUser = modelMapper.map(addUserRequestDto,User.class);

        User user = userRepository.save(newUser);

        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public UserDto getUserById(Long id) {
        
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());

        return new UserDto(user.getId(), user.getEmail(), user.getPassword(), user.getUsername(), user.getMobileNumber(), user.getConnected_Users());

    }

    @Override
    public List<UserDto> getUsers() {
        
        List<User> Users = userRepository.findAll();

        List<UserDto> UsersDto = new ArrayList<>();

        for(User user : Users){

            UsersDto.add(new UserDto(user.getId(),user.getEmail(), user.getPassword(), user.getUsername(), user.getMobileNumber(), user.getConnected_Users()));
        }

        return UsersDto;
    }

    @Override
    public void deleteUserById(Long id){

        if(!userRepository.existsById(id)){

            throw new IllegalArgumentException("User does not exist "+id);
        }
        else{

            userRepository.deleteById(id);
        }
    }

}
