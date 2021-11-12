package com.cavityeye.backend.user.dto;
import lombok.Data;


@Data
public class NotificationGroupDto {
    private String name;
    private String[] users; 

}
