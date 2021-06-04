package com.joaoh.manutencao.manutencao.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO implements Serializable {

    @NotEmpty(message = "Preenchimento obrigatório")
    private String nome;

    private String telefone;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String email;

    public ClienteDTO() {}

    

    public ClienteDTO(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }
    
}
