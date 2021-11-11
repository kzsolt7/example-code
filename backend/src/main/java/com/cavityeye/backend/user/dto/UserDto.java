package com.cavityeye.backend.user.dto;

import lombok.Data;

@Data
public class UserDto {

    private String userName;

    private String email;

    private String password;

    private String[] groups;

    private String state;

}
