package br.com.infoway.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.infoway.dto.GestorDTO;
import br.com.infoway.dto.LanchoneteDTO;
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
	public ResponseEntity<?> salvarGestor(@RequestBody GestorDTO gestor) throws Exception {
		LanchoneteDTO lanchoneteDTO = new LanchoneteDTO(gestor.getLanchonete());
		Lanchonete lanchonete = lanchoneteService.inserir(lanchoneteDTO);
		return ResponseEntity.ok(gestorService.inserir(gestor, lanchonete));
	}

}
