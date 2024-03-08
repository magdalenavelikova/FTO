package com.fto.model.entity;

import com.fto.model.enums.UsersRoleEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.IdGeneratorType;

@Entity
@Table(name = "user_roles")
public class UserRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private UsersRoleEnum role;

    public Long getId() {
        return id;
    }

    public UserRoleEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public UsersRoleEnum getRole() {
        return role;
    }

    public UserRoleEntity setRole(UsersRoleEnum role) {
        this.role = role;
        return this;
    }
}
