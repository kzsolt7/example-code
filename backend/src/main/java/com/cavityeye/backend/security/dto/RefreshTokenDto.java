package com.cavityeye.backend.security.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document("refreshToken")
public class RefreshTokenDto {

    @Id
    private String id;

    private String userName;

    private String token;

    private Instant expiryDate;
}
