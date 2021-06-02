package com.mercadolibre.desafiospring.models;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private LocalDate date;

    @ManyToOne
    private Seller seller;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Product product;

    @NotNull
    private Integer category;

    @NotNull
    private Double price;

    @NotNull
    private Boolean hasPromo;

    @DecimalMin(value = "0.0", message="Discount must be between 0 and 1")
    @DecimalMax(value="1.0",message="Discount must be between 0 and 1")
    private Double discount;

    public Post() {
    }

    public Post(Integer id, LocalDate date, Seller seller, Product product, Integer category, Double price, Boolean hasPromo, Double discount) {
        this.id = id;
        this.date = date;
        this.seller = seller;
        this.product = product;
        this.category = category;
        this.price = price;
        this.hasPromo = hasPromo;
        this.discount = discount;
    }

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

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
