package com.mercadolibre.desafiospring.controllers;


import com.mercadolibre.desafiospring.models.Post;
import com.mercadolibre.desafiospring.requests.PostRequest;
import com.mercadolibre.desafiospring.responses.PostResponse;
import com.mercadolibre.desafiospring.services.PostService;
import com.mercadolibre.desafiospring.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
        PostResponse postResponse = new PostResponse(post.getId(), post.getLocalDate(),
                post.getSeller().getId(), post.getDetail(), post.getCategory(), post.getPrice());
        return ResponseEntity.ok(postResponse);
    }




}
