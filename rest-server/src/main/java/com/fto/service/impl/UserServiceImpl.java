package com.fto.service.impl;

import com.fto.model.entity.UserEntity;
import com.fto.model.entity.UserRoleEntity;
import com.fto.model.enums.UserRoleEnum;
import com.fto.repository.UserRepository;
import com.fto.repository.UserRoleRepository;
import com.fto.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class UserServiceImpl implements UserService {

    @Value("${app.admin.password}")
    public String adminPass;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }


    @Override
    public void init() {
        if (userRepository.count() == 0) {
            UserRoleEntity roleAdmin = userRoleRepository.findUserRoleEntitiesByRole(UserRoleEnum.ADMIN).orElse(null);
            UserEntity admin = new UserEntity();

            admin.setEmail("magdalenal.velikova@gmail.com");
            String pass = passwordEncoder.encode(adminPass);
            admin.setPassword(pass);
            admin.setEnabled(true);
            admin.setName("Magdalena Velikova");

            admin.setCreated(LocalDateTime.now());
            admin.addRole(roleAdmin);
            userRepository.save(admin);
            userRepository.save(admin);
        }
    }
}
