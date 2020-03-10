package br.com.infoway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.infoway.dto.PessoaDTO;
import br.com.infoway.dto.SenhaDTO;
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

	public Pessoa atualizar(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}
	
	public Pessoa atualizarSenha(SenhaDTO senhaDTO) {
		Pessoa pessoa = pessoaRepository.findById(senhaDTO.getId()).get();
		pessoa.setPermitidoAlterarSenha(false);
		pessoa.setSenha(bcryptEncoder.encode(senhaDTO.getSenha()));
		return pessoaRepository.save(pessoa);
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

	public Pessoa pesquisarPorEmail(String email) {
		return pessoaRepository.findByEmail(email);
	}

	public Pessoa pesquisarPorId(Long id) {
		return pessoaRepository.findById(id).get();
	}

}
