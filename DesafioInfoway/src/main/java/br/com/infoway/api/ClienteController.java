package br.com.infoway.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.infoway.dto.ClienteDTO;
import br.com.infoway.service.ClienteService;

/**
 * Controller de endpoints relacionados a entidade Cliente
 * @author Otavio
 *
 */
@RestController
@RequestMapping(value="/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> salvarCliente(@RequestBody ClienteDTO cliente){
		try {
			return ResponseEntity.ok(clienteService.inserir(cliente));
		}catch(Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(e.getMessage());
		}

	}

}
