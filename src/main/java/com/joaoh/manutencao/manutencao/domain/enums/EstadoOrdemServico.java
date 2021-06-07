package com.joaoh.manutencao.manutencao.domain.enums;

import lombok.Getter;

@Getter
public enum EstadoOrdemServico {
    APROVACAO(1, "Aguardando Aprovação"),
    APROVADA(2, "Aprovada"),
    ABERTA(3, "Em Aberto"),
    ATENDIMENTO(4, "Em Atendimento"),
    CONCLUIDA(5, "Concluída");

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
