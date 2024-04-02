package com.fto.service;

import com.fto.model.AppUserDetails;
import com.fto.model.dto.IdTokenRequestDto;
import com.fto.model.dto.RegisterUserDto;
import com.fto.model.dto.UserDto;
import org.springframework.web.context.request.ServletWebRequest;

public interface UserService {
    void init();


    AppUserDetails login(String username, String password);

    UserDto getUser(Long id);

    AppUserDetails loginOAuthGoogle(IdTokenRequestDto requestBody);

    UserDto registerNewUserAccount(RegisterUserDto registerUserDto, ServletWebRequest request);
}
