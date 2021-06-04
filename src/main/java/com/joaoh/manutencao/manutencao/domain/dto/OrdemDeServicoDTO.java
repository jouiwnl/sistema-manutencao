package com.joaoh.manutencao.manutencao.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.joaoh.manutencao.manutencao.domain.enums.EstadoOrdemServico;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdemDeServicoDTO implements Serializable {

    @NotEmpty(message = "Necessário alterar o estado da ordem de serviço")
    private EstadoOrdemServico status;

    public OrdemDeServicoDTO() {}

    public OrdemDeServicoDTO(EstadoOrdemServico status) {
        this.status = status;
    }
    
}
