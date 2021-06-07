package com.joaoh.manutencao.manutencao.domain.enums;

import lombok.Getter;

@Getter
public enum TipoEquipamento {
    
    COMPUTADOR(1, "Computador/Notebook"),
    IMPRESSORA(2, "Impressora"),
    CELULAR(3, "Celular"),
    ELETRODOMESTICO(4, "Eletrodoméstico");

    private Integer cod;
    private String descricao;

    TipoEquipamento(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static TipoEquipamento toEnum(Integer cod) {
        for (TipoEquipamento x : TipoEquipamento.values()) {
            if (x.getCod().equals(cod)) {
                return x;
            }
        }

        throw new IllegalArgumentException("Código inválido");
    }


}
