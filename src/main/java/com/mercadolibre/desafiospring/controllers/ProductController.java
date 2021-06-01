package com.mercadolibre.desafiospring.controllers;


import com.fasterxml.jackson.annotation.JsonView;
import com.mercadolibre.desafiospring.models.Post;
import com.mercadolibre.desafiospring.requests.PostRequest;
import com.mercadolibre.desafiospring.responses.posts.FollowedPostsResponse;
import com.mercadolibre.desafiospring.responses.posts.PostResponse;
import com.mercadolibre.desafiospring.responses.posts.ProductResponse;
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

    @PostMapping(value="/newpost")
    ResponseEntity<PostResponse> create(@RequestBody @Valid PostRequest postRequest){
        Post post = postService.create(postRequest);
        PostResponse postResponse = new PostResponse(post.getId(), post.getDate(),
                post.getSeller().getId(), ProductResponse.fromEntity(post.getProduct()),
                post.getCategory(), post.getPrice());
        return ResponseEntity.ok(postResponse);
    }

    @GetMapping(value="/followed/{userId}/list")
    @JsonView(PostView.Simple.class)
    ResponseEntity<FollowedPostsResponse> followedProducts(@PathVariable Integer userId){
        List<PostResponse> postResponseList = postService.findFollowedPostsLast14Days(userId);
        FollowedPostsResponse response = new FollowedPostsResponse(userId,postResponseList);
        return ResponseEntity.ok(response);
    }




}
