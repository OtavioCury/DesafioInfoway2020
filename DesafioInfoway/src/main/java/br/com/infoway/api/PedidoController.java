package br.com.infoway.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.infoway.dto.PedidoDTO;
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
	 * Endpoint responsável por salvar um pedido
	 * @param pedido
	 * @return
	 */
	@PreAuthorize("hasRole('CLIENTE')")
	@PostMapping
	public ResponseEntity<Object> salvarPedido(@RequestBody PedidoDTO pedido){
		try {
			Cliente cliente = clienteToken();
			return ResponseEntity.ok(pedidoService.inserir(pedido, cliente));
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(e.getMessage());
		}

	}

	private Cliente clienteToken() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		Cliente cliente = (Cliente) pessoaService.login(user.getUsername(), user.getPassword());
		return cliente;
	}

}
