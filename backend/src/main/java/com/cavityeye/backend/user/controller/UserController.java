package com.cavityeye.backend.user.controller;

import com.cavityeye.backend.user.dto.UserDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping()
    public UserDto getUsers() {
        return null;
    }

    @PostMapping()
    public void createUser(@RequestBody UserDto user) {
        System.out.println(user);
    }
}
