package com.fto.model.dto;


import com.fto.validation.annotation.FieldMatch;
import com.fto.validation.annotation.UniqueUserEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@FieldMatch(first = "password",
        second = "confirmPassword",
        message = "Passwords do not match!")
public class RegisterUserDto {
    @NotEmpty(message = "User email should be provided.")
    @Email(message = "Please enter valid email.")
    @UniqueUserEmail(message = "There is already a registered user with this email address.")
    private String email;
    @NotEmpty
    @Size(min = 5, max = 20, message = "Please enter between 5 and 20 characters.")
    private String password;
    @NotEmpty
    @Size(min = 5, message = "Please enter between 5 and 20 characters.")
    private String confirmPassword;
    @NotEmpty
    @Size(min = 2, max = 60, message = "Please enter between 2 and 60 characters.")
    private String name;

    public RegisterUserDto() {

    }
    public String getEmail() {
        return email;
    }

    public RegisterUserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterUserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public RegisterUserDto setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getName() {
        return name;
    }

    public RegisterUserDto setName(String name) {
        this.name = name;
        return this;
    }
}
