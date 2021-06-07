package com.joaoh.manutencao.manutencao.domain.dto;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.joaoh.manutencao.manutencao.domain.enums.EstadoOrdemServico;
import com.joaoh.manutencao.manutencao.domain.enums.TipoEquipamento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdemDeServicoDTO implements Serializable {

    private String nomeEquipamento;
    private String detalhe;
    
    @Enumerated(value = EnumType.ORDINAL)
    private TipoEquipamento tipoEquipamento;

    private EstadoOrdemServico status;

    public OrdemDeServicoDTO() {}


    public OrdemDeServicoDTO(String nomeEquipamento, String detalhe, TipoEquipamento tipoEquipamento, EstadoOrdemServico status) {
        this.nomeEquipamento = nomeEquipamento;
        this.detalhe = detalhe;
        this.tipoEquipamento = tipoEquipamento;
        this.status = status;
    }

}
