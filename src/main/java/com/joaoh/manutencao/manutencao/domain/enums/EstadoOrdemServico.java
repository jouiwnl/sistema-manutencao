package com.joaoh.manutencao.manutencao.domain.enums;

import lombok.Getter;

@Getter
public enum EstadoOrdemServico {
    ABERTA(1, "Em Aberto"),
    ATENDIMENTO(2, "Em Atendimento"),
    CONCLUIDA(3, "Concluída");

    private Integer cod;
    private String descricao;

    EstadoOrdemServico(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static EstadoOrdemServico toEnum(Integer cod) {
        for (EstadoOrdemServico x : EstadoOrdemServico.values()) {
            if (x.getCod().equals(cod)) {
                return x;
            }
        }

        throw new IllegalArgumentException("Código inválido");
    }

}
