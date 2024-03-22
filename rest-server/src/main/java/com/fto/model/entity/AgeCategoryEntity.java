package com.fto.model.entity;

import com.fto.model.enums.AgeCategoryEnum;
import jakarta.persistence.*;

@Entity
@Table (name = "age_categories")
public class AgeCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private AgeCategoryEnum ageCategory;

    public AgeCategoryEntity() {
    }

    public Long getId() {
        return id;
    }

    public AgeCategoryEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public AgeCategoryEnum getAgeCategory() {
        return ageCategory;
    }

    public AgeCategoryEntity setAgeCategory(AgeCategoryEnum ageCategory) {
        this.ageCategory = ageCategory;
        return this;
    }
}
