package com.mercadolibre.desafiospring.responses;

import java.util.List;

public abstract class FollowList {

    private Integer userId;
    private String userName;

    public FollowList() {
    }

    public FollowList(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
