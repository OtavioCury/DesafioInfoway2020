package br.com.infoway.modelo.enums;

public enum StatusPedido {
	SOLICITADO("Solicitado"), FINALIZADO("Finalizado"), CANCELADO("Cancelado");

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
}
