package com.joaoh.manutencao.manutencao.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteNewDTO implements Serializable {

    @NotEmpty(message = "Preenchimento Obrigatório")
    @Length(min = 5, max = 200)
    private String nome;
    
    @NotEmpty(message = "Preenchimento Obrigatório")
    @Length(min = 9, max = 12)
    private String telefone;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Email
    @Length(min = 5, max = 300)
    private String email;
    
    @NotEmpty(message = "Preenchimento obrigatório")
    private String rua;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String numero;

    @NotEmpty(message = "Preenchimento obrigatório")
    private Integer cidadeId;

    
    public ClienteNewDTO() {}

    public ClienteNewDTO(String nome, String telefone, String email, String rua, String numero, Integer cidadeId) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.rua = rua;
        this.numero = numero;
        this.cidadeId = cidadeId;
    }
}
