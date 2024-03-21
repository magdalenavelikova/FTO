package com.fto.service;


import com.fto.model.AppUserDetails;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUserName(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

    String generateToken(AppUserDetails userDetails);

    Boolean canTokenBeRefreshed(String token);
}
