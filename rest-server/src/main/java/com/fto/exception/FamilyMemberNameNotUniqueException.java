package com.fto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "FamilyMember is not unique.")
public class FamilyMemberNameNotUniqueException extends RuntimeException {

    private final String name;

    public FamilyMemberNameNotUniqueException(String name) {
        super("There is already a registered member with name" + name + "in this family.!");
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
