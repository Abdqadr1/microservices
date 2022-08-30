package com.qadr.reactiveweb.security;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qadr.reactiveweb.controller.AuthenticationManager;
import com.qadr.reactiveweb.controller.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class SecurityContextRepository implements ServerSecurityContextRepository {

    private static final String TOKEN_PREFIX = "Bearer ";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Mono save(ServerWebExchange swe, SecurityContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Mono load(ServerWebExchange swe) {
        ServerHttpRequest request = swe.getRequest();
        System.out.println(request.getPath());
        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String accessToken = null;
        if (authHeader != null && authHeader.startsWith(TOKEN_PREFIX)) {
            accessToken = authHeader.replace(TOKEN_PREFIX, "");
        }else {
            log.warn("couldn't find bearer string, will ignore the header.");
        }
        if (accessToken != null) {
            try {
                DecodedJWT decodedJWT = JWTUtil.verifyToken(accessToken);
                String username = decodedJWT.getSubject();
                String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                List<SimpleGrantedAuthority> authorities = Arrays.stream(roles)
                        .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

                System.out.println(authorities);

                Authentication auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
                return this.authenticationManager.authenticate(auth).map(SecurityContextImpl::new);

            }catch (Exception e){
                Map<String, Object> ret = new HashMap<>();
                ret.put("message", e.getMessage());
                return Mono.just(ret);
            }
        } else {
            return Mono.empty();
        }
    }

}
