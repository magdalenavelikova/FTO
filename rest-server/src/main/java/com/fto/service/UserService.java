package com.fto.service;

import com.fto.model.AppUserDetails;
import com.fto.model.dto.UserDto;
import com.fto.service.impl.AppUserDetailsService;

public interface UserService {
  void init();


  AppUserDetails login(String username, String password);

    UserDto getUser(Long id);
}
