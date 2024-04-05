package com.fto.validation;


import com.fto.model.entity.FamilyMemberEntity;
import com.fto.repository.FamilyMemberRepository;
import com.fto.validation.annotation.UniqueFamilyMemberName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class UniqueFamilyMemberNameValidator implements ConstraintValidator<UniqueFamilyMemberName, String> {
    private final FamilyMemberRepository familyMemberRepository;
    private String familyName;
    private String memberName;

    public UniqueFamilyMemberNameValidator(FamilyMemberRepository familyMemberRepository) {
        this.familyMemberRepository = familyMemberRepository;
    }

    @Override
    public void initialize(UniqueFamilyMemberName constraintAnnotation) {
        this.familyName = constraintAnnotation.familyName();
        this.memberName = constraintAnnotation.memberName();

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Optional<FamilyMemberEntity> byNameAndFamilyName = familyMemberRepository.findByNameAndFamily_Name(memberName, familyName);

        return familyMemberRepository.findByNameAndFamily_Name(memberName, familyName).isEmpty();
    }
}
