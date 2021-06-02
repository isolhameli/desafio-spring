package com.mercadolibre.desafiospring.services;

import com.mercadolibre.desafiospring.exceptions.products.PostMustHavePromotionException;
import com.mercadolibre.desafiospring.exceptions.user.UserNotFoundException;
import com.mercadolibre.desafiospring.models.Post;
import com.mercadolibre.desafiospring.models.Product;
import com.mercadolibre.desafiospring.models.Seller;
import com.mercadolibre.desafiospring.repositories.PostRepository;
import com.mercadolibre.desafiospring.repositories.ProductRepository;
import com.mercadolibre.desafiospring.requests.PostRequest;
import com.mercadolibre.desafiospring.requests.PromotionalPostRequest;
import com.mercadolibre.desafiospring.responses.posts.FollowedPostsResponse;
import com.mercadolibre.desafiospring.responses.posts.PostResponse;
import com.mercadolibre.desafiospring.responses.posts.PromotionalPostResponse;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{


    private final ProductRepository productRepository;
    private final UserService userService;
    private final PostRepository postRepository;
    private final ProductService productService;

    public PostServiceImpl(UserService userService, PostRepository postRepository, ProductService productService,
                           ProductRepository productRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @Override
    public PostResponse create(PostRequest postRequest) {
        Post post = save(postRequest);
        return new PostResponse(post);
    }

    @Transactional
    public Post save(PostRequest postRequest){
        Seller seller = userService.getSeller(postRequest.getUserId());
        Product product = productService.save(postRequest.getDetail());
        Post post = postRequest.toModel(seller,product);
        return postRepository.save(post);
    }

    private List<Post> findListFollowedPostsLast14Days(Integer userId, String order) {
        if (!userService.checkUserExistence(userId)){
            throw new UserNotFoundException("User not found. ID: "+userId);
        }
        LocalDate today = LocalDate.now();
        LocalDate twoWeeksAgo = LocalDate.now().minusDays(14);
        if (order.toLowerCase().strip().equals("desc")){
            return postRepository.findBySellerFollowersIdAndDateBetween(userId,twoWeeksAgo, today, Sort.by("date").descending());
        }
        return postRepository.findBySellerFollowersIdAndDateBetween(userId,twoWeeksAgo, today,Sort.by("date").ascending());

    }

    public FollowedPostsResponse getFollowedPostsLast14DaysResponse(Integer userId, String order){

        List<PostResponse> postResponseList = findListFollowedPostsLast14Days(userId,order).stream()
                .map(el -> new PostResponse(el))
                .collect(Collectors.toList());

        return new FollowedPostsResponse(userId,postResponseList);
    }

    @Override
    public PromotionalPostResponse createPromotional(PromotionalPostRequest postRequest) {
        Post post = save(postRequest);
        if (!postRequest.getHasPromo()){
            throw new PostMustHavePromotionException("Posts created through this endpoint must have a promotion");
        }
        return new PromotionalPostResponse(post);
    }
}
