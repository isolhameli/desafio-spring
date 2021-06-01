package com.mercadolibre.desafiospring.responses.posts;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.mercadolibre.desafiospring.models.Product;
import com.mercadolibre.desafiospring.views.PostView;

import java.time.LocalDate;

@JsonPropertyOrder({"userId", "id","date", "detail", "category","price"})
public class PostResponse {

    @JsonView(PostView.Detailed.class)
    private Integer userId;
    private Integer id;
    private LocalDate date;
    private ProductResponse detail;
    private Integer category;
    private Double price;

    public PostResponse() {
    }

    public PostResponse(Integer id, LocalDate date, Integer userId, ProductResponse detail, Integer category, Double price) {
        this.id = id;
        this.date = date;
        this.userId = userId;
        this.detail = detail;
        this.category = category;
        this.price = price;
    }

    @JsonProperty("id_post")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public ProductResponse getDetail() {
        return detail;
    }

    public void setDetail(ProductResponse detail) {
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
