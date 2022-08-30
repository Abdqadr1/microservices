package com.qadr.reactiveweb.controller;

import com.qadr.reactiveweb.error.CustomException;
import com.qadr.reactiveweb.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailService userDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public Mono<Map<String, String>> login (@RequestParam("username") String username,
                                                      @RequestParam("password") String password){
        if(username.isBlank() || password.isBlank())
            throw new CustomException(HttpStatus.BAD_REQUEST,"Enter a valid username or password");

        return userDetailService.findByUsername(username)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "Username does not exist")))
                .filter(details -> passwordEncoder.matches(password, details.getPassword()))
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "Incorrect password")))
                .subscribeOn(Schedulers.parallel())
                .map(userDetails -> {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
                    Authentication auth = authenticationManager.authenticate(authenticationToken).block();
                    Map<String, String> tokens = new HashMap<>();
                    tokens.put("access_token", JWTUtil.createAccessToken(auth, "/login"));
                    tokens.put("refresh_token", JWTUtil.createRefreshToken(auth));
                    return  tokens;
                });
    }

}

