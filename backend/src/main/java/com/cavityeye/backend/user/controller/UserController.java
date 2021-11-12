package com.cavityeye.backend.user.controller;

import com.cavityeye.backend.user.dto.NotificationGroupDto;
import com.cavityeye.backend.user.dto.PermissionGroupDto;
import com.cavityeye.backend.user.dto.UserDto;
import com.cavityeye.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import java.security.Permission;
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
        return userService.getUserById(id);
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/in-notification-group")
    public List<UserDto> getUsersByNotificationGroup(@RequestParam String groupName){
        return userService.getUsersByNotificationGroup();
    }

    @GetMapping("/in-permission-group")
    public List<UserDto> getUsersByPermissionGroup(@RequestParam String groupName){
        return userService.getUsersByPermissionGroup();
    }

    //visszaadja a notofication group-okat és hogy milyen userek tartoznak bele
    @GetMapping("/notification-groups")
    public List<NotificationGroupDto> getNotificationGroups(){
        return userService.getNotificationGroups();
    }

    //visszaadja a permission group-okat és, hogy milyen permission-ok tartoznak bele
    @GetMapping("/permission-groups")
    public List<PermissionGroupDto> getPermissionGroups(){
        return userService.getPermissionGroups();
    }


}
