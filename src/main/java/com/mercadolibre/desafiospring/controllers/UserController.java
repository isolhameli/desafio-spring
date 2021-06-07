package com.mercadolibre.desafiospring.controllers;


import com.mercadolibre.desafiospring.models.Seller;
import com.mercadolibre.desafiospring.requests.UserRequest;
import com.mercadolibre.desafiospring.responses.users.FollowList;
import com.mercadolibre.desafiospring.responses.users.SellerFollowersCount;
import com.mercadolibre.desafiospring.responses.users.UserCreateResponse;
import com.mercadolibre.desafiospring.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value ="/users")
@Api(tags = "Users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 409, message = "Duplicate UserName")})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "US 0000 - Creates a new Client")
    @PostMapping(value="/create") // US 0000 (must create User before following or posting)
    ResponseEntity<UserCreateResponse> create(@RequestBody @Valid UserRequest userRequest){
        return ResponseEntity.ok(userService.create(userRequest));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Client not found")})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "US 0001 - Allows a Client to follow a Seller")
    @PostMapping(value = "/{userId}/follow/{userIdToFollow}") // US 0001
    ResponseEntity follow(@PathVariable Integer userId, @PathVariable Integer userIdToFollow){
        userService.follow(userId, userIdToFollow);
        return ResponseEntity.ok().build();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Client not found")})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "US 0002 - Count of followers of a Seller")
    @GetMapping(value = "/{userId}/followers/count") // US 0002
    ResponseEntity<SellerFollowersCount> getFollowersCount(@PathVariable Integer userId) {

        Seller user = (Seller) userService.getSeller(userId);
        SellerFollowersCount followerCount = userService.getFollowerCountResponse(userId);
        return ResponseEntity.ok(followerCount);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Client not found")})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "US 0003 and US0008 - List of Sellers followed by a Client")
    @GetMapping(value = "/{userId}/followers/list") // US 0003 and US 0008
    ResponseEntity<FollowList> getFollowedList(@PathVariable Integer userId, @RequestParam(defaultValue = "name_asc") String order){
        FollowList followList = userService.getFollowers(userId, order);
        return ResponseEntity.ok(followList);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Client not found")})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "US 0004 and US0008 - List of Clients following a Seller")
    @GetMapping(value = "/{userId}/followed/list") // US 0004 US 0008
    ResponseEntity<FollowList> getFollowersList(@PathVariable Integer userId, @RequestParam(defaultValue = "name_asc") String order){
        FollowList followList = userService.getFollowed(userId, order);
        return ResponseEntity.ok(followList);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Client not found")})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "US 0007 - Allows a Client to unfollow a Seller")
    @PostMapping(value = "/{userId}/unfollow/{userIdToUnfollow}") // US 0007
    ResponseEntity<FollowList> getFollowedList(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow){
        userService.unfollow(userId, userIdToUnfollow);
        return ResponseEntity.ok().build();
    }


}
