package com.mercadolibre.desafiospring.services;

import com.mercadolibre.desafiospring.models.Seller;
import com.mercadolibre.desafiospring.models.User;
import com.mercadolibre.desafiospring.requests.UserRequest;

public interface UserService {
    User create(UserRequest userRequest);

    void follow(Integer userId, Integer userIdToFollow);

    User update(User user);

    Seller getSeller(Integer userId);

    User getUser(Integer userId);
}
