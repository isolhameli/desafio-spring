package com.mercadolibre.desafiospring.responses.products;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PromotionalPostCountResponse {

    private Integer userId;
    private String userName;
    private Integer promotionalProductsCount;

    public PromotionalPostCountResponse() {
    }

    public PromotionalPostCountResponse(Integer userId, String userName, Integer promotionalProductsCount) {
        this.userId = userId;
        this.userName = userName;
        this.promotionalProductsCount = promotionalProductsCount;
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

    @JsonProperty("promoproducts_count")
    public Integer getPromotionalProductsCount() {
        return promotionalProductsCount;
    }

    public void setPromotionalProductsCount(Integer promotionalProductsCount) {
        this.promotionalProductsCount = promotionalProductsCount;
    }
}
