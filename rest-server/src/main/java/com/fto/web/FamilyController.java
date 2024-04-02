package com.fto.web;

import com.fto.model.AppUserDetails;
import com.fto.model.dto.FamilyDto;
import com.fto.service.FamilyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/family")
public class FamilyController {
    private final FamilyService familyService;


    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN') || hasRole('FAMILY_MODERATOR')")
    public ResponseEntity<FamilyDto> create(@RequestBody @Valid FamilyDto family,
                                            @AuthenticationPrincipal AppUserDetails user) {
        FamilyDto saved = familyService.createFamily(family, user);
        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(saved);
    }

    @GetMapping
    public ResponseEntity<List<FamilyDto>> getAll(@AuthenticationPrincipal AppUserDetails user) {
        List<FamilyDto> families = familyService.getAll(user);
        return ResponseEntity.
                status(HttpStatus.FOUND).
                body(families);
    }


}