package com.cavityeye.backend.user.controller;

import com.cavityeye.backend.user.dto.NotificationGroupDto;
import com.cavityeye.backend.user.dto.PermissionGroupDto;
import com.cavityeye.backend.user.dto.UserDto;
import com.cavityeye.backend.user.service.GroupService;
import com.cavityeye.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final GroupService groupService;

    private final UserService userService;


    @PostMapping()
    public UserDto createUser(@RequestBody UserDto user) {
        return userService.saveUser(user);
    }

    @GetMapping()
    public UserDto getUserById(@RequestParam String id) {
        return userService.getUserById(id);
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/in-notification-group")
    public List<UserDto> getUsersByNotificationGroup(@RequestParam String groupName) {
        return userService.getUsersByNotificationGroup();
    }

    @GetMapping("/in-permission-group")
    public List<UserDto> getUsersByPermissionGroup(@RequestParam String groupName) {
        return userService.getUsersByPermissionGroup();
    }

    @PutMapping()
    public UserDto updateUser(@RequestBody UserDto user){
        return userService.updateUser(user);
    }


    //visszaadja a permission group-okat és, hogy milyen permission-ok tartoznak bele
    @GetMapping("/permission-group/all")
    public List<PermissionGroupDto> getPermissionGroup() {
        return groupService.getPermissionGroups();
    }

    @PostMapping("/permission-group")
    public PermissionGroupDto createPermissionGroup(@RequestBody PermissionGroupDto permissionGroup) {
        return groupService.createPermissionGroup(permissionGroup);
    }

    @GetMapping("/permission-group")
    public PermissionGroupDto getPermissionGroupById(@RequestParam String id){
        return groupService.getPermissionGroupById(id);
    }

    @PutMapping("/permission-group")
    public PermissionGroupDto updatePermissionGroup(@RequestBody PermissionGroupDto permissionGroup){
        return groupService.updatePermissionGroup(permissionGroup);
    }

    @DeleteMapping("/permission-group")
    public void deletePermissionGroup(@RequestParam String id){
        groupService.deletePermissionGroup(id);
    }

    //visszaadja a notofication group-okat és hogy milyen userek tartoznak bele
    @GetMapping("/notification-group/all")
    public List<NotificationGroupDto> getNotificationGroups() {
        return groupService.getNotificationGroups();
    }

    @PostMapping("/notification-group")
    public NotificationGroupDto createNotificationGroup(@RequestBody NotificationGroupDto notificationGroup){
        return groupService.createNotificationGroup(notificationGroup);
    }

    @GetMapping("/notification-group")
    public NotificationGroupDto getNotificationGroupById(@RequestParam String id){
        return groupService.getNotificationGroupById(id);
    }

    @PutMapping("/notification-group")
    public NotificationGroupDto updateNotificationGroup(@RequestBody NotificationGroupDto notificationGroup){
        return groupService.updateNotificationGroup(notificationGroup);
    }

    @DeleteMapping("/notification-group")
    public void deleteNotificationGroup(@RequestParam String id){
        groupService.deleteNotificationGroup(id);
    }

    }
