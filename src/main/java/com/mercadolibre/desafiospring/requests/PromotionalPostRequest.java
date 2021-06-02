package com.mercadolibre.desafiospring.requests;

import com.mercadolibre.desafiospring.models.Post;
import com.mercadolibre.desafiospring.models.Product;
import com.mercadolibre.desafiospring.models.Seller;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PromotionalPostRequest extends PostRequest{

    @NotNull(message = "Field cannot be null")
    private Boolean hasPromo;

    @NotNull(message="Field cannot be null")
    @DecimalMin(value="0.0",message="Discount must be between 0 and 1")
    @DecimalMax(value="1.0",message="Discount must be between 0 and 1")
    private Double discount;

    public PromotionalPostRequest(){
    }

    public PromotionalPostRequest(Integer userId, ProductRequest detail, Integer category, Double price, String date, Boolean hasPromo, Double discount) {
        super(userId, detail, category, price, date);
        this.hasPromo = hasPromo;
        this.discount = discount;
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

    public Post toModel(Seller seller, Product product){
        LocalDate localDate = LocalDate.parse(this.date,
                DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return new Post(null, localDate,seller,product,this.category,
                this.price, this.hasPromo, this.discount);
    }
}
