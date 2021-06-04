package com.joaoh.manutencao.manutencao.domain.enums;

import lombok.Getter;

@Getter
public enum TipoFuncionario {
    ADM(1, "ROLE_ADMIN"),
    BALCONISTA(2, "ROLE_BALCONISTA"),
    TECNICO(3, "ROLE_TECNICO");

    private Integer cod;
    private String desc;

    TipoFuncionario() {}

    TipoFuncionario (Integer cod, String desc) {
        this.cod = cod;
        this.desc = desc;
    }

}
