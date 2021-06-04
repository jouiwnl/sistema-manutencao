package com.joaoh.manutencao.manutencao.domain.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.joaoh.manutencao.manutencao.domain.enums.EstadoOrdemServico;
import com.joaoh.manutencao.manutencao.domain.enums.TipoEquipamento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdemDeServicoNewDTO implements Serializable {
    private EstadoOrdemServico estadoOrdemServico;
    
    @NotEmpty(message = "Preenchimento Obrigatório")
    @NotNull
    private Integer clienteId;

    @NotNull
    @NotEmpty(message = "Preenchimento obrigatório")
    private String nomeEquipamento;
   
    @NotEmpty(message = "Preenchimento obrigatório")
    private String detalhe;
    
    @NotEmpty(message = "Preenchimento obrigatório")
    private TipoEquipamento tipoEquipamento;
    private LocalDate data;

    public OrdemDeServicoNewDTO() {}


    public OrdemDeServicoNewDTO(EstadoOrdemServico estadoOrdemServico, Integer clienteId, String nomeEquipamento, String detalhe, TipoEquipamento tipoEquipamento, LocalDate data) {
        this.estadoOrdemServico = estadoOrdemServico;
        this.clienteId = clienteId;
        this.nomeEquipamento = nomeEquipamento;
        this.detalhe = detalhe;
        this.tipoEquipamento = tipoEquipamento;
        this.data = data;
    }

}
