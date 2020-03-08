package br.com.infoway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
	
	@Query("SELECT p FROM Pessoa p WHERE p.email = :email and p.senha = :senha")
	Pessoa login(String email, String senha);
}
