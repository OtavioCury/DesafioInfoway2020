package br.com.infoway.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.infoway.service.PedidoService;

/**
 * Controller de endpoints relacionados a entidade Pedido
 * @author Otavio
 *
 */
@RestController
@RequestMapping(value="/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	/**
	 * Endpoint responsável por salvar um pedido
	 * @param pedido
	 * @return
	 */
//	@PreAuthorize("hasRole('CLIENTE')")
//	@PostMapping
//	public ResponseEntity<Object> salvarProduto(@RequestBody PedidoDTO pedido){
//		try {
//			return ResponseEntity.ok(pedidoService.inserir(pedido));
//		} catch(javax.validation.ConstraintViolationException e) {
//			return ResponseEntity
//					.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body(e.getMessage());
//		}
//	}

}
