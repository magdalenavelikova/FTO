package com.fto.init;

import com.fto.service.AgeCategoryService;
import com.fto.service.FamilyRoleService;
import com.fto.service.UserRoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInit implements CommandLineRunner {
    private final FamilyRoleService familyRoleService;
    private final UserRoleService userRoleService;
    private  final AgeCategoryService ageCategoryService;

    public AppInit(FamilyRoleService familyRoleService, UserRoleService userRoleService, AgeCategoryService ageCategoryService) {
        this.familyRoleService = familyRoleService;
        this.userRoleService = userRoleService;
        this.ageCategoryService = ageCategoryService;
    }


    @Override
    public void run(String... args) throws Exception {
        familyRoleService.init();
        userRoleService.init();
        ageCategoryService.init();;
    }
}
