package com.qadr.reactiveweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

public class MyUserDetailService implements ReactiveUserDetailsService {

    @Autowired
    private CustomerService customerService;

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();
        return new MapReactiveUserDetailsService(user);
    }


    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return customerService.findByUsername(username)
                .cast(UserDetails.class);
    }
}
