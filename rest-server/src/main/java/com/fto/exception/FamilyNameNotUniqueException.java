package com.fto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "FamilyName is not unique.")
public class FamilyNameNotUniqueException extends RuntimeException {

    private final String familyName;

    public FamilyNameNotUniqueException(String familyName) {
        super("There is already a registered family with this name " + familyName + "!");
        this.familyName = familyName;
    }

    public String getFamilyName() {
        return familyName;
    }
}
