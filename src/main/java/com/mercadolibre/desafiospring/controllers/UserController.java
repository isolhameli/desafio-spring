package com.mercadolibre.desafiospring.controllers;


import com.mercadolibre.desafiospring.models.Seller;
import com.mercadolibre.desafiospring.models.User;
import com.mercadolibre.desafiospring.requests.CreateUserRequest;
import com.mercadolibre.desafiospring.responses.SellerFollowersCount;
import com.mercadolibre.desafiospring.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value ="/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value="/create")
    ResponseEntity create(@RequestBody CreateUserRequest createUserRequest){
       Integer id = userService.create(createUserRequest);
       String innerPath = String.format(createUserRequest.isSeller() ? "/users/%d/followers/count" : "/users/%d/followed/list", id);
       System.out.println(createUserRequest.isSeller());
       return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentContextPath().path(innerPath).buildAndExpand(id).toUri()).build();
    }

    @PostMapping(value = "/{userId}/follow/{userIdToFollow}")
    ResponseEntity follow(@PathVariable Integer userId, @PathVariable Integer userIdToFollow){
        userService.follow(userId, userIdToFollow);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{userId}/followers/list")
    ResponseEntity<SellerFollowersCount> getFollowers(@PathVariable Integer userId){
        Seller user = (Seller) userService.getFollowers(userId);
        SellerFollowersCount sellerFollowersCount = new SellerFollowersCount(userId,user.getUserName(),user.getFollowerCount());
        return ResponseEntity.ok(sellerFollowersCount);
    }


}
