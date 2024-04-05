package com.fto.web;


import com.fto.exception.UserNotFoundException;
import com.fto.exception.UserNotUniqueException;
import com.fto.model.AppException;
import com.fto.model.dto.FamilyErrorDto;
import com.fto.model.dto.UserErrorDto;
import jakarta.validation.ConstraintDefinitionException;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    public GlobalExceptionHandler() {
    }
    @ExceptionHandler(ConstraintDefinitionException.class)
    public ResponseEntity<Object> handleConstraintDefinitionException(
            ConstraintDefinitionException ex, WebRequest request) {
      String errorMessage = "UniqueFamilyName: " + ex.getMessage()+ex.getLocalizedMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }



    @ExceptionHandler(UserNotUniqueException.class)
    public ResponseEntity<UserErrorDto> onUsernameNotUnique(UserNotUniqueException unue) {
        UserErrorDto userErrorDto = new UserErrorDto(unue.getUsername(), "Username is already exist!");

        return
                ResponseEntity.status(HttpStatus.CONFLICT).body(userErrorDto);
    }
//    @ExceptionHandler(FamilyNameNotUniqueException.class)
//    public ResponseEntity<FamilyErrorDto> onFamilyNameNotUnique(FamilyNameNotUniqueException fnue) {
//        FamilyErrorDto familyErrorDto = new FamilyErrorDto(fnue.getFamilyName(), "Family name is already exist!");
//
//        return
//                ResponseEntity.status(HttpStatus.CONFLICT).body(familyErrorDto);
//    }
//

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserErrorDto> onUserNotFound(UserNotFoundException unfe) {
        UserErrorDto userErrorDto = new UserErrorDto(Long.toString(unfe.getId()), "User not found!");

        return
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(userErrorDto);
    }



    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            @NotNull HttpHeaders headers,
            @NotNull HttpStatusCode status, WebRequest request) {
        AppException apiError = new AppException(
                request.getLocale().getLanguage(),
                HttpStatus.CONFLICT,
                ex.getLocalizedMessage(),
                getValidationErrors(ex.getBindingResult()));

        return handleExceptionInternal(ex, apiError, headers, status, request);

    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<?> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("File too large!");
    }

    private Map<String, String> getValidationErrors(BindingResult bindingResult) {
        Map<String, String> fieldErrors = new HashMap<>();

        bindingResult.getFieldErrors()
                .forEach(fieldError -> fieldErrors.put(fieldError.getField(),
                        fieldError.getDefaultMessage()));

        bindingResult.getGlobalErrors()
                .forEach(objectError -> fieldErrors.put(objectError.getObjectName(),
                        objectError.getDefaultMessage()));

        return fieldErrors;
    }


}
