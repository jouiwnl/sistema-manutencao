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



@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Funcionario implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String nome;
    @NotEmpty
    private String usuario;
    @NotEmpty
    private String senha;

    @Enumerated(EnumType.STRING)
    private TipoFuncionario tipo;

    public Funcionario() {}

    public Funcionario(String nome, String usuario, String senha, TipoFuncionario tipo) {
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.tipo = tipo;
    }
    

    
}
