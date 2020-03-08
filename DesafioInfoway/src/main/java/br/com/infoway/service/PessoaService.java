package br.com.infoway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.infoway.dto.PessoaDTO;
import br.com.infoway.modelo.Pessoa;
import br.com.infoway.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired 
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	/**
	 * Método responsável por inserir uma pessoa no banco de dados
	 * @param pessoa
	 * @return
	 */
	public Pessoa inserir(PessoaDTO pessoa) {
		Pessoa pessoaEntity = new Pessoa(pessoa.getNome(), pessoa.getEmail(), bcryptEncoder.encode(pessoa.getSenha()));
		return pessoaRepository.save(pessoaEntity);
	}
	
	/**
	 * Método responsável por consultar uma pessoa no banco de dados por email e senha
	 * @param email
	 * @param senha
	 * @return
	 */
	public Pessoa login(String email, String senha) {
		return pessoaRepository.login(email, senha);
	}
	
}
