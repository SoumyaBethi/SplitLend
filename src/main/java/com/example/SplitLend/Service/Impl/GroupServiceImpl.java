package com.example.SplitLend.Service.Impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.SplitLend.Dto.CreateGroupRequestDto;
import com.example.SplitLend.Dto.CreateGroupResponseDto;
import com.example.SplitLend.Entity.Group;
import com.example.SplitLend.Entity.GroupMembers;
import com.example.SplitLend.Entity.User;
import com.example.SplitLend.Repository.GroupRepository;
import com.example.SplitLend.Repository.UserRepository;
import com.example.SplitLend.Service.GroupService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService{

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    @Override
    public CreateGroupResponseDto createGroup(CreateGroupRequestDto createGroupRequestDto) {

        if(createGroupRequestDto.getMembers().size() <= 2){

            throw new IllegalArgumentException("Group size should be greater than 2");
        }
       
        Long createdBy = createGroupRequestDto.getCreatedBy();
        List<Long> members = createGroupRequestDto.getMembers();

        List<GroupMembers> groupMembers = new ArrayList<>();
        User created = userRepository.findById(createdBy).orElseThrow(() -> new IllegalArgumentException());

        Group newGroup = new Group();
        newGroup.setCreatedBy(created);
        newGroup.setName(createGroupRequestDto.getName());
        // groupRepository.save(newGroup);
        
        for(Long id: members){

           User temp = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
           GroupMembers grp = new GroupMembers();
           grp.setUser(temp);
           grp.setJoinedAt(LocalDateTime.now());
           grp.setGroup(newGroup);
           
           if(id==createdBy){

              grp.setIsAdmin(true);
           }

           groupMembers.add(grp);
        }

    
        newGroup.setMembers(groupMembers);
        groupRepository.save(newGroup);

        CreateGroupResponseDto response = new CreateGroupResponseDto();
        response.setCreatedBy(createdBy);
        response.setId(newGroup.getId());
        response.setMembers(members);
        response.setName(createGroupRequestDto.getName());

        return response;
    }

    @Override
    public CreateGroupResponseDto getGroupById(Long id) {
        
        Group group = groupRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());

        
        CreateGroupResponseDto response = new CreateGroupResponseDto();
        response.setCreatedBy(group.getCreatedBy().getId());
        response.setId(group.getId());
        response.setName(group.getName());

        List<Long> members = new ArrayList<>();

        for(GroupMembers mem:group.getMembers()){

            members.add(mem.getUser().getId());
        }

        response.setMembers(members);

        return response;
    }

    @Override
    public void deleteGroupById(Long id) {

        if(!groupRepository.existsById(id)){

            throw new IllegalArgumentException("Group does not exist "+id);
        }
        else{

            groupRepository.deleteById(id);
        }
    }

}
