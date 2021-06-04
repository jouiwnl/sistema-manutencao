package com.joaoh.manutencao.manutencao.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Credenciais implements Serializable {
    
    private String user;
    private String senha;

    public Credenciais () {}
    
    public Credenciais(String user, String senha) {
        this.senha = senha;
        this.user = user;
    }

}
