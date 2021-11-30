package com.cavityeye.backend.user.dto;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document("permissionGroup")
public class PermissionGroupDto {

    @Id
    private String id;

    @Indexed(unique=true)
    private String name;

    private String[] permissions;

    private String state;

}
