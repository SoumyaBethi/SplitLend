package com.example.SplitLend.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SplitLend.Dto.AddUserRequestDto;
import com.example.SplitLend.Dto.UserDto;
import com.example.SplitLend.Service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createNewUser(@RequestBody @Valid AddUserRequestDto addUserRequestDto) {
        
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.CreateUser(addUserRequestDto));
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDto>> getUsers() {
        
        return ResponseEntity.ok(userService.getUsers());
    }
    
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){

        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
    
}
