package br.com.infoway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.infoway.modelo.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	
	@Query("SELECT p FROM Pedido p inner join Cliente c on (p.cliente.id = c.id) WHERE p.status = 'FINALIZADO'")
	List<Pedido> pedidosFinalizados(long id);
}
