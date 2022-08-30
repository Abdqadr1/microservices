package com.qadr.reactiveweb.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Date;
import java.util.stream.Collectors;

public class JWTUtil {
    private static final Algorithm algorithm = Algorithm.HMAC256("my_secret_key".getBytes());
    public static String createAccessToken (Authentication auth, String path){
        return JWT.create()
                .withSubject((String) auth.getPrincipal())
                .withIssuer(path)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .withClaim("roles",
                        auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }

    public static String createRefreshToken (Authentication auth){
        String token = JWT.create()
                .withSubject((String) auth.getPrincipal())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 60 * 1000))
                .withClaim("roles",
                        auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
        return token;
    }

    public static DecodedJWT verifyToken(String token){
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }


}
