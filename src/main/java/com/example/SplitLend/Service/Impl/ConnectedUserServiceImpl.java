package com.example.SplitLend.Service.Impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.SplitLend.Dto.AddConnectUserRequestDto;
import com.example.SplitLend.Entity.Connected_Users;
import com.example.SplitLend.Entity.ConnectionStatus;
import com.example.SplitLend.Entity.User;
import com.example.SplitLend.Repository.ConnectedUserRepository;
import com.example.SplitLend.Repository.UserRepository;
import com.example.SplitLend.Service.ConnectedUserService;
import java.util.Set;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConnectedUserServiceImpl implements ConnectedUserService {

    private final ConnectedUserRepository connectedUserRepository;
    private final UserRepository userRepository;

    @Override
    public Connected_Users CreateConnection(AddConnectUserRequestDto addConnectUserRequestDto) {

        if (addConnectUserRequestDto.getId2().equals(addConnectUserRequestDto.getId1())) {

            throw new IllegalArgumentException("User cannot connect with himself");
        }

        Connected_Users newConnection = new Connected_Users();

        User user = userRepository.findById(addConnectUserRequestDto.getId1())
                .orElseThrow(() -> new RuntimeException("User not found"));

        User friend = userRepository.findById(addConnectUserRequestDto.getId2())
                .orElseThrow(() -> new RuntimeException("User not found"));

        newConnection.setUser(user);
        newConnection.setConnectedUser(friend);
        newConnection.setStatus(ConnectionStatus.PENDING);
        newConnection.setCreatedAt(LocalDateTime.now());

        connectedUserRepository.save(newConnection);

        return newConnection;
    }

    @Override
    public Connected_Users acceptConnection(Long id) {

        Optional<Connected_Users> optionalConnection = connectedUserRepository.findById(id);
        Connected_Users connection;

        if (optionalConnection.isEmpty()) {

            throw new RuntimeException("Connection not found");
        } else {

            connection = optionalConnection.get();

            connection.setStatus(ConnectionStatus.ACCEPTED);

            User user1 = userRepository.findById(connection.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Set<Long> connectionsUser = user1.getConnected_Users();
            if (connectionsUser == null) {
                connectionsUser = new HashSet<>();
            }

            connectionsUser.add(connection.getConnectedUser().getId());
            user1.setConnected_Users(connectionsUser);
            userRepository.save(user1);

            User user2 = userRepository.findById(connection.getConnectedUser().getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            connectionsUser = user2.getConnected_Users();
            if (connectionsUser == null) {
                connectionsUser = new HashSet<>();
            }
            
            connectionsUser.add(connection.getUser().getId());
            user2.setConnected_Users(connectionsUser);
            userRepository.save(user2);

            connectedUserRepository.save(connection);

            return connection;
        }

    }

}
