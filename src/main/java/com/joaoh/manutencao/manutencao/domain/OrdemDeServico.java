package com.joaoh.manutencao.manutencao.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joaoh.manutencao.manutencao.domain.enums.EstadoOrdemServico;
import com.joaoh.manutencao.manutencao.domain.enums.TipoEquipamento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class OrdemDeServico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    private EstadoOrdemServico status;
    
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

    @NotEmpty(message = "Preenchimento Obrigatório")
    private String nomeEquipamento;

    @NotEmpty(message = "Preenchimento Obrigatório")
    private String detalhe;
    
    @Enumerated(value = EnumType.ORDINAL)
    private TipoEquipamento tipoEquipamento;

    private LocalDate data;

    public OrdemDeServico() {}


    public OrdemDeServico(Integer id, EstadoOrdemServico status, Cliente cliente, String nomeEquipamento, TipoEquipamento tipoEquipamento, String detalhe, LocalDate data) {
        this.id = id;
        this.status = status;
        this.cliente = cliente;
        this.nomeEquipamento = nomeEquipamento;
        this.tipoEquipamento = tipoEquipamento;
        this.detalhe = detalhe;
        this.data = data;
    }

    
    
}
