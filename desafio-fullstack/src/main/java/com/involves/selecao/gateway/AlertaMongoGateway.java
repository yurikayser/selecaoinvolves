package com.involves.selecao.gateway;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.gateway.mongo.MongoDbFactory;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Component
public class AlertaMongoGateway implements AlertaGateway{

	private final String ALERTA_CONSTANT= "alertas";

	@Autowired
	private MongoDbFactory mongoFactory;


	@Override
	public void salvar(Alerta alerta) {
		MongoDatabase database = mongoFactory.getDb();
		MongoCollection<Document> collection = database.getCollection(this.ALERTA_CONSTANT);
		Document doc = new Document("ponto_de_venda", alerta.getPontoDeVenda())
                .append("descricao", alerta.getDescricao())
                .append("tipo", alerta.getFlTipo())
                .append("margem", alerta.getMargem())
                .append("produto", alerta.getProduto());
		collection.insertOne(doc);
	}

	@Override
	public List<Alerta> buscarTodos() {
		List<Alerta> alertas = new ArrayList<>();
		MongoDatabase database = mongoFactory.getDb();
		FindIterable<Document> table = database.getCollection(this.ALERTA_CONSTANT).find();

		for (Document document : table) {
			Alerta alerta = new Alerta();
			alerta.setDescricao(document.getString("descricao"));
			alerta.setFlTipo(document.getInteger("tipo"));
			alerta.setMargem(document.getInteger("margem"));
			alerta.setPontoDeVenda(document.getString("ponto_de_venda"));
			alerta.setProduto(document.getString("produto"));
			alertas.add(alerta);
		}

		return alertas;
	}
}
