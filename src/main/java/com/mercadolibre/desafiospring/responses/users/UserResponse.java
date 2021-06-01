package com.mercadolibre.desafiospring.responses.users;

import com.mercadolibre.desafiospring.models.User;

public class UserResponse {

    private Integer userId;
    private String userName;

    public UserResponse(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public UserResponse() {
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

    public static UserResponse fromModel(User user){
        return new UserResponse(user.getId(), user.getUserName());
    }
}
