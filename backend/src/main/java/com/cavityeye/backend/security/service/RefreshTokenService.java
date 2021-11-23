package com.cavityeye.backend.security.service;

import com.cavityeye.backend.security.SecurityConstants;
import com.cavityeye.backend.security.dto.RefreshTokenDto;
import com.cavityeye.backend.security.repository.RefreshTokenRepository;
import com.cavityeye.backend.user.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public void saveRefreshToken(RefreshTokenDto refreshTokenDto) {
        refreshTokenRepository.save(refreshTokenDto);
    }

    public String generateNewAccessToken(String token) {
        var refreshToken = getUserNameByRefreshToken(token);

        if (refreshToken.isPresent()) {
            return createRefreshToken(refreshToken.get());
        }

        return token;
    }

    private Optional<RefreshTokenDto> getUserNameByRefreshToken(String token) {
        return refreshTokenRepository.getUserByRefreshToken(token);
    }

    private String createRefreshToken(RefreshTokenDto refreshToken) {

        var user = userRepository.findByUserName(refreshToken.getUserName());

        if (user.isPresent()) {
            var userRoles = Arrays.stream(user.get().getPermissions()).toList();
            var roles = userRoles.stream()
                    .map(role -> "ROLE_" + role).toList();

            byte[] signingKey = SecurityConstants.JWT_SECRET.getBytes();

            String token = Jwts.builder()
                    .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                    .setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
                    .setIssuer(SecurityConstants.TOKEN_ISSUER)
                    .setAudience(SecurityConstants.TOKEN_AUDIENCE)
                    .setSubject(refreshToken.getUserName())
                    .setExpiration(new Date(System.currentTimeMillis() + 864000000))
                    .claim("rol", roles)
                    .compact();

            return token;
        }


//        var refreshToken = new RefreshTokenDto();
//        refreshToken.setToken(UUID.randomUUID().toString());
//        refreshToken.setUserName(username);
//        refreshToken.setExpiryDate(Instant.now().plusMillis(864000000));

        return null;
    }
}
