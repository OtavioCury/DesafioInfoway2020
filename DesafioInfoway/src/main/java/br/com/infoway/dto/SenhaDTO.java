package br.com.infoway.dto;

public class SenhaDTO {
	private long id;
	private String senha;
	public SenhaDTO(long id, String senha) {
		super();
		this.id = id;
		this.senha = senha;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
