package com.mercadolibre.desafiospring.controllers;


import com.fasterxml.jackson.annotation.JsonView;
import com.mercadolibre.desafiospring.models.Post;
import com.mercadolibre.desafiospring.requests.PostRequest;
import com.mercadolibre.desafiospring.requests.PromotionalPostRequest;
import com.mercadolibre.desafiospring.responses.posts.FollowedPostsResponse;
import com.mercadolibre.desafiospring.responses.posts.PostResponse;
import com.mercadolibre.desafiospring.responses.posts.ProductResponse;
import com.mercadolibre.desafiospring.responses.posts.PromotionalPostResponse;
import com.mercadolibre.desafiospring.services.PostService;
import com.mercadolibre.desafiospring.services.ProductService;
import com.mercadolibre.desafiospring.views.PostView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value ="/products")
public class ProductController {

    private final ProductService productService;
    private final PostService postService;

    public ProductController(ProductService productService, PostService postService) {
        this.productService = productService;
        this.postService = postService;
    }

    @PostMapping(value="/newpost") // US 0005
    ResponseEntity<PostResponse> create(@RequestBody @Valid PostRequest postRequest){
        PostResponse post = postService.create(postRequest);
        return ResponseEntity.ok(post);
    }

    @PostMapping(value="/newpromopost") // US 0010
    ResponseEntity<PromotionalPostResponse> create(@RequestBody @Valid PromotionalPostRequest postRequest){
        PromotionalPostResponse post = postService.createPromotional(postRequest);
        return ResponseEntity.ok(post);
    }

    @GetMapping(value="/followed/{userId}/list") // US 0006 and US 0009
    @JsonView(PostView.Simple.class)
    ResponseEntity<FollowedPostsResponse> followedProducts(@PathVariable Integer userId, @RequestParam(defaultValue = "desc") String order){
        FollowedPostsResponse postResponseList = postService.getFollowedPostsLast14DaysResponse(userId, order);
        return ResponseEntity.ok(postResponseList);
    }




}
