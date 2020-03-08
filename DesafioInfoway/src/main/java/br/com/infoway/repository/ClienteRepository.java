package br.com.infoway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.infoway.modelo.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	Cliente findByEmail(String email);
}
