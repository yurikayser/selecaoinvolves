package com.involves.selecao.service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

import com.involves.selecao.gateway.pesquisa.PesquisaGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.alerta.Resposta;
import com.involves.selecao.gateway.mongo.AlertaGateway;

@Service
public class ProcessaAlertasService {

	@Autowired
	private AlertaGateway alertaGateway;

	@Autowired
	private PesquisaGateway pesquisaGateway;

	public void processa() throws IOException {
		Pesquisa[] dados = this.pesquisaGateway.pesquisaDadosAlertas();
		for (int i = 0; i < dados.length; i++){
			for (int j = 0; j < dados[i].getRespostas().size(); j++){
				Resposta resposta = dados[i].getRespostas().get(j);
				// TALVEZ TRANSFORMAR EM UM CASE de acordo com a pergunta
				//Strings podem virar enums, pergunta e resposta
				if (resposta.getPergunta().equals("Qual a situação do produto?")) {
					if(resposta.getResposta().equals("Produto ausente na gondola")){
					    Alerta alerta = new Alerta();
					    alerta.setPontoDeVenda(dados[i].getPonto_de_venda());// camel Case
					    alerta.setDescricao("Ruptura detectada!");
					    alerta.setProduto(dados[i].getProduto());
					    alerta.setFlTipo(1); // mudar o nome desta variavel
					    alertaGateway.salvar(alerta);
					}
				} else if(resposta.getPergunta().equals("Qual o preço do produto?")) {
					int precoColetado = Integer.parseInt(resposta.getResposta());
					int precoEstipulado = Integer.parseInt(dados[i].getPreco_estipulado());
					if(precoColetado > precoEstipulado){
					    Alerta alerta = new Alerta();
					    int margem = precoEstipulado - Integer.parseInt(resposta.getResposta()); // método que calcula a margem
					    alerta.setMargem(margem);
					    alerta.setDescricao("Preço acima do estipulado!");
					    alerta.setProduto(dados[i].getProduto());
					    alerta.setPontoDeVenda(dados[i].getPonto_de_venda());
					    alerta.setFlTipo(2);
					    alertaGateway.salvar(alerta);
					} else if(precoColetado < precoEstipulado){
						Alerta alerta = new Alerta();
					    int margem = precoEstipulado - Integer.parseInt(resposta.getResposta());
					    alerta.setMargem(margem);
					    alerta.setDescricao("Preço abaixo do estipulado!");
					    alerta.setProduto(dados[i].getProduto());
					    alerta.setPontoDeVenda(dados[i].getPonto_de_venda());
					    alerta.setFlTipo(3);
					    alertaGateway.salvar(alerta);
					}
				} else {
					System.out.println("Alerta ainda não implementado!");
				}
			} 
		}
	}
}

