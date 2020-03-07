package br.com.infoway.dto;

public class LanchoneteDTO {
	
	public LanchoneteDTO(String nome) {
		super();
		this.nome = nome;
	}

	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
