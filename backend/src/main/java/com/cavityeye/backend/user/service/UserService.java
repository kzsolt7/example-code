package com.cavityeye.backend.user.service;

import com.cavityeye.backend.user.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void saveUser(UserDto user){
        System.out.println(user);
    }

    public UserDto findUserById(String id){
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
}
