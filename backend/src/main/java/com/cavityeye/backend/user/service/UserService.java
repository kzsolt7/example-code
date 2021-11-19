package com.cavityeye.backend.user.service;

import com.cavityeye.backend.user.dto.NotificationGroupDto;
import com.cavityeye.backend.user.dto.PermissionGroupDto;
import com.cavityeye.backend.user.dto.UserDto;
import com.cavityeye.backend.user.repository.NotificationGroupRepository;
import com.cavityeye.backend.user.repository.PermissionGroupRepository;
import com.cavityeye.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PermissionGroupRepository permissionGroupRepository;

    public UserDto saveUser(UserDto user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    public UserDto getUserById(String id) {
        var user = userRepository.findById(id);
        user.get().setPermissions(UserPermission(user.get()));
        return user.get();
    }


    public List<UserDto> getAllUsers() {
        var users = userRepository.findAll();
        users.forEach(user -> user.setPermissions(UserPermission(user)));
        return users;
    }

    public List<UserDto> getUsersByNotificationGroup() {
        return getAllUsers();
    }

    public List<UserDto> getUsersByPermissionGroup(String groupName) {
        List<UserDto> users = new ArrayList<>();
        getAllUsers().forEach((user) -> {
            for (String permissionGroup : user.getPermissionGroups()) {
                if (Objects.equals(permissionGroup, groupName)) {
                    users.add(user);
                }
            }
        });
        return users;
    }

    public UserDto updateUser(UserDto user) {
        if (user.getPassword().equals("")) {
            user.setPassword(userRepository.findByUserName(user.getUserName()).get().getPassword());
        } else {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }

        return userRepository.save(user);
    }

    public void createAdminUser() {
        String[] permissions = {
                "setFirstSampRefLimit",
                "setRefDefault",
                "importRefByHand",
                "setSampTime",
                "createEditRef",
                "editPfillTcool",
                "editEvaluationValueSettings",
                "editLimits",
                "turnSensorsOnOff",
                "enableDisableCycleStop",
                "editCycleStopCond",
                "enableDisableSwitchover",
                "editSwitchoverCondSettings",
                "enableDisablePartialSelect",
                "editPartialSelect",
                "editSelectorSignalLength",
                "enableDisableSurveillance",
                "editMoldManagerSettings",
                "manageUsers",
                "editSetIMM",
                "updateSoftware",
                "editNetworkSettings",
                "editDeviceDefaultSettings"};
        String[] persmissionGroups = {"admin"};

        var user = new UserDto();
        var existUser = userRepository.findByUserName("ce");

        if (existUser.isEmpty()) {
            var permissionGroup = new PermissionGroupDto();
            permissionGroup.setName("admin");
            permissionGroup.setState("Active");
            permissionGroup.setPermissions(permissions);
            permissionGroupRepository.save(permissionGroup);

            user.setUserName("ce");
            user.setEmail("ce@cavityeye.com");
            user.setPermissionGroups(persmissionGroups);
            user.setPassword(new BCryptPasswordEncoder().encode("cesuper"));
            user.setPermissions(permissions);
            user.setState("Active");
            userRepository.save(user);
        }
    }

    private String[] UserPermission(UserDto user) {
        List<String> userPermissions = new ArrayList<>();

        Arrays.stream(user.getPermissions()).forEach(r -> userPermissions.add(r));

        Arrays.stream(user.getPermissionGroups()).forEach(permission -> {
            var groupPermission = permissionGroupRepository.findByName(permission).get().getPermissions();
            Arrays.stream(groupPermission).forEach(r -> userPermissions.add(r));
        });

        String[] stringArray = userPermissions.toArray(new String[0]);

        return stringArray;

    }

}
