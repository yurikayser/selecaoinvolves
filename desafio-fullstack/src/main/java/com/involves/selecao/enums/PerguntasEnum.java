package com.involves.selecao.enums;

public enum PerguntasEnum {

    SITUACAO_PRODUTO("Qual a situação do produto?"),
    PRECO_PRODUTO("Qual o preço do produto?"),
    SHARE("%share");

    private String descricao;

    PerguntasEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao(){
        return this.descricao;
    }
}
