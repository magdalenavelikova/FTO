package com.fto.service.impl;

import com.fto.model.AppUserDetails;
import com.fto.model.entity.UserEntity;
import com.fto.model.entity.UserRoleEntity;
import com.fto.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository
                .findByEmail(username)
                .map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid credential"));
    }

    private UserDetails map(UserEntity userEntity) {

        return new AppUserDetails(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getName(),
                userEntity.isEnabled(),
                userEntity.getRoles().stream().map(this::map).toList()
        );
    }

    private GrantedAuthority map(UserRoleEntity userRoleEntity) {

        return new SimpleGrantedAuthority("ROLE_" + userRoleEntity.getRole().name());
    }
}
