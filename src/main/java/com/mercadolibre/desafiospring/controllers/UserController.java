package com.mercadolibre.desafiospring.controllers;


import com.mercadolibre.desafiospring.models.Seller;
import com.mercadolibre.desafiospring.models.User;
import com.mercadolibre.desafiospring.requests.UserRequest;
import com.mercadolibre.desafiospring.responses.*;
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
        SellerFollowersCount sellerFollowersCount = new SellerFollowersCount(userId, user.getUserName(), user.getFollowerCount());
        return ResponseEntity.ok(sellerFollowersCount);
    }

    @GetMapping(value = "/{userId}/followers/list")
    ResponseEntity<FollowList> getFollowersList(@PathVariable Integer userId){
        Seller seller = userService.getSeller(userId);
        List<UserResponse> usuarioResponseList= seller.getFollowerList().stream().
                map(el -> new UserResponse(el.getId(),el.getUserName()))
                .collect(Collectors.toList());
            FollowList followList = new FollowerList(userId,seller.getUserName(),usuarioResponseList);
            return ResponseEntity.ok(followList);
    }

    @GetMapping(value = "/{userId}/followed/list")
    ResponseEntity<FollowList> getFollowedList(@PathVariable Integer userId){
        User user = userService.getUser(userId);
        List<UserResponse> usuarioResponseList = user.getFollowingList().stream().
                map(el -> new UserResponse(el.getId(),el.getUserName()))
                .collect(Collectors.toList());
        FollowList followList = new FollowedList(userId,user.getUserName(),usuarioResponseList);
        return ResponseEntity.ok(followList);
    }


}
