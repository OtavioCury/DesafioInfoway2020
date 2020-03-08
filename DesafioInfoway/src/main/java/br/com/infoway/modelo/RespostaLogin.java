package br.com.infoway.modelo;

import br.com.infoway.config.JwtResponse;

public class RespostaLogin {
	
	private JwtResponse respostaToken;
	private Pessoa pessoa;
	
	public RespostaLogin(JwtResponse respostaToken, Pessoa pessoa) {
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
