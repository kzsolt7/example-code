package com.cavityeye.backend.user.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Document("user")
public class UserDto {

    @Id
    private String id;

    @Indexed(unique=true)
    @NotNull
    @NotBlank
    private String userName;

    private String email;

    @NotNull
    @NotBlank
    private String password;

    private String[] permissionGroups;

    private String[] permissions;

    private String state;
}
