package com.fto.init;

import com.fto.service.UserRoleService;
import com.fto.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInit implements CommandLineRunner {

    private final UserRoleService userRoleService;

    private final UserService userService;


    public AppInit(UserRoleService userRoleService, UserService userService) {
        this.userRoleService = userRoleService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        userRoleService.init();
        userService.init();

    }
}
