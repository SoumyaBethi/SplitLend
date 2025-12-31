package com.example.SplitLend.Service;


import com.example.SplitLend.Dto.CreateGroupRequestDto;
import com.example.SplitLend.Dto.CreateGroupResponseDto;

public interface GroupService {

    CreateGroupResponseDto createGroup(CreateGroupRequestDto createGroupRequestDto);

    CreateGroupResponseDto getGroupById(Long id);

    void deleteGroupById(Long id);
}
