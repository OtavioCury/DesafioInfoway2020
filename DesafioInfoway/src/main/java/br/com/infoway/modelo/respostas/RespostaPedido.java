package br.com.infoway.modelo.respostas;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.infoway.modelo.Pedido;
import br.com.infoway.modelo.Produto;

public class RespostaPedido implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pedido pedido;
	private List<Produto> produtos;
	@JsonCreator
	public RespostaPedido(Pedido pedido, List<Produto> produtos) {
		super();
		this.pedido = pedido;
		this.produtos = produtos;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
}
