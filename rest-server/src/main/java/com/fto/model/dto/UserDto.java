package com.fto.model.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDto {
    private Long id;
    private String email;
    private String name;

    private String created;
    private List<UserRoleDto> roles = new ArrayList<>();

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public UserDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getCreated() {
        return created;
    }

    public UserDto setCreated(String created) {
        this.created = created;
        return this;
    }

    public List<UserRoleDto> getRoles() {
        return roles;
    }

    public UserDto setRoles(List<UserRoleDto> roles) {
        this.roles = roles;
        return this;
    }
}
