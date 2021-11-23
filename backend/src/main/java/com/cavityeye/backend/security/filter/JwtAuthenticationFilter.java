package com.cavityeye.backend.security.filter;

import com.cavityeye.backend.security.SecurityConstants;
import com.cavityeye.backend.security.dto.RefreshTokenDto;
import com.cavityeye.backend.security.service.RefreshTokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final RefreshTokenService refreshTokenService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, RefreshTokenService refreshTokenService) {

        this.authenticationManager = authenticationManager;
        this.refreshTokenService = refreshTokenService;

        setFilterProcessesUrl(SecurityConstants.AUTH_LOGIN_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication authentication) {
        UserDetails user = ((UserDetails) authentication.getPrincipal());

        List<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        byte[] signingKey = SecurityConstants.JWT_SECRET.getBytes();

        String token = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
                .setIssuer(SecurityConstants.TOKEN_ISSUER)
                .setAudience(SecurityConstants.TOKEN_AUDIENCE)
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 864000000))
                .claim("rol", roles)
                .compact();

        var refreshToken = new RefreshTokenDto();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setUserName(user.getUsername());
        refreshToken.setExpiryDate(Instant.now().plusMillis(864000000));

        refreshTokenService.saveRefreshToken(refreshToken);

        response.addHeader(SecurityConstants.TOKEN_HEADER, SecurityConstants.TOKEN_PREFIX + token);
        response.addHeader(SecurityConstants.TOKEN_HEADER_REFRESH, refreshToken.getToken());
        response.addHeader("username", user.getUsername());
    }
}

