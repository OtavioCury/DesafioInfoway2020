package br.com.infoway.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.infoway.dto.PedidoClienteDTO;
import br.com.infoway.dto.PedidoGestorDTO;
import br.com.infoway.modelo.Cliente;
import br.com.infoway.service.PedidoService;
import br.com.infoway.service.PessoaService;

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
	@Autowired
	private PessoaService pessoaService;

	/**
	 * Endpoint responsável por salvar um pedido de um cliente
	 * @param pedido
	 * @return
	 */
	@PreAuthorize("hasRole('CLIENTE')")
	@PostMapping(path = "/cliente")
	public ResponseEntity<Object> salvarPedido(@RequestBody PedidoClienteDTO pedido){
		try {
			Cliente cliente = clienteToken();
			return ResponseEntity.ok(pedidoService.inserir(pedido, cliente));
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(e.getMessage());
		}
	}

	/**
	 * Endpoint responsável por atualizar um pedido por um gestor
	 * @param pedido
	 * @return
	 */
	@PreAuthorize("hasRole('GESTOR')")
	@PostMapping(path = "/gestor")
	public ResponseEntity<Object> atualizarStatus(@RequestBody PedidoGestorDTO pedido){
		try {
			return ResponseEntity.ok(pedidoService.atualizarStatus(pedido));
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(e.getMessage());
		}
	}

	/**
	 * Endpoint responsável por consultar o status de um pedido por um gestor
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasRole('GESTOR')")
	@GetMapping(path = "/gestor/{id}")
	public ResponseEntity<Object> consultarStatusPedidoGestor(@PathVariable long id){
		try {
			return ResponseEntity.ok(pedidoService.statusPedido(id));
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(e.getMessage());
		}

	}

	/**
	 * Endpoint responsável por consultar o status de um pedido por um cliente
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasRole('CLIENTE')")
	@GetMapping(path = "/cliente/{id}")
	public ResponseEntity<Object> consultarStatusPedido(@PathVariable long id){
		try {
			Cliente cliente = clienteToken();
			return ResponseEntity.ok(pedidoService.statusPedido(id, cliente));
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(e.getMessage());
		}

	}

	/**
	 * Endpoint responsável por consultar os pedidos finalizados de um cliente
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasRole('CLIENTE')")
	@GetMapping(path = "/cliente")
	public ResponseEntity<Object> pedidosFinalizados(){
		try {
			Cliente cliente = clienteToken();
			return ResponseEntity.ok(pedidoService.pedidosFinalizados(cliente));
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(e.getMessage());
		}
	}

	/**
	 * Endpoint responsável pelo cancelamento de um pedido por um cliente
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasRole('CLIENTE')")
	@DeleteMapping(path = "/cliente/{id}")
	public ResponseEntity<Object> cancelarPedido(@PathVariable long id){
		try {
			Cliente cliente = clienteToken();
			return ResponseEntity.ok(pedidoService.cancelar(id, cliente));
		} catch(Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(e.getMessage());
		}
	}

	/**
	 * Método responsável por extrair um objeto Cliente de um token
	 * @return
	 */
	private Cliente clienteToken() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		Cliente cliente = (Cliente) pessoaService.login(user.getUsername(), user.getPassword());
		return cliente;
	}

}
