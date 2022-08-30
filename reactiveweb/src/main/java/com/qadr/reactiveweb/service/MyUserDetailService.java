package com.qadr.reactiveweb.service;

import com.qadr.reactiveweb.model.CustomerDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class MyUserDetailService implements ReactiveUserDetailsService {

    @Autowired
    private CustomerService customerService;


    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return customerService.findByUsername(username)
                .map(CustomerDetails::new);
    }
}
