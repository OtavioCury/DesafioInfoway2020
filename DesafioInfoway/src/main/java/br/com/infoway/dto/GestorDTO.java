package br.com.infoway.dto;

public class GestorDTO extends PessoaDTO{
	
	private String lanchonete;

	public GestorDTO(String nome, String email, String senha) {
		super(nome, email, senha);
	}

	public String getLanchonete() {
		return lanchonete;
	}

	public void setLanchonete(String lanchonete) {
		this.lanchonete = lanchonete;
	}
	
}
