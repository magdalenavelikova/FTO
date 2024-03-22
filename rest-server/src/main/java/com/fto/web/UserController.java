package com.fto.web;

import com.fto.model.AppUserDetails;
import com.fto.model.dto.AuthRequest;
import com.fto.service.JwtService;
import com.fto.service.UserService;
import com.fto.service.impl.AppUserDetailsService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final JwtService jwtService;
    private final AppUserDetailsService userDetailService;
    private final UserService userService;

    public UserController(JwtService jwtService, AppUserDetailsService userDetailService, UserService userService) {
        this.jwtService = jwtService;
        this.userDetailService = userDetailService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        if(isValid(request)!=null) {
            AppUserDetails user = userService.login(request.getUsername(), request.getPassword());
            user.setPassword(null);
          return ResponseEntity
                  .ok()
                  .header(HttpHeaders.AUTHORIZATION,
                          jwtService.generateToken(user))
                  .body(user);
        }
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .build();
    }

    private UserDetails isValid(AuthRequest request) {
        return userDetailService.loadUserByUsername(request.getUsername());
    }
}
