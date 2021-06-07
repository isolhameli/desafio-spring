package com.mercadolibre.desafiospring.services;

import com.mercadolibre.desafiospring.models.Seller;
import com.mercadolibre.desafiospring.models.User;
import com.mercadolibre.desafiospring.requests.UserRequest;
import com.mercadolibre.desafiospring.responses.users.FollowList;
import com.mercadolibre.desafiospring.responses.users.SellerFollowersCount;
import com.mercadolibre.desafiospring.responses.users.UserCreateResponse;

public interface UserService {
    UserCreateResponse create(UserRequest userRequest);

    void follow(Integer userId, Integer userIdToFollow);

    User update(User user);

    Seller getSeller(Integer userId);

    SellerFollowersCount getFollowerCountResponse(Integer userId);

    User getUser(Integer userId);

    boolean checkUserExistence(Integer userId);

    void unfollow(Integer userId, Integer userIdToUnfollow);

    FollowList getFollowers(Integer userId, String order);

    FollowList getFollowed(Integer userId, String order);
}
