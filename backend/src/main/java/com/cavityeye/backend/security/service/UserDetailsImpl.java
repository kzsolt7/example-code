package com.cavityeye.backend.security.service;

import com.cavityeye.backend.user.dto.UserDto;
import com.cavityeye.backend.user.repository.PermissionGroupRepository;
import com.cavityeye.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private final UserDto user;
    private final PermissionGroupRepository permissionGroupRepository;
    private final UserService userService;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> list = new ArrayList<>();

        Arrays.stream(user.getPermissionGroups()).forEach(group -> {
            Arrays.stream(permissionGroupRepository.findByName(group).get().getPermissions()).forEach(gr -> {
                list.add(new SimpleGrantedAuthority("ROLE_" + gr));
            });
        });

        Arrays.stream(user.getPermissions()).forEach(permission -> list.add(new SimpleGrantedAuthority("ROLE_" + permission)));

        return list;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

