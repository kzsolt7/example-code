package com.cavityeye.backend.user.dto;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("user")
public class UserDto {

    private String userName;

    private String email;

    private String password;

    private String[] permissionGroups;

    private String[] permissions;

    private String state;

}
