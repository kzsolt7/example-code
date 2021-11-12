package com.cavityeye.backend.user.service;

import com.cavityeye.backend.user.dto.NotificationGroupDto;
import com.cavityeye.backend.user.dto.PermissionGroupDto;
import com.cavityeye.backend.user.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public void saveUser(UserDto user){
        System.out.println(user);
    }

    public UserDto getUserById(String id){
        System.out.println(id);
        var user = new UserDto();
        user.setUserName("Jani");
        user.setEmail("Valami@gmail.com");
        String[] permissions = {"editPfill","createEditRef"};
        String[] permissionGroups = {"enginner","operator"};
        user.setPermissions(permissions);
        user.setPassword("janika345");
        user.setPermissionGroups(permissionGroups);
        user.setState("Active");
        return user;
    }

    public List<UserDto> getAllUsers(){
        var user = new UserDto();
        user.setUserName("Jani");
        user.setEmail("Valami@gmail.com");
        String[] permissions = {"editPfill","createEditRef"};
        String[] permissionGroups = {"enginner","operator"};
        user.setPermissions(permissions);
        user.setPassword("janika345");
        user.setPermissionGroups(permissionGroups);
        user.setState("Active");

        var user1 = new UserDto();
        user1.setUserName("Jani");
        user1.setEmail("Valami@gmail.com");
        String[] permissions1 = {"editPfill","createEditRef"};
        String[] permissionGroups1 = {"enginner","operator"};
        user1.setPermissions(permissions1);
        user1.setPassword("janika345");
        user1.setPermissionGroups(permissionGroups1);
        user1.setState("Active");

        List<UserDto> users = new ArrayList<>();
        users.add(user);
        users.add(user1);
        return users;
    }

    public List<UserDto> getUsersByNotificationGroup() {
        return getAllUsers();
    }

    public List<UserDto> getUsersByPermissionGroup() {
        return getAllUsers();
    }

    public List<NotificationGroupDto> getNotificationGroups(){
        return null;
    }

    public List<PermissionGroupDto> getPermissionGroups() {
        return null;
    }
}
