package com.example.SplitLend.Service;

import com.example.SplitLend.Dto.AddConnectUserRequestDto;
import com.example.SplitLend.Entity.Connected_Users;

public interface ConnectedUserService {

    
    Connected_Users CreateConnection(AddConnectUserRequestDto addConnectUserRequestDto);

    
    Connected_Users acceptConnection(Long id);

}
