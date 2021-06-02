package com.mercadolibre.desafiospring.services;

import com.mercadolibre.desafiospring.requests.PostRequest;
import com.mercadolibre.desafiospring.requests.PromotionalPostRequest;
import com.mercadolibre.desafiospring.responses.posts.FollowedPostsResponse;
import com.mercadolibre.desafiospring.responses.posts.PostResponse;
import com.mercadolibre.desafiospring.responses.posts.PromotionalPostResponse;

public interface PostService {


    PostResponse create(PostRequest postRequest);

    FollowedPostsResponse getFollowedPostsLast14DaysResponse(Integer userId, String order);

    PromotionalPostResponse createPromotional(PromotionalPostRequest postRequest);
}
