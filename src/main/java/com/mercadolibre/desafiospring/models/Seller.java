package com.mercadolibre.desafiospring.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Seller extends User{

    @OneToMany(mappedBy = "seller")
    private List<Product> productList;


    @ManyToMany(mappedBy = "followingList")
    private List<User> followerList;


    public Seller() {
    }

    public Seller(String userName, LocalDate joinDate) {
        super(userName, joinDate);
        this.productList = new ArrayList();
        this.followerList = new ArrayList();
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<User> getFollowerList() {
        return followerList;
    }

    public void setFollowerList(List<User> followerList) {
        this.followerList = followerList;
    }

    @Transient
    public Integer getFollowerCount(){
        return this.followerList.size();
    }
}
