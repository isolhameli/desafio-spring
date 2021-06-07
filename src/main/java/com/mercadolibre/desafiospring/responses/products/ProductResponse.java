package com.mercadolibre.desafiospring.responses.products;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mercadolibre.desafiospring.models.Product;

@JsonPropertyOrder({"id", "productName","type", "brand", "color","notes"})
public class ProductResponse {

    private Integer id;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;


    public ProductResponse() {
    }

    public ProductResponse(Product product){
        this.id = product.getId();
        this.productName = product.getProductName();
        this.type = product.getType();
        this.brand = product.getBrand();
        this.color = product.getColor();
        this.notes = product.getNotes();
    }

    @JsonProperty("product_id")
    public Integer getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public String getNotes() {
        return notes;
    }


}
