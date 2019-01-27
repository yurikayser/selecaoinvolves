package com.involves.selecao.enums;

public enum RespostasEnum{
    AUSENTE_GONDOLA("Produto ausente na gondola"),
    ;

    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    RespostasEnum(String descricao) {
        this.descricao = descricao;
    }
}
