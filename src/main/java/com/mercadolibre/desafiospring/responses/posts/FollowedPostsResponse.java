package com.mercadolibre.desafiospring.responses.posts;

import com.mercadolibre.desafiospring.models.Post;

import java.util.List;

public class FollowedPostsResponse {

    private Integer userId;
    private List<PostResponse> posts;

    public FollowedPostsResponse() {
    }

    public FollowedPostsResponse(Integer userId, List<PostResponse> posts) {
        this.userId = userId;
        this.posts = posts;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<PostResponse> getPosts() {
        return posts;
    }

    public void setPosts(List<PostResponse> posts) {
        this.posts = posts;
    }
}

