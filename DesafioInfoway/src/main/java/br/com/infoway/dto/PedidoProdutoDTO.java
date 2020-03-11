package br.com.infoway.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class PedidoProdutoDTO {

	private long id_produto;
	private int quantidade;

	@JsonCreator
	public PedidoProdutoDTO(long id_produto, int quantidade) {
		super();
		this.setId_produto(id_produto);
		this.quantidade = quantidade;
	}


	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}


	public long getId_produto() {
		return id_produto;
	}


	public void setId_produto(long id_produto) {
		this.id_produto = id_produto;
	}


}
