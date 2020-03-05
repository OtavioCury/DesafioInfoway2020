package br.com.infoway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.infoway.modelo.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}
