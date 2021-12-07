package com.cavityeye.backend.user.controller;

import com.cavityeye.backend.user.dto.NotificationGroupDto;
import com.cavityeye.backend.user.dto.PermissionDto;
import com.cavityeye.backend.user.dto.PermissionGroupDto;
import com.cavityeye.backend.user.dto.UserDto;
import com.cavityeye.backend.user.service.GroupService;
import com.cavityeye.backend.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.RequiredArgsConstructor;
import org.bson.json.JsonObject;
import org.json.JSONArray;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final GroupService groupService;
    private final UserService userService;

    @PostMapping()
    public UserDto createUser(@Valid @RequestBody UserDto user) {
        return userService.saveUser(user);
    }

    @GetMapping()
    public UserDto getUserById(@RequestParam String id) {
        return userService.getUserById(id);
    }

    @GetMapping("/byUserName")
    public UserDto getUserByUserName(@RequestParam String username){
        return userService.getUserByUserName(username);
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
        return userService.getUsersByPermissionGroup(groupName);
    }

    @PutMapping()
    public UserDto updateUser(@RequestBody UserDto user) {
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
    public PermissionGroupDto getPermissionGroupById(@RequestParam String id) {
        return groupService.getPermissionGroupById(id);
    }

    @PutMapping("/permission-group")
    public PermissionGroupDto updatePermissionGroup(@RequestBody PermissionGroupDto permissionGroup) {
        return groupService.updatePermissionGroup(permissionGroup);
    }

    @DeleteMapping("/permission-group")
    public void deletePermissionGroup(@RequestParam String id) {
        groupService.deletePermissionGroup(id);
    }

    //visszaadja a notofication group-okat és hogy milyen userek tartoznak bele
    @GetMapping("/notification-group/all")
    public List<NotificationGroupDto> getNotificationGroups() {
        return groupService.getNotificationGroups();
    }

    @PostMapping("/notification-group")
    public NotificationGroupDto createNotificationGroup(@RequestBody NotificationGroupDto notificationGroup) {
        return groupService.createNotificationGroup(notificationGroup);
    }

    @GetMapping("/notification-group")
    public NotificationGroupDto getNotificationGroupById(@RequestParam String id) {
        return groupService.getNotificationGroupById(id);
    }

    @PutMapping("/notification-group")
    public NotificationGroupDto updateNotificationGroup(@RequestBody NotificationGroupDto notificationGroup) {
        return groupService.updateNotificationGroup(notificationGroup);
    }

    @DeleteMapping("/notification-group")
    public void deleteNotificationGroup(@RequestParam String id) {
        groupService.deleteNotificationGroup(id);
    }

    @GetMapping("/create-admin-user")
    public void createAdminUser() {
        userService.createAdminUser();
    }

    @GetMapping("/permissions")
    public List<PermissionDto> getPermissions() {
        return groupService.getPermissions();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportAllUser() {
        var users = userService.getAllUsers();
        var json = new JSONArray(users).toString().getBytes();

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=users"+ Instant.now() +".json")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(json.length)
                .body(json);
    }
}


