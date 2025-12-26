package com.example.SplitLend.Dto;

import java.time.LocalDateTime;

import com.example.SplitLend.Entity.ConnectionStatus;
import com.example.SplitLend.Entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConnectUserDto {

    private Long Id;

    private User user;

    private User connectedUser;

    private ConnectionStatus status;

    private LocalDateTime createdAt;
}
