package com.mercadolibre.desafiospring.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Seller extends User{

    @OneToMany(mappedBy = "seller")
    private List<Post> postList;


    @ManyToMany(mappedBy = "following")
    private Set<User> followers;


    public Seller() {
    }

    public Seller(String userName, LocalDate joinDate) {
        super(userName, joinDate);
        this.postList = new ArrayList();
        this.followers = new HashSet<User>();
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followerList) {
        this.followers = followerList;
    }

    @Transient
    public Integer getFollowerCount(){
        return this.followers.size();
    }
}
