package com.fto.service;

import com.fto.model.dto.UserDto;

public interface UserService {
  void init();
  UserDto getUser(String email);
}
