package com.involves.selecao.gateway.pesquisa;

import com.involves.selecao.alerta.Pesquisa;

import java.io.IOException;

public interface PesquisaGateway {

    public Pesquisa[] pesquisaDadosAlertas() throws IOException;
}
