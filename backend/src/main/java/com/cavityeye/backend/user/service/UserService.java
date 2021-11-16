package com.cavityeye.backend.user.service;

import com.cavityeye.backend.user.dto.NotificationGroupDto;
import com.cavityeye.backend.user.dto.PermissionGroupDto;
import com.cavityeye.backend.user.dto.UserDto;
import com.cavityeye.backend.user.repository.NotificationGroupRepository;
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
    private final NotificationGroupRepository notificationGroupRepository;

    public UserDto saveUser(UserDto user) {
        return userRepository.save(user);
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

    public UserDto updateUser(UserDto user) {
        return userRepository.save(user);
    }
}
