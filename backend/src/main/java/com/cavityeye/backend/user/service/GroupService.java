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
public class GroupService {
    private final PermissionGroupRepository permissionGroupRepository;
    private final NotificationGroupRepository notificationGroupRepository;


    public List<NotificationGroupDto> getNotificationGroups() {
        return notificationGroupRepository.findAll();
    }


    public PermissionGroupDto createPermissionGroup(PermissionGroupDto permissionGroup) {
        return permissionGroupRepository.save(permissionGroup);
    }

    public List<PermissionGroupDto> getPermissionGroups() {
        return permissionGroupRepository.findAll();
    }

    public PermissionGroupDto getPermissionGroupById(String id) {
        return permissionGroupRepository.findById(id).get();
    }

    public NotificationGroupDto createNotificationGroup(NotificationGroupDto notificationGroup) {
        return notificationGroupRepository.save(notificationGroup);
    }

    public NotificationGroupDto getNotificationGroupById(String id) {
        return notificationGroupRepository.findById(id).get();
    }

    public PermissionGroupDto updatePermissionGroup(PermissionGroupDto permissionGroup) {
        return permissionGroupRepository.save(permissionGroup);
    }

    public void deletePermissionGroup(String id) {
        permissionGroupRepository.delete(permissionGroupRepository.findById(id).get());
    }
}
