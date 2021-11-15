package com.cavityeye.backend.user.dto;
import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
public class PermissionGroupDto {

    @Id
    private String id;

    private String name;

    private String[] permissions;

}
