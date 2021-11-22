package com.cavityeye.backend.user.service;

import com.cavityeye.backend.user.dto.NotificationGroupDto;
import com.cavityeye.backend.user.dto.PermissionDto;
import com.cavityeye.backend.user.dto.PermissionGroupDto;
import com.cavityeye.backend.user.dto.UserDto;
import com.cavityeye.backend.user.repository.NotificationGroupRepository;
import com.cavityeye.backend.user.repository.PermissionGroupRepository;
import com.cavityeye.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public void deleteNotificationGroup(String id) {
        notificationGroupRepository.delete(notificationGroupRepository.findById(id).get());
    }

    public NotificationGroupDto updateNotificationGroup(NotificationGroupDto notificationGroup) {
        return notificationGroupRepository.save(notificationGroup);
    }

    public List<PermissionDto> getPermissions() {
        var permissions = new ArrayList<PermissionDto>();

        permissions.add(new PermissionDto("Set first sampling, reference, limit", "setFirstSampRefLimit"));
        permissions.add(new PermissionDto("Set ref default", "setRefDefault"));
        permissions.add(new PermissionDto("Import reference by hand", "importRefByHand"));
        permissions.add(new PermissionDto("Set sampling time", "setSampTime"));
        permissions.add(new PermissionDto("Create, edit, reference", "createEditRef"));
        permissions.add(new PermissionDto("Edit Pfill, Tcool", "editPfillTcool"));
        permissions.add(new PermissionDto("Edit evaluation value settings", "editEvaluationValueSettings"));
        permissions.add(new PermissionDto("Edit limits", "editLimits"));
        permissions.add(new PermissionDto("Turn sensor on/off", "turnSensorsOnOff"));
        permissions.add(new PermissionDto("Enable/disable cycle stop", "enableDisableCycleStop"));
        permissions.add(new PermissionDto("Edit cycle stop condition", "editCycleStopCond"));
        permissions.add(new PermissionDto("Enable/disable switchover", "enableDisableSwitchover"));
        permissions.add(new PermissionDto("Edit switchover condition settings", "editSwitchoverCondSettings"));
        permissions.add(new PermissionDto("Enable/disable partial select", "enableDisablePartialSelect"));
        permissions.add(new PermissionDto("Edit partial select", "editPartialSelect"));
        permissions.add(new PermissionDto("Edit selector signal length", "editSelectorSignalLength"));
        permissions.add(new PermissionDto("Enable/disable surveillance", "enableDisableSurveillance"));
        permissions.add(new PermissionDto("Edit mold manager settings", "editMoldManagerSettings"));
        permissions.add(new PermissionDto("Manage users", "manageUsers"));
        permissions.add(new PermissionDto("Edit/set IMM", "editSetIMM"));
        permissions.add(new PermissionDto("Update software", "updateSoftware"));
        permissions.add(new PermissionDto("Edit network settings", "editNetworkSettings"));
        permissions.add(new PermissionDto("Edit device default settings", "editDeviceDefaultSettings"));

        return permissions;
    }
}
