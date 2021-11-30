package com.cavityeye.backend.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PermissionDto {

    private String name;

    private String value;
}
