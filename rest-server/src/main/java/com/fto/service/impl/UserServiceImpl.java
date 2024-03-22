package com.fto.service.impl;

import com.fto.model.entity.UserEntity;
import com.fto.model.enums.UserRoleEnum;
import com.fto.model.mapper.UserMapper;
import com.fto.repository.UserRepository;
import com.fto.service.UserService;
import com.fto.model.dto.UserDto;
import com.fto.model.entity.UserRoleEntity;
import com.fto.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Value("${app.admin.password}")
    public String adminPass;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, UserRoleRepository userRoleRepository, UserMapper userMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userMapper = userMapper;
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

    @Override
    public UserDto getUser(String email) {
        Optional<UserEntity> byEmail = userRepository.findByEmail(email);
        UserDto userDto = userMapper.userEntityToUserDto(byEmail.get());
        return userDto;
    }
}
