package com.softifyo.mgmt.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.softifyo.mgmt.constants.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

public class AuthResponse {

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class TokenPair {
        private String accessToken;
        private String refreshToken;
        private String tokenType;
        private long expiresIn;
        private UserInfo user;

        public static TokenPair of(String accessToken, String refreshToken, long expiresIn, UserInfo user) {
            return TokenPair.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .tokenType("Bearer")
                    .expiresIn(expiresIn)
                    .user(user)
                    .build();
        }
    }

    @Data
    @Builder
    public static class UserInfo {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String username;
        private Role role;
        private LocalDateTime createdAt;
    }
}
