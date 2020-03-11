package br.com.infoway.modelo.enums;

public enum StatusPedido {
	SOLICITADO("Solicitado"), SENDO_PREPARADO("Sendo preparado"), FINALIZADO("Finalizado");

	private String descricao;

	StatusPedido(String descricao) {
		this.setDescricao(descricao);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String statusPedido() {
		if(this.equals(StatusPedido.SOLICITADO)) {
			return "O pedido foi solicitado";
		}else if(this.equals(StatusPedido.SENDO_PREPARADO)) {
			return "O pedido está sendo preparado";
		}else if(this.equals(StatusPedido.FINALIZADO)) {
			return "O pedido foi finalizado";
		}else if(this.equals(StatusPedido.FINALIZADO)) {
			return "O pedido foi cancelado";
		}
		return null;
	}
}
