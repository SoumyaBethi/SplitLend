package com.example.SplitLend.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SplitLend.Dto.AddUserRequestDto;
import com.example.SplitLend.Dto.CreateGroupRequestDto;
import com.example.SplitLend.Dto.CreateGroupResponseDto;
import com.example.SplitLend.Dto.UserDto;
import com.example.SplitLend.Service.GroupService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/create")
    public ResponseEntity<CreateGroupResponseDto> createGroup(@RequestBody @Valid CreateGroupRequestDto createGroupRequestDto) {
        
        return ResponseEntity.status(HttpStatus.CREATED).body(groupService.createGroup(createGroupRequestDto));
    }

    @GetMapping("/getGroup/{id}")
    public ResponseEntity<CreateGroupResponseDto> getGroup(@PathVariable("id") Long id) {
        
        return ResponseEntity.ok(groupService.getGroupById(id));
    }
    
    @DeleteMapping("/deleteGroup/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable("id") Long id){

        groupService.deleteGroupById(id);
        return ResponseEntity.noContent().build();
    }
}
