package com.fto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Family was not found.")
public class FamilyNotFoundException extends RuntimeException {
    private Long id;
    private String name;

    public FamilyNotFoundException(Long id) {
        super("Family with ID " + id + " not found!");
        this.id = id;
    }

    public FamilyNotFoundException(String name) {
        super("Family with name " + name + " not found!");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }


}
