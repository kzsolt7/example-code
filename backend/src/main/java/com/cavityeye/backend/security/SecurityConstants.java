package com.cavityeye.backend.security;

public class SecurityConstants {
    public static final String USER_ROLE =  "USER";
    public static final String ADMIN_ROLE =  "ADMIN";

    public static final String AUTH_LOGIN_URL = "/api/authenticate";

    // Signing key for HS512 algorithm
    public static final String JWT_SECRET = "n2r5u8x/A%D*G-KjPdSgVkYf3t6v9y$B&E(H+MbQeTtWmZq4t7w!p%C*F-J@NcRg";

    // JWT token defaults
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_HEADER_REFRESH = "Refresh";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "secure-devtools-api";
    public static final String TOKEN_AUDIENCE = "secure-devtools-app";

    public static final String TOKEN_USERNAME = "username";
    public static final String TOKEN_ROLE = "role";

    private SecurityConstants() { }
}
