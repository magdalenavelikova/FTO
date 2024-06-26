package com.fto.web;

import com.fto.model.AppUserDetails;
import com.fto.model.dto.FamilyDto;
import com.fto.model.dto.FamilyEditDto;
import com.fto.model.dto.FamilyMemberDto;
import com.fto.model.dto.FamilyViewDto;
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
    public ResponseEntity<FamilyViewDto> create(@RequestBody @Valid FamilyDto family,
                                                @AuthenticationPrincipal AppUserDetails user) {
        FamilyViewDto saved = familyService.createFamily(family, user);
        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(saved);
    }
    @PostMapping("/member/add")
    @PreAuthorize("hasRole('ADMIN') || hasRole('FAMILY_MODERATOR')")
    public ResponseEntity<FamilyViewDto> createMember(@RequestBody @Valid FamilyMemberDto member,
                                                @AuthenticationPrincipal AppUserDetails user) {
        FamilyViewDto saved = familyService.addMember(member, user);
        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(saved);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')|| hasRole('FAMILY_MODERATOR')")
    public ResponseEntity<?> deleteFamily(@PathVariable Long id, @AuthenticationPrincipal AppUserDetails user) {
        familyService.deleteFamily(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')or hasRole('FAMILY_MODERATOR')")
    public ResponseEntity<FamilyViewDto> editLink(@RequestBody @Valid FamilyEditDto family, @PathVariable Long id, @AuthenticationPrincipal AppUserDetails user) {
        return ResponseEntity.ok()
                .body(familyService.editFamily(id, family));


    }
    @GetMapping
    public ResponseEntity<List<FamilyViewDto>> getAll(@AuthenticationPrincipal AppUserDetails user) {
        List<FamilyViewDto> families = familyService.getAll(user);
        return ResponseEntity.
                status(HttpStatus.FOUND).
                body(families);
    }


}