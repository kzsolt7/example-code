package com.cavityeye.backend.user.dto;
import lombok.Data;


@Data
public class PermissionGroupDto {
    private String name;
    private String[] permissions;

}
