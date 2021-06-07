package com.mercadolibre.desafiospring.controllers;


import com.fasterxml.jackson.annotation.JsonView;
import com.mercadolibre.desafiospring.requests.PostRequest;
import com.mercadolibre.desafiospring.requests.PromotionalPostRequest;
import com.mercadolibre.desafiospring.responses.products.FollowedPostsResponse;
import com.mercadolibre.desafiospring.responses.products.PostResponse;
import com.mercadolibre.desafiospring.responses.products.PromotionalPostCountResponse;
import com.mercadolibre.desafiospring.responses.products.PromotionalPostListResponse;
import com.mercadolibre.desafiospring.services.PostService;
import com.mercadolibre.desafiospring.services.ProductService;
import com.mercadolibre.desafiospring.views.PostView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value ="/products")
@Api(tags = "Products")
public class ProductController {

    private final ProductService productService;
    private final PostService postService;

    public ProductController(ProductService productService, PostService postService) {
        this.productService = productService;
        this.postService = postService;
    }

    @ApiOperation(value = "US 0005 - Register a new non promotional Post for a given Seller")
    @PostMapping(value="/newpost") // US 0005
    @JsonView(PostView.Detailed.class)
    ResponseEntity<PostResponse> create(@RequestBody @Valid PostRequest postRequest){
        PostResponse post = postService.create(postRequest);
        return ResponseEntity.ok(post);
    }

    @ApiOperation(value = "US 0010 - Register a new promotional Post for a given Seller")
    @PostMapping(value="/newpromopost") // US 0010
    @JsonView(PostView.PromotionalDetailed.class)
    ResponseEntity<PostResponse> createPromotional(@RequestBody @Valid PromotionalPostRequest postRequest){
        PostResponse post = postService.createPromotional(postRequest);
        return ResponseEntity.ok(post);
    }

    @ApiOperation(value = "US 0006 and US 0009 - List all Posts by Sellers followed by a Client (userId) posted in the last 14 days")
    @GetMapping(value="/followed/{userId}/list") // US 0006 and US 0009
    @JsonView(PostView.Simple.class)
    ResponseEntity<FollowedPostsResponse> followedProducts(@PathVariable Integer userId, @RequestParam(defaultValue = "desc") String order){
        FollowedPostsResponse postResponseList = postService.getFollowedPostsLast14DaysResponse(userId, order);
        return ResponseEntity.ok(postResponseList);
    }

    @ApiOperation(value = "US 0011 - Count all Promotional Posts by a Seller")
    @GetMapping(value = "/{userId}/countPromo") // US 0011
    ResponseEntity<PromotionalPostCountResponse> promotionalPostCount(@PathVariable Integer userId){
        PromotionalPostCountResponse promotionalPostCount = postService.getPromotionalPostCount(userId);
        return ResponseEntity.ok(promotionalPostCount);
    }

    @ApiOperation(value = "US 0012 - Lists all Promotional Posts by a Seller")
    @GetMapping(value = "/{userId}/list") // US 0012
    @JsonView(PostView.PromotionalSimple.class)
    ResponseEntity<PromotionalPostListResponse> promotionalPostList(@PathVariable Integer userId){
        PromotionalPostListResponse promotionalPostList = postService.getPromotionalPostList(userId);
        return ResponseEntity.ok(promotionalPostList);
    }




}
