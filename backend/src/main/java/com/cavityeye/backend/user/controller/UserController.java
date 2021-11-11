package com.cavityeye.backend.user.controller;

import com.cavityeye.backend.user.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping()
    public UserDto getUsers() {
        var user = new UserDto();
        user.setName("Rolika");
        return user;
    }
}
