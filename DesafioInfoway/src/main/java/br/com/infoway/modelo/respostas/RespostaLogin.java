package br.com.infoway.modelo.respostas;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.infoway.config.JwtResponse;
import br.com.infoway.modelo.Pessoa;

public class RespostaLogin {
	
	private JwtResponse respostaToken;
	private Pessoa pessoa;
	
	@JsonCreator
	public RespostaLogin(@JsonProperty("respostaToken") JwtResponse respostaToken, @JsonProperty("pessoa") Pessoa pessoa) {
		super();
		this.respostaToken = respostaToken;
		this.pessoa = pessoa;
	}
	public JwtResponse getRespostaToken() {
		return respostaToken;
	}
	public void setRespostaToken(JwtResponse respostaToken) {
		this.respostaToken = respostaToken;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	
}
