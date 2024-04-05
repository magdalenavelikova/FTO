package com.fto.validation;

import com.fto.model.AppUserDetails;
import com.fto.repository.FamilyRepository;
import com.fto.validation.annotation.UniqueFamilyName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.security.core.context.SecurityContextHolder;


public class UniqueFamilyNameValidator implements ConstraintValidator<UniqueFamilyName, String> {
    private final FamilyRepository familyRepository;

    public UniqueFamilyNameValidator(FamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        AppUserDetails user = (AppUserDetails) SecurityContextHolder.
                getContext().
                getAuthentication().getPrincipal();
        return familyRepository.findByNameAndUserEmail(value, user.getUsername()).isEmpty();

    }
}
