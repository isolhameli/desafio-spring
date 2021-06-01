package com.mercadolibre.desafiospring.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Seller extends User{

    @OneToMany(mappedBy = "seller")
    private List<Post> postList;


    @ManyToMany(mappedBy = "followingList")
    private List<User> followerList;


    public Seller() {
    }

    public Seller(String userName, LocalDate joinDate) {
        super(userName, joinDate);
        this.postList = new ArrayList();
        this.followerList = new ArrayList();
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
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
