package com.example.SplitLend.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SplitLend.Dto.AddConnectUserRequestDto;
import com.example.SplitLend.Entity.Connected_Users;
import com.example.SplitLend.Service.ConnectedUserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/connect")
public class ConnectedUsersController {

    private final ConnectedUserService connectedUserService;
    
    @PostMapping("/create")
    public ResponseEntity<Connected_Users> createConnection(@RequestBody @Valid AddConnectUserRequestDto addConnectUserRequestDto) {
        
        return ResponseEntity.status(HttpStatus.CREATED).body(connectedUserService.CreateConnection(addConnectUserRequestDto));
       
    }

    @PutMapping("/accept/{id}")
    public ResponseEntity<Connected_Users> acceptConnection(@PathVariable("id") Long id){

         return ResponseEntity.ok(connectedUserService.acceptConnection(id));
    }
}
