package com.cavityeye.backend.security.service;

import com.cavityeye.backend.user.dto.UserDto;
import com.cavityeye.backend.user.repository.PermissionGroupRepository;
import com.cavityeye.backend.user.repository.UserRepository;
import com.cavityeye.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService  {

    public final UserRepository userRepository;
    private final UserService userService;
    private final PermissionGroupRepository permissionGroupRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var user = userRepository.findByUserName(username);

        if (user.isEmpty() || user.get().getState().equals("Inactive")) {
            throw new UsernameNotFoundException(username);
        }

        return new UserDetailsImpl(user.get(), permissionGroupRepository, userService);
    }

}
