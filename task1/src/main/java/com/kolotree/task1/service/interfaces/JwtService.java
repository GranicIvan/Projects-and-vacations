package com.kolotree.task1.service.interfaces;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public interface JwtService {

    String extractUsername(String token) ;

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    String generateToken(UserDetails userDetails);

    String generatetoken(Map<String, Objects> extraClaims, UserDetails userDetails);

    long getJwtExpirationTime();

    long getExpirationTime();

    boolean isTokenValid(String token, UserDetails userDetails);


    }
