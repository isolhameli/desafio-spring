package com.mercadolibre.desafiospring.controllers;


import com.mercadolibre.desafiospring.models.Seller;
import com.mercadolibre.desafiospring.models.User;
import com.mercadolibre.desafiospring.requests.UserRequest;
import com.mercadolibre.desafiospring.responses.users.*;
import com.mercadolibre.desafiospring.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value ="/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value="/create")
    ResponseEntity create(@RequestBody @Valid UserRequest userRequest){
        User user = userService.create(userRequest);
        String innerPath = String.format(userRequest.isSeller() ? "/users/%d/followers/count" : "/users/%d/followed/list", user.getId());

        return ResponseEntity.status(HttpStatus.OK)
               .location(ServletUriComponentsBuilder.fromCurrentContextPath().path(innerPath).buildAndExpand(user.getId()).toUri())
               .build();
    }

    @PostMapping(value = "/{userId}/follow/{userIdToFollow}")
    ResponseEntity follow(@PathVariable Integer userId, @PathVariable Integer userIdToFollow){
        userService.follow(userId, userIdToFollow);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{userId}/followers/count")
    ResponseEntity<SellerFollowersCount> getFollowersCount(@PathVariable Integer userId) {

        Seller user = (Seller) userService.getSeller(userId);
        Integer followerCount = userService.getFollowerCount(userId);
        SellerFollowersCount sellerFollowersCount = new SellerFollowersCount(userId, user.getUserName(), followerCount);
        return ResponseEntity.ok(sellerFollowersCount);
    }


    @GetMapping(value = "/{userId}/followers/list")
    ResponseEntity<FollowList> getFollowedList(@PathVariable Integer userId, @RequestParam(defaultValue = "asc") String order){
        FollowList followList = userService.getFollowers(userId, order);
        return ResponseEntity.ok(followList);
    }

    @GetMapping(value = "/{userId}/followed/list")
    ResponseEntity<FollowList> getFollowersList(@PathVariable Integer userId, @RequestParam(defaultValue = "asc") String order){
        FollowList followList = userService.getFollowed(userId, order);
        return ResponseEntity.ok(followList);
    }

    @PostMapping(value = "/{userId}/unfollow/{userIdToUnfollow}")
    ResponseEntity<FollowList> getFollowedList(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow){
        userService.unfollow(userId, userIdToUnfollow);
        return ResponseEntity.ok().build();
    }


}
