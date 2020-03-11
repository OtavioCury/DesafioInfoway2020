package br.com.infoway.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;

public class PedidoClienteDTO {

	private List<PedidoProdutoDTO> produto_quantidade;

	@JsonCreator
	public PedidoClienteDTO(List<PedidoProdutoDTO> produto_quantidade) {
		super();
		this.produto_quantidade = produto_quantidade;
	}

	public List<PedidoProdutoDTO> getProduto_quantidade() {
		return produto_quantidade;
	}

	public void setProduto_quantidade(List<PedidoProdutoDTO> produto_quantidade) {
		this.produto_quantidade = produto_quantidade;
	}

}
