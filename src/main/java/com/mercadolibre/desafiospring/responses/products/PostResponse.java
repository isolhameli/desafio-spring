package com.mercadolibre.desafiospring.responses.products;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.mercadolibre.desafiospring.models.Post;
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
    @JsonView({PostView.PromotionalDetailed.class,PostView.PromotionalSimple.class})
    private Boolean hasPromo;
    @JsonView({PostView.PromotionalDetailed.class,PostView.PromotionalSimple.class})
    private Double discount;

    public PostResponse() {
    }

    public PostResponse(Post post){
        this.id = post.getId();
        this.date = post.getDate();
        this.userId = post.getSeller().getId();
        this.detail = new ProductResponse(post.getProduct());
        this.category = post.getCategory();
        this.price = post.getPrice();
        this.hasPromo = post.getHasPromo();
        this.discount = post.getDiscount();
    }

    @JsonProperty("id_post")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonFormat(pattern = "dd-MM-uuuu")
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

    public Boolean getHasPromo() {
        return hasPromo;
    }

    public void setHasPromo(Boolean hasPromo) {
        this.hasPromo = hasPromo;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
