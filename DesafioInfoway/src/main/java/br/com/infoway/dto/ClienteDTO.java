package br.com.infoway.dto;

import java.util.Date;

public class ClienteDTO extends PessoaDTO{
	
	private String telefone;
	private Date dataNascimento;

	public ClienteDTO(String nome, String email, String senha, String telefone, Date dataNascimento) {
		super(nome, email, senha);
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	
}
