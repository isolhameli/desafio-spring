package com.mercadolibre.desafiospring.services;

import com.mercadolibre.desafiospring.models.Post;
import com.mercadolibre.desafiospring.models.Product;
import com.mercadolibre.desafiospring.models.Seller;
import com.mercadolibre.desafiospring.repositories.PostRepository;
import com.mercadolibre.desafiospring.requests.PostRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PostServiceImpl implements PostService{

    private final UserService userService;
    private final PostRepository postRepository;
    private final ProductService productService;

    public PostServiceImpl(UserService userService, PostRepository postRepository, ProductService productService) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.productService = productService;
    }

    @Override
    public Post create(PostRequest postRequest) {
        Seller seller = userService.getSeller(postRequest.getUserId());
        Product product = productService.create(postRequest.getDetail());
        Post post = new Post(null, LocalDate.now(), seller,
                product, postRequest.getCategory(), postRequest.getPrice());
        return postRepository.save((Post) post);
    }
}
