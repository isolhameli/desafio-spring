package com.mercadolibre.desafiospring.requests;

import com.mercadolibre.desafiospring.models.Post;
import com.mercadolibre.desafiospring.models.Product;
import com.mercadolibre.desafiospring.models.Seller;
import com.mercadolibre.desafiospring.validations.LocalDateCheck;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PostRequest {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");

    @NotNull(message = "Field cannot be null")
    protected Integer userId;
    @NotNull(message = "Field cannot be null")
    @Valid
    protected ProductRequest detail;
    @NotNull(message = "Field cannot be null")
    protected Integer category;
    @NotNull(message = "Field cannot be null")
    protected Double price;
    @LocalDateCheck
    protected String date;

    public PostRequest() {
    }

    public PostRequest(Integer userId, ProductRequest detail, Integer category, Double price, String date) {
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

    public String getDate() {

        return date == null ? LocalDate.now().format(formatter) : date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Post toModel(Seller seller, Product product){
        LocalDate localDate = LocalDate.parse(this.date,
                DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return new Post(null, localDate,seller,product,this.category,
                this.price, false, null);
    }
}
