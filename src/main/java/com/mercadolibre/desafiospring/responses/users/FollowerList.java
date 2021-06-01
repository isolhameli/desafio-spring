package com.mercadolibre.desafiospring.responses.users;

import java.util.List;

public class FollowerList extends FollowList{

    private List<UserResponse> followers;

    public FollowerList(Integer userId, String userName, List<UserResponse> followers) {
        super(userId, userName);
        this.followers = followers;
    }

    public List<UserResponse> getFollowers() {
        return followers;
    }

    public void setFollowers(List<UserResponse> followers) {
        this.followers = followers;
    }
}
