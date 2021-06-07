package com.joaoh.manutencao.manutencao.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.joaoh.manutencao.manutencao.domain.enums.EstadoOrdemServico;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdemApproveDTO implements Serializable {
    
    @NotEmpty(message = "Preenchimento Obrigatório")
    private EstadoOrdemServico status;

    public OrdemApproveDTO() {}


    public OrdemApproveDTO(EstadoOrdemServico status) {
        this.status = status;
    }

}
