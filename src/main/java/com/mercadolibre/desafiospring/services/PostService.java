package com.mercadolibre.desafiospring.services;

import com.mercadolibre.desafiospring.requests.PostRequest;
import com.mercadolibre.desafiospring.requests.PromotionalPostRequest;
import com.mercadolibre.desafiospring.responses.products.FollowedPostsResponse;
import com.mercadolibre.desafiospring.responses.products.PostResponse;
import com.mercadolibre.desafiospring.responses.products.PromotionalPostCountResponse;
import com.mercadolibre.desafiospring.responses.products.PromotionalPostListResponse;

public interface PostService {


    PostResponse create(PostRequest postRequest);

    FollowedPostsResponse getFollowedPostsLast14DaysResponse(Integer userId, String order);

    PostResponse createPromotional(PromotionalPostRequest postRequest);

    PromotionalPostCountResponse getPromotionalPostCount(Integer userId);

    PromotionalPostListResponse getPromotionalPostList(Integer userId);
}
