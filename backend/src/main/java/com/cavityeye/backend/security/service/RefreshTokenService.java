package com.cavityeye.backend.security.service;

import com.cavityeye.backend.security.SecurityConstants;
import com.cavityeye.backend.user.repository.UserRepository;
import com.cavityeye.backend.user.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final UserRepository userRepository;
    private final UserService userService;

    public ResponseEntity<String> generateNewAccessToken(String token) {

        HttpHeaders headers = new HttpHeaders();

        byte[] signingKey = SecurityConstants.JWT_SECRET.getBytes();

        Jws<Claims> parsedToken = Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token);

        long exp = Long.parseLong(parsedToken.getBody().get("exp").toString());
        var user = userRepository.findByUserName(parsedToken.getBody().get("sub").toString());

        if (user.isPresent() && exp > Instant.now().getEpochSecond()) {

            var userPermissionGruop = userService.UserPermission(user.get());

            var roles = Arrays.stream(userPermissionGruop).map(role -> "ROLE_" + role).toList();

            String newToken = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
                .setIssuer(SecurityConstants.TOKEN_ISSUER)
                .setAudience(SecurityConstants.TOKEN_AUDIENCE)
                .setSubject(parsedToken.getBody().get("sub").toString())
                .setExpiration(Date.from(Instant.now().plusMillis(1296000000L)))
                .claim("rol", roles)
                .compact();

            String refresToken = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", "refresh")
                .setIssuer(SecurityConstants.TOKEN_ISSUER)
                .setAudience(SecurityConstants.TOKEN_AUDIENCE)
                .setSubject(parsedToken.getBody().get("sub").toString())
                .setExpiration(Date.from(Instant.now().plusMillis(2592000000L)))
                .compact();

            headers.add("Authorization", SecurityConstants.TOKEN_PREFIX + newToken);
            headers.add("Refresh", refresToken);
            headers.add("username", user.get().getUserName());

            return ResponseEntity.ok().headers(headers).body("");

        }

        return null;
    }

}
