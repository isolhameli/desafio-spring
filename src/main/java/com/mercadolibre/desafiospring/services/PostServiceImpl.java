package com.mercadolibre.desafiospring.services;

import com.mercadolibre.desafiospring.exceptions.products.PostMustHavePromotionException;
import com.mercadolibre.desafiospring.exceptions.users.UserNotFoundException;
import com.mercadolibre.desafiospring.models.Post;
import com.mercadolibre.desafiospring.models.Product;
import com.mercadolibre.desafiospring.models.Seller;
import com.mercadolibre.desafiospring.repositories.PostRepository;
import com.mercadolibre.desafiospring.repositories.ProductRepository;
import com.mercadolibre.desafiospring.requests.PostRequest;
import com.mercadolibre.desafiospring.requests.PromotionalPostRequest;
import com.mercadolibre.desafiospring.responses.products.FollowedPostsResponse;
import com.mercadolibre.desafiospring.responses.products.PostResponse;
import com.mercadolibre.desafiospring.responses.products.PromotionalPostCountResponse;
import com.mercadolibre.desafiospring.responses.products.PromotionalPostListResponse;
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
    private final UtilService utilService;

    public PostServiceImpl(ProductRepository productRepository, UserService userService, PostRepository postRepository,
                           ProductService productService, UtilService utilService) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.postRepository = postRepository;
        this.productService = productService;
        this.utilService = utilService;
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
        return postRepository.findBySellerFollowersIdAndDateBetween(userId,twoWeeksAgo, today,
                utilService.getSort(order,PostResponse.class, "date"));
    }

    public FollowedPostsResponse getFollowedPostsLast14DaysResponse(Integer userId, String order){

        List<PostResponse> postResponseList = findListFollowedPostsLast14Days(userId,order).stream()
                .map(el -> new PostResponse(el))
                .collect(Collectors.toList());

        return new FollowedPostsResponse(userId,postResponseList);
    }

    @Override
    public PostResponse createPromotional(PromotionalPostRequest postRequest) {
        if (!postRequest.getHasPromo()){
            throw new PostMustHavePromotionException("Posts created through this endpoint must have a promotion");
        }
        Post post = save(postRequest);
        return new PostResponse(post);
    }

    @Override
    public PromotionalPostCountResponse getPromotionalPostCount(Integer userId) {
        Seller user = userService.getSeller(userId);
        Integer promotionalPostCount = postRepository.countBySellerIdAndHasPromo(userId,true);
        return new PromotionalPostCountResponse(userId, user.getUserName(),promotionalPostCount);
    }

    @Override
    public PromotionalPostListResponse getPromotionalPostList(Integer userId) {
        Seller user = userService.getSeller(userId);
        List<PostResponse> postList= postRepository.
                findBySellerIdAndHasPromo(userId,true)
                .stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
        return new PromotionalPostListResponse(userId, user.getUserName(),postList);
    }

    private Sort.Direction getSortDirection(String order){
        return order.toLowerCase().equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
    }
}
