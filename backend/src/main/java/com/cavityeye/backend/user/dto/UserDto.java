package com.cavityeye.backend.user.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("user")
public class UserDto {

    @Id
    private String id;

    @Indexed(unique=true)
    private String userName;

    private String email;

    private String password;

    private String[] permissionGroups;

    private String[] permissions;

    private String state;
}
