package com.cavityeye.backend.user.dto;
import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
public class NotificationGroupDto {

    @Id
    private String id;

    private String name;

    private String[] users; 

}
