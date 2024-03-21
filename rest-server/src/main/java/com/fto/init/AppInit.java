package com.fto.init;

import com.fto.service.AgeCategoryService;
import com.fto.service.FamilyRoleService;
import com.fto.service.UserRoleService;
import com.fto.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInit implements CommandLineRunner {
    private final FamilyRoleService familyRoleService;
    private final UserRoleService userRoleService;
    private final AgeCategoryService ageCategoryService;
    private final UserService userService;


    public AppInit(FamilyRoleService familyRoleService, UserRoleService userRoleService, AgeCategoryService ageCategoryService, UserService userService) {
        this.familyRoleService = familyRoleService;
        this.userRoleService = userRoleService;
        this.ageCategoryService = ageCategoryService;
        this.userService = userService;
    }


    @Override
    public void run(String... args)  {
        familyRoleService.init();
        userRoleService.init();
        ageCategoryService.init();
        userService.init();
    }
}
