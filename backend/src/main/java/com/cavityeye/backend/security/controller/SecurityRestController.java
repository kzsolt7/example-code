package com.cavityeye.backend.security.controller;

import com.cavityeye.backend.security.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SecurityRestController {

    private final RefreshTokenService refreshTokenService;

    @PostMapping("/refreshtoken")
    public ResponseEntity<String> resfreshToken(@RequestParam String refreshtoken) {
        return refreshTokenService.generateNewAccessToken(refreshtoken);
    }
}
