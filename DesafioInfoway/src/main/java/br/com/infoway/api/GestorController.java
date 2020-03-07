package br.com.infoway.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.infoway.dto.GestorDTO;
import br.com.infoway.dto.LanchoneteDTO;
import br.com.infoway.exception.MensagemException;
import br.com.infoway.modelo.Lanchonete;
import br.com.infoway.service.GestorService;
import br.com.infoway.service.LanchoneteService;

@RestController
@RequestMapping(value="/gestor")
public class GestorController {

	@Autowired
	private GestorService gestorService;
	@Autowired
	private LanchoneteService lanchoneteService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> salvarGestor(@RequestBody GestorDTO gestor) throws Exception {
		try {
			LanchoneteDTO lanchoneteDTO = new LanchoneteDTO(gestor.getLanchonete());
			Lanchonete lanchonete = lanchoneteService.inserir(lanchoneteDTO);
			return ResponseEntity.ok(gestorService.inserir(gestor, lanchonete));
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(MensagemException.emailExistente);
		} catch(javax.validation.ConstraintViolationException e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(e.getMessage());
		}
	}

}
