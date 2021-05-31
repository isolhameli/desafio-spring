package com.mercadolibre.desafiospring.services;

import com.mercadolibre.desafiospring.models.User;
import com.mercadolibre.desafiospring.requests.CreateUserRequest;

public interface UserService {
    int create(CreateUserRequest createUserRequest);

    void follow(Integer userId, Integer userIdToFollow);

    User update(User user);

    User getFollowers(Integer userId);
}
