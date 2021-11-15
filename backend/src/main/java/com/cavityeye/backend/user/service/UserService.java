package com.cavityeye.backend.user.service;

import com.cavityeye.backend.user.dto.NotificationGroupDto;
import com.cavityeye.backend.user.dto.PermissionGroupDto;
import com.cavityeye.backend.user.dto.UserDto;
import com.cavityeye.backend.user.repository.PermissionGroupRepository;
import com.cavityeye.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PermissionGroupRepository permissionGroupRepository;

    public void saveUser(UserDto user) {
        System.out.println(user);
        userRepository.save(user);
    }

    public UserDto getUserById(String id) {
        var user = userRepository.findById(id);
        return user.get();
    }

    public List<UserDto> getAllUsers() {
       var users = userRepository.findAll();
        return users;
    }

    public List<UserDto> getUsersByNotificationGroup() {
        return getAllUsers();
    }

    public List<UserDto> getUsersByPermissionGroup() {
        return getAllUsers();
    }

    public List<NotificationGroupDto> getNotificationGroups() {
        return null;
    }


    public void createPermissionGroup(PermissionGroupDto permissionGroup) {
        permissionGroupRepository.save(permissionGroup);
    }

    public List<PermissionGroupDto> getPermissionGroups() {
        return permissionGroupRepository.findAll();
    }

    public PermissionGroupDto getPermissionGroupById(String id) {
        return permissionGroupRepository.findById(id).get();
    }
}
