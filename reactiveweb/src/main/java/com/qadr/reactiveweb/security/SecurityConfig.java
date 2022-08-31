package com.qadr.reactiveweb.security;

import com.qadr.reactiveweb.controller.AuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SecurityContextRepository securityContextRepository;

//    @Bean
//    public MapReactiveUserDetailsService userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("user")
//                .roles("USER")
//                .build();
//        return new MapReactiveUserDetailsService(user);
//    }


    @Bean
    SecurityWebFilterChain webHttpSecurity(ServerHttpSecurity http) {
        http
                .csrf().disable()
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange((exchanges) -> exchanges
                        .pathMatchers(HttpMethod.GET,"/").permitAll()
                        .pathMatchers(HttpMethod.DELETE, "/customer/**").hasAnyAuthority("ADMIN")
                        .pathMatchers(HttpMethod.POST, "/customer", "/login").permitAll()
                        .pathMatchers(HttpMethod.GET, "/customer", "/video/**").permitAll()
                        .pathMatchers("/delete/**", "/deleteall/**").hasAnyAuthority("DEV")
                        .anyExchange().authenticated()
                );
        return http.build();
    }
}
