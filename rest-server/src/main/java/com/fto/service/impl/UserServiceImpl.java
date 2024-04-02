package com.fto.service.impl;

import com.fto.exception.UserNotFoundException;
import com.fto.model.AppUserDetails;
import com.fto.model.dto.IdTokenRequestDto;
import com.fto.model.dto.RegisterUserDto;
import com.fto.model.dto.UserDto;
import com.fto.model.entity.UserEntity;
import com.fto.model.entity.UserRoleEntity;
import com.fto.model.enums.UserRoleEnum;
import com.fto.model.mapper.UserMapper;
import com.fto.repository.UserRepository;
import com.fto.repository.UserRoleRepository;
import com.fto.service.UserService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Value("${app.admin.password}")
    public String adminPass;
    //    @Value("${app.google.client-id}")
//    public String clientId;
//    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
//    public String clientSecret;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final GoogleIdTokenVerifier verifier;

    public UserServiceImpl(@Value("${app.google.client-id}") String clientId, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService, UserRepository userRepository, UserRoleRepository userRoleRepository, UserMapper userMapper, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
        NetHttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();
        verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList(clientId))
                .build();
    }


    @Override
    public AppUserDetails login(String username, String password) {
        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        username,
                        password));

        SecurityContextHolder.
                getContext().
                setAuthentication(auth);
        return (AppUserDetails) auth.getPrincipal();

    }

    @Override
    public UserDto getUser(Long id) {
        return userMapper.userEntityToUserDto(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id)));
    }

    @Override
    public AppUserDetails loginOAuthGoogle(IdTokenRequestDto requestBody) {
        UserEntity user = verifyIDToken(requestBody.getIdToken());
        if (user == null) {
            throw new IllegalArgumentException();
        }
        user = createOrUpdateUser(user);
        return this.login(user.getEmail());

    }

    @Override
    public UserDto registerNewUserAccount(RegisterUserDto registerUserDto, ServletWebRequest request) {
        UserEntity userEntity = userMapper.userRegisterDtoToUserEntity(registerUserDto);
        String rowPassword = userEntity.getPassword();
        String password = passwordEncoder.encode(rowPassword);
        userEntity.setPassword(password);
        userEntity.setCreated(LocalDateTime.now());
        Optional<UserRoleEntity> moderator = userRoleRepository.findUserRoleEntitiesByRole(UserRoleEnum.FAMILY_MODERATOR);
        moderator.ifPresent(userEntity::addRole);
        userEntity.setCreated(LocalDateTime.now());
        userEntity.setEnabled(true);
        return userMapper.userEntityToUserDto(userRepository.save(userEntity));


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

        }
    }

    private UserEntity verifyIDToken(String idToken) {
        try {

            Collection<String> audience = verifier.getAudience();
            GoogleIdToken idTokenObj = verifier.verify(idToken);
            if (idTokenObj == null) {
                return null;
            }
            GoogleIdToken.Payload payload = idTokenObj.getPayload();
            String firstName = (String) payload.get("given_name");
            String lastName = (String) payload.get("family_name");
            String email = payload.getEmail();
            String pictureUrl = (String) payload.get("picture");

            return new UserEntity(email, firstName + " " + lastName, pictureUrl);
        } catch (GeneralSecurityException | IOException e) {
            return null;
        }
    }

    private UserEntity createOrUpdateUser(UserEntity user) {
        UserEntity existingUser = userRepository.findByEmail(user.getEmail()).orElse(null);
        if (existingUser == null) {
            user.setPassword(null);
            user.setEnabled(true);
            user.setCreated(LocalDateTime.now());
            user.setRoles(userRoleRepository.findAll()
                    .stream()
                    .filter(r -> r.getRole() != (UserRoleEnum.ADMIN))
                    .collect(Collectors.toList()));
            userRepository.save(user);
            return user;
        }
        existingUser.setName(user.getName());

        existingUser.setPictureUrl(user.getPictureUrl());
        userRepository.save(existingUser);
        return existingUser;
    }

    private AppUserDetails login(String userName) {
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(userName);

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.
                getContext().
                setAuthentication(auth);
        return (AppUserDetails) auth.getPrincipal();
    }

}
