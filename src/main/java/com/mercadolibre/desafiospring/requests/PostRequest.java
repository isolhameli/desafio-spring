package com.mercadolibre.desafiospring.requests;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class PostRequest {
    @NotNull(message = "Field cannot be null")
    private Integer userId;
    @NotNull(message = "Field cannot be null")
    private ProductRequest detail;
    @NotNull(message = "Field cannot be null")
    private Integer category;
    @NotNull(message = "Field cannot be null")
    private Double price;
    private LocalDate date;

    public PostRequest() {
    }

    public PostRequest(Integer userId, ProductRequest detail, Integer category, Double price, LocalDate date) {
        this.userId = userId;
        this.detail = detail;
        this.category = category;
        this.price = price;
        this.date = date;
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

    public LocalDate getDate() {
        return date == null ? LocalDate.now() : date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
