package com.mercadolibre.desafiospring.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class CreateUserRequest {

    @NotBlank(message = "Preenchimento obrigatório")
    @Size(min=5,max=20,message = "Nome de usuário deve ter entre 5 e 20 caracteres")
    private String userName;
    private boolean seller;

    public CreateUserRequest() {
    }

    public CreateUserRequest(String userName, boolean seller) {
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
