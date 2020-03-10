package br.com.infoway.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PedidoDTO {
	
	private List<PedidoProdutoDTO> pedidosProdutos;

	@JsonCreator
	public PedidoDTO(@JsonProperty("id_cliente") long cliente, 
			@JsonProperty("produto_quantidade") List<PedidoProdutoDTO> pedidosProdutos) {
		super();
		this.pedidosProdutos = pedidosProdutos;
	}

	public List<PedidoProdutoDTO> getPedidosProdutos() {
		return pedidosProdutos;
	}
	public void setPedidosProdutos(List<PedidoProdutoDTO> pedidosProdutos) {
		this.pedidosProdutos = pedidosProdutos;
	}

}
