package com.fto.model.entity;

import com.fto.model.enums.FamilyRoleEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "family_roles")
public class FamilyRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private FamilyRoleEnum familyRole;

    public Long getId() {
        return id;
    }

    public FamilyRoleEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public FamilyRoleEnum getFamilyRole() {
        return familyRole;
    }

    public FamilyRoleEntity setFamilyRole(FamilyRoleEnum familyRole) {
        this.familyRole = familyRole;
        return this;
    }
}
