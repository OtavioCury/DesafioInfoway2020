package br.com.infoway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.infoway.modelo.Gestor;

@Repository
public interface GestorRepostory extends JpaRepository<Gestor, Long>{

}
