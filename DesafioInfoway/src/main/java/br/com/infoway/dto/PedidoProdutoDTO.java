package br.com.infoway.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PedidoProdutoDTO {
	
	private long produto;
	private int quantidade;
	
	@JsonCreator
	public PedidoProdutoDTO(@JsonProperty("id_produto") long produto, int quantidade) {
		super();
		this.setProduto(produto);
		this.quantidade = quantidade;
	}
	

	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public long getProduto() {
		return produto;
	}
	public void setProduto(long produto) {
		this.produto = produto;
	}
		
}
