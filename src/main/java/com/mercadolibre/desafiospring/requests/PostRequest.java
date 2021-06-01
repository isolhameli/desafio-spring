package com.mercadolibre.desafiospring.requests;

import com.mercadolibre.desafiospring.models.Product;

import java.time.LocalDate;

public class PostRequest {
    private Integer userId;
    private ProductRequest detail;
    private Integer category;
    private Double price;

    public PostRequest() {
    }

    public PostRequest(Integer userId, ProductRequest detail, Integer category, Double price) {
        this.userId = userId;
        this.detail = detail;
        this.category = category;
        this.price = price;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public ProductRequest getDetail() {
        return detail;
    }

    public void setDetail(ProductRequest detail) {
        this.detail = detail;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
