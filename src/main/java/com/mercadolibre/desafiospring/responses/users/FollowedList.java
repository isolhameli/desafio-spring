package com.mercadolibre.desafiospring.responses.users;

import java.util.List;

public class FollowedList extends FollowList{

    private List<UserResponse> followed;

    public FollowedList(Integer userId, String userName, List<UserResponse> followed) {
        super(userId, userName);
        this.followed = followed;
    }

    public List<UserResponse> getFollowed() {
        return followed;
    }

    public void setFollowed(List<UserResponse> followers) {
        this.followed = followed;
    }
}
