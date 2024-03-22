package com.fto.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class AppUserDetails implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private String name;
    private boolean enabled;
    private final Collection<GrantedAuthority> authorities;

    public AppUserDetails(Long id, String username, String password, String name, boolean enabled, Collection<GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.enabled = enabled;
        this.authorities = authorities;
    }



    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
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
        return this.enabled;
    }

    public Long getId() {
        return id;
    }

    public AppUserDetails setId(Long id) {
        this.id = id;
        return this;
    }

    public AppUserDetails setUsername(String username) {
        this.username = username;
        return this;
    }

    public AppUserDetails setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public AppUserDetails setName(String name) {
        this.name = name;
        return this;
    }

    public AppUserDetails setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }
}
