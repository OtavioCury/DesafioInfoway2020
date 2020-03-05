package br.com.infoway.modelo.enums;

public enum ClasseProduto {
	BEBIDA("Bebida"), COMIDA("Comida"), SOBREMESA("Sobremesa");

	private String descricao;

	ClasseProduto(String descricao) {
		this.setDescricao(descricao);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
