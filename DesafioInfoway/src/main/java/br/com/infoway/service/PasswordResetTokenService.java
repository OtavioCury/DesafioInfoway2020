package br.com.infoway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoway.modelo.PasswordResetToken;
import br.com.infoway.modelo.Pessoa;
import br.com.infoway.repository.PasswordResetTokenRepository;

@Service
public class PasswordResetTokenService {
	@Autowired
	private PasswordResetTokenRepository passwordTokenRepository;
	
	/**
	 * Método responsável por salvar um token de uma Pessoa no banco de dados
	 * @param pessoa
	 * @param token
	 */
	public void criaToken(Pessoa pessoa, String token) {
		PasswordResetToken tokenEntity = new PasswordResetToken(token, pessoa);
		passwordTokenRepository.save(tokenEntity);
	}
	
	/**
	 * Pesquisa um objeto PasswordResetToken pela string token
	 * @param token
	 * @return
	 */
	public PasswordResetToken pesquisaToken(String token) {
		return passwordTokenRepository.findByToken(token);
	}
}
