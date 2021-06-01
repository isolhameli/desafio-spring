package com.mercadolibre.desafiospring.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mercadolibre.desafiospring.models.Product;

import java.time.LocalDate;

@JsonPropertyOrder({"userId", "id","date", "detail", "category","price"})
public class PostResponse {

    private Integer userId;
    private Integer id;
    private LocalDate date;
    private Product detail;
    private Integer category;
    private Double price;

    public PostResponse() {
    }

    public PostResponse(Integer id, LocalDate date, Integer userId, Product detail, Integer category, Double price) {
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

    public Product getDetail() {
        return detail;
    }

    public void setDetail(Product detail) {
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
