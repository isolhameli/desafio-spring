package com.mercadolibre.desafiospring.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class UserRequest {

    @NotNull(message = "Field cannot be null")
    @Size(min=5,max=20,message = "userName must be between 5 and 20 characters")
    private String userName;
    @NotNull(message = "Field cannot be null")
    private Boolean seller;

    public UserRequest() {
    }

    public UserRequest(String userName, boolean seller) {
        this.userName = userName;
        this.seller = seller;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isSeller() {
        return seller;
    }

    public void setSeller(boolean seller) {
        this.seller = seller;
    }
}
