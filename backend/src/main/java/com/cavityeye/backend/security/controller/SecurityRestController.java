package com.cavityeye.backend.security.controller;

import com.cavityeye.backend.security.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SecurityRestController {

    private final RefreshTokenService refreshTokenService;

    @PostMapping("resfreshtoken")
    public String resfreshToken(@RequestParam String resfreshtoken) {
        return refreshTokenService.generateNewAccessToken(resfreshtoken);
    }
}
