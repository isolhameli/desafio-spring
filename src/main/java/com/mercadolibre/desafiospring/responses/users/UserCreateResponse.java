package com.mercadolibre.desafiospring.responses.users;

import com.mercadolibre.desafiospring.models.User;

public class UserCreateResponse extends UserResponse{

    private boolean seller;

    public UserCreateResponse() {
    }

    public UserCreateResponse(Integer userId, String userName, boolean seller) {
        super(userId, userName);
        this.seller = seller;
    }

    public boolean isSeller() {
        return seller;
    }

    public void setSeller(boolean seller) {
        this.seller = seller;
    }
}
