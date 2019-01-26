package com.involves.selecao.gateway.pesquisa;

import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Configuration
public class PesquisaConectionFactory {

    private URL url;

    public PesquisaConectionFactory(){

    }

    public HttpURLConnection getRequestPesquisa() throws IOException {
        this.url = new URL("https://selecao-involves.agilepromoter.com/pesquisas");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        return connection;
    }

}
