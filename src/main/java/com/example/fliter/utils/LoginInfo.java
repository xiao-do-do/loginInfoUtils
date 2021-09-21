package com.example.fliter.utils;

import java.io.Serializable;

public class LoginInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private String loginName;

    public LoginInfo() {
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
