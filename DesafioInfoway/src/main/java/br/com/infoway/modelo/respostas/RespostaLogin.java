package br.com.infoway.modelo.respostas;

import br.com.infoway.config.JwtResponse;
import br.com.infoway.modelo.Pessoa;

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
