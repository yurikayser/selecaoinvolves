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

	public void processPesquisa(Resposta resposta, Pesquisa pesquisa) {
		Alerta alerta;
		if (resposta.getPergunta().equals(PerguntasEnum.SITUACAO_PRODUTO.getDescricao())) {
			alerta = createAlertaSituacaoProduto(resposta,pesquisa);
		} else if(resposta.getPergunta().equals(PerguntasEnum.PRECO_PRODUTO.getDescricao())) {
			alerta = createAlertaPrecoProduto(resposta,pesquisa);
		} else{
			alerta = this.createAlertaParticipacao(resposta,pesquisa);
		}
//		this.alertaGateway.salvar(alerta);
	}

	public Alerta createAlertaParticipacao(Resposta resposta, Pesquisa pesquisa) {
		Alerta alerta = null;
		int margem = Integer.parseInt(pesquisa.getParticipacao_estipulada()) - Integer.parseInt(resposta.getResposta());

		if(margem > 0){
			alerta = new Alerta();
			alerta.setMargem(margem);
			alerta.setDescricao("Participação superior ao estipulado");
			alerta.setCategoria(pesquisa.getCategoria());
			alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
			alerta.setFlTipo(4);
		}else if(margem < 0){
			alerta = new Alerta();
			alerta.setMargem(margem);
			alerta.setDescricao("Participação inferior ao estipulado");
			alerta.setCategoria(pesquisa.getCategoria());
			alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
			alerta.setFlTipo(5);
		}
		return alerta;
	}

	public Alerta createAlertaPrecoProduto(Resposta resposta, Pesquisa pesquisa) {
		int margem = Integer.parseInt(pesquisa.getPreco_estipulado()) - Integer.parseInt(resposta.getResposta());
		Alerta alerta = null;
		if(margem < 0){
			alerta = new Alerta();
			alerta.setMargem(margem);
			alerta.setDescricao("Preço acima do estipulado!");
			alerta.setProduto(pesquisa.getProduto());
			alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
			alerta.setFlTipo(2);
		} else if(margem > 0){
			alerta = new Alerta();
			alerta.setMargem(margem);
			alerta.setDescricao("Preço abaixo do estipulado!");
			alerta.setProduto(pesquisa.getProduto());
			alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
			alerta.setFlTipo(3);
		}
		return alerta;
	}

	public Alerta createAlertaSituacaoProduto(Resposta resposta, Pesquisa pesquisa) {
		Alerta alerta = null;
		if(resposta.getResposta().equals(RespostasEnum.AUSENTE_GONDOLA.getDescricao())){
			alerta = new Alerta();
			alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
			alerta.setDescricao("Ruptura detectada!");
			alerta.setProduto(pesquisa.getProduto());
			alerta.setFlTipo(1);
		}
		return alerta;
	}
}

