package br.com.infoway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.infoway.modelo.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

	/**
	 * Pesquisa uma pessoa por email
	 * @param email
	 * @return
	 */
	Pessoa findByEmail(String email);
}
