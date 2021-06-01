package com.mercadolibre.desafiospring.services;

import com.mercadolibre.desafiospring.models.Post;
import com.mercadolibre.desafiospring.requests.PostRequest;
import com.mercadolibre.desafiospring.responses.posts.PostResponse;

import java.util.List;

public interface PostService {


    Post create(PostRequest postRequest);

    List<PostResponse> findFollowedPostsLast14Days(Integer userId);
}
