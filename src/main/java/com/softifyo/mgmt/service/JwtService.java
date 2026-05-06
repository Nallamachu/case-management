package com.softifyo.mgmt.service;

import com.softifyo.mgmt.dto.RefreshToken;
import com.softifyo.mgmt.entity.User;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.function.Function;

public interface JwtService {
    String extractUsername(String token);
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
    String generateToken(UserDetails userDetails);
    RefreshToken generateRefreshToken(User user);
    boolean isTokenValid(String token, UserDetails userDetails);
}
