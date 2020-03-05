package br.com.infoway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.infoway.modelo.Lanchonete;

@Repository
public interface LanchoneteRepository extends JpaRepository<Lanchonete, Long>{

	Lanchonete findByNome(String nome);
}
