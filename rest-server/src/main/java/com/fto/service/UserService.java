package com.fto.service;

import com.fto.model.AppUserDetails;
import com.fto.model.dto.IdTokenRequestDto;
import com.fto.model.dto.UserDto;

public interface UserService {
    void init();


    AppUserDetails login(String username, String password);

    UserDto getUser(Long id);

    AppUserDetails loginOAuthGoogle(IdTokenRequestDto requestBody);
}
