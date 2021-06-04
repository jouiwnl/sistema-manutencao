package com.joaoh.manutencao.manutencao.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

    private String user;
    private String senha;

    public LoginDTO () {}


    public LoginDTO(String user, String senha) {
        this.user = user;
        this.senha = senha;
    }

    
}
