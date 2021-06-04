package com.joaoh.manutencao.manutencao.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import com.joaoh.manutencao.manutencao.domain.enums.TipoFuncionario;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Funcionario implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String nome;
    @NotEmpty
    private String user;
    @NotEmpty
    private String senha;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    private TipoFuncionario tipo;

    public Funcionario() {}

    public Funcionario(String nome, String user, String senha, TipoFuncionario tipo) {
        this.nome = nome;
        this.user = user;
        this.senha = senha;
        this.tipo = tipo;
    }
    

    
}
