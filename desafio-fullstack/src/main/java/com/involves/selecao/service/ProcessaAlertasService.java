package com.involves.selecao.service;
import java.io.IOException;

import com.involves.selecao.enums.PerguntasEnum;
import com.involves.selecao.enums.RespostasEnum;
import com.involves.selecao.gateway.pesquisa.PesquisaGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		for (Pesquisa pesquisa : dados){
			pesquisa.getRespostas().forEach(resposta -> this.processPesquisa(resposta, pesquisa));
		}

	}

	private void processPesquisa(Resposta resposta, Pesquisa pesquisa) {
		if (resposta.getPergunta().equals(PerguntasEnum.SITUACAO_PRODUTO.getDescricao())) {
			createAlertaSituacaoProduto(resposta,pesquisa);
		} else if(resposta.getPergunta().equals(PerguntasEnum.PRECO_PRODUTO.getDescricao())) {
			createAlertaPrecoProduto(resposta,pesquisa);
		} else {
			System.out.println("Alerta ainda não implementado!");
		}
	}

	private void createAlertaPrecoProduto(Resposta resposta, Pesquisa pesquisa) {
		int margem = Integer.parseInt(pesquisa.getPreco_estipulado()) - Integer.parseInt(resposta.getResposta());

		if(margem < 0){
			Alerta alerta = new Alerta();
			alerta.setMargem(margem);
			alerta.setDescricao("Preço acima do estipulado!");
			alerta.setProduto(pesquisa.getProduto());
			alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
			alerta.setFlTipo(2);
			alertaGateway.salvar(alerta);
		} else if(margem > 0){
			Alerta alerta = new Alerta();
			alerta.setMargem(margem);
			alerta.setDescricao("Preço abaixo do estipulado!");
			alerta.setProduto(pesquisa.getProduto());
			alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
			alerta.setFlTipo(3);
			alertaGateway.salvar(alerta);
		}
	}

	private void createAlertaSituacaoProduto(Resposta resposta,Pesquisa pesquisa) {
		if(resposta.getResposta().equals(RespostasEnum.AUSENTE_GONDOLA.getDescricao())){
			Alerta alerta = new Alerta();
			alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
			alerta.setDescricao("Ruptura detectada!");
			alerta.setProduto(pesquisa.getProduto());
			alerta.setFlTipo(1);
			alertaGateway.salvar(alerta);
		}
	}
}

