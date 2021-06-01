package com.mercadolibre.desafiospring.services;

import com.mercadolibre.desafiospring.exceptions.user.UserNotFoundException;
import com.mercadolibre.desafiospring.models.Post;
import com.mercadolibre.desafiospring.models.Product;
import com.mercadolibre.desafiospring.models.Seller;
import com.mercadolibre.desafiospring.repositories.PostRepository;
import com.mercadolibre.desafiospring.requests.PostRequest;
import com.mercadolibre.desafiospring.responses.posts.PostResponse;
import com.mercadolibre.desafiospring.responses.posts.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        Post post = new Post(null, postRequest.getDate(), seller,
                product, postRequest.getCategory(), postRequest.getPrice());
        return postRepository.save((Post) post);
    }

    @Override
    public List<PostResponse> findFollowedPostsLast14Days(Integer userId) {
        if (!userService.checkUserExistence(userId)){
            throw new UserNotFoundException("User not found. ID: "+userId);
        }
        return postRepository.findFollowedPostsLast14Days(userId)
                .stream().map(el -> postToPostResponse(el)).collect(Collectors.toList());
    }

    private PostResponse postToPostResponse(Post post){
        return new PostResponse(post.getId(),post.getDate(), post.getSeller().getId(),
                ProductResponse.fromEntity(post.getProduct()),
                post.getCategory(), post.getPrice());
    }
}
