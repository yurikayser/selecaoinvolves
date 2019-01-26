package com.involves.selecao.gateway.pesquisa;

import com.google.gson.Gson;
import com.involves.selecao.alerta.Pesquisa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Objects;

@Component
public class PesquisaGatewayImpl implements PesquisaGateway {

    @Autowired
    private PesquisaConectionFactory connectionFactory;

    @Override
    public Pesquisa[] pesquisaDadosAlertas() throws IOException {
        HttpURLConnection requestPesquisa = this.connectionFactory.getRequestPesquisa();
        BufferedReader reader = new BufferedReader(new InputStreamReader(requestPesquisa.getInputStream(), "UTF-8"));
        StringBuffer buffer = new StringBuffer();

        String inputLine = reader.readLine();
        while (Objects.nonNull(inputLine)) {
            buffer.append(inputLine);
            inputLine = reader.readLine();
        }
        reader.close();

        Gson gson = new Gson();
        return gson.fromJson(buffer.toString(), Pesquisa[].class);
    }
}
