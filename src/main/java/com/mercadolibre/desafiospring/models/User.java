package com.mercadolibre.desafiospring.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
    @NotBlank(message = "Preenchimento obrigatório")
    @Size(min=5,max=20,message = "Nome de usuário deve ter entre 5 e 20 caracteres")
    private String userName;
    private LocalDate joinDate;

    @ManyToMany
    @JoinTable(name="FOLLOWERS",joinColumns = @JoinColumn(name="USER_ID"),inverseJoinColumns = @JoinColumn(name="SELLER_ID"))
    private List<Seller> followingList;

    public User(String userName, LocalDate joinDate) {
        this.userName = userName;
        this.joinDate = joinDate;
        this.followingList = new ArrayList<>();

    }

    public User() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public List<Seller> getFollowingList() {
        return followingList;
    }

    public void setFollowingList(List<Seller> followingList) {
        this.followingList = followingList;
    }
}
