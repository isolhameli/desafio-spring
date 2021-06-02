package com.mercadolibre.desafiospring.responses.posts;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.mercadolibre.desafiospring.models.Post;
import com.mercadolibre.desafiospring.views.PostView;

import java.time.LocalDate;

public class PromotionalPostResponse extends  PostResponse{


    private Boolean hasPromo;

    private Double discount;

    public PromotionalPostResponse() {
    }

    public PromotionalPostResponse(Post post) {
        super(post);
        this.hasPromo = post.getHasPromo();
        this.discount = post.getDiscount();
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
