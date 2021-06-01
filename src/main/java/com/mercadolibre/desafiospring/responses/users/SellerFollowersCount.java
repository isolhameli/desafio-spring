package com.mercadolibre.desafiospring.responses.users;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SellerFollowersCount {

    private Integer userId;
    private String userName;
    private Integer followersCount;

    public SellerFollowersCount() {
    }

    public SellerFollowersCount(Integer userId, String userName, Integer followersCount) {
        this.userId = userId;
        this.userName = userName;
        this.followersCount = followersCount;
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

    @JsonProperty("followers_count")
    public Integer getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }
}
