package com.cavityeye.backend.user.service;

import com.cavityeye.backend.user.dto.PermissionGroupDto;
import com.cavityeye.backend.user.dto.UserDto;
import com.cavityeye.backend.user.repository.PermissionGroupRepository;
import com.cavityeye.backend.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoWriteException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PermissionGroupRepository permissionGroupRepository;
    private final GroupService groupService;

    public UserDto saveUser(UserDto user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        try {
            return userRepository.save(user);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    public UserDto getUserById(String id) {
        var user = userRepository.findById(id);
        user.get().setPermissions(UserPermission(user.get()));
        return user.get();
    }

    public UserDto getUserByUserName(String username){
        var user = userRepository.findByUserName(username);
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
        String[] permissions = new String[groupService.getPermissions().size()];
        for (int i = 0; i < groupService.getPermissions().size(); i++) {
            permissions[i] = groupService.getPermissions().get(i).getValue();
        }

        String[] permissionGroups = {"admin"};

        var user = new UserDto();
        var existUser = userRepository.findByUserName("ce");

        if (existUser.isEmpty()) {
            var permissionGroup = new PermissionGroupDto();
            permissionGroup.setName("admin");
            permissionGroup.setState("Active");
            permissionGroup.setPermissions(permissions);
            permissionGroupRepository.save(permissionGroup);

            user.setUserName("user");
            user.setEmail("user@devtools.hu");
            user.setPermissionGroups(permissionGroups);
            user.setPassword(new BCryptPasswordEncoder().encode("superuser"));
            user.setPermissions(permissions);
            user.setState("Active");
            userRepository.save(user);
        }
    }

    public String[] UserPermission(UserDto user) {
        List<String> userPermissions = new ArrayList<>();

        Arrays.stream(user.getPermissions()).forEach(r -> userPermissions.add(r));

        Arrays.stream(user.getPermissionGroups()).forEach(permission -> {
            var groupPermission = permissionGroupRepository.findByName(permission).get().getPermissions();
            Arrays.stream(groupPermission).forEach(r -> userPermissions.add(r));
        });

        String[] stringArray = userPermissions.toArray(new String[0]);

        return stringArray;

    }

    public void importUsers(MultipartFile file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        var fileContent = mapper.readTree(new String(file.getBytes()));
        var users = mapper.readValue(fileContent.toString(), UserDto[].class);
        var userList = Arrays.stream(users).toList();
        userList.forEach(r -> {
            try {
                userRepository.insert(r);
            } catch (Exception e) {
                log.info(e.getMessage());
            }
        });
    }

}
