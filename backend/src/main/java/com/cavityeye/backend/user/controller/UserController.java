package com.cavityeye.backend.user.controller;

import com.cavityeye.backend.user.dto.UserDto;
import com.cavityeye.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;



    @PostMapping()
    public void createUser(@RequestBody UserDto user) {
        userService.saveUser(user);
    }

    @GetMapping()
    public UserDto getUserById(@RequestParam String id){
        return userService.findUserById(id);
    }
}
