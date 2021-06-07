package com.mercadolibre.desafiospring.responses.products;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PromotionalPostListResponse {

    private Integer userId;
    private String userName;
    private List<PostResponse> posts;

    public PromotionalPostListResponse() {
    }

    public PromotionalPostListResponse(Integer userId, String userName, List<PostResponse> posts) {
        this.userId = userId;
        this.userName = userName;
        this.posts = posts;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<PostResponse> getPosts() {
        return posts;
    }

    public void setPosts(List<PostResponse> posts) {
        this.posts = posts;
    }
}
