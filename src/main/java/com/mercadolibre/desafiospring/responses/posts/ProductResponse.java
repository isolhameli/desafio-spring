package com.mercadolibre.desafiospring.responses.posts;

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

    public ProductResponse(Integer id, String productName, String type, String brand, String color, String notes) {
        this.id = id;
        this.productName = productName;
        this.type = type;
        this.brand = brand;
        this.color = color;
        this.notes = notes;
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

    public static ProductResponse fromEntity(Product product){
        return new ProductResponse(product.getId(), product.getProductName(), product.getType(),
                product.getBrand(), product.getColor(), product.getNotes());
    }
}
