package com.softifyo.mgmt.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class RefreshToken {
    private int id;
    private String userId;
    private String token;
    private int refreshCount;
    private Date expiryDate;

    public RefreshToken() {

    }

    public RefreshToken(int id, String userId, String token, int refreshCount, Date expiryDate) {
        this.id = id;
        this.userId = userId;
        this.token = token;
        this.refreshCount = refreshCount;
        this.expiryDate = expiryDate;
    }

}

