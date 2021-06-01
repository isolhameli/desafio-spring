package com.mercadolibre.desafiospring.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private LocalDate localDate;

    @ManyToOne
    private Seller seller;

    @OneToOne
    private Product detail;

    @NotNull
    private Integer category;

    @NotNull
    private Double price;

    public Post() {
    }

    public Post(Integer id, LocalDate localDate, Seller seller, Product detail, Integer category, Double price) {
        this.id = id;
        this.localDate = localDate;
        this.seller = seller;
        this.detail = detail;
        this.category = category;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
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
