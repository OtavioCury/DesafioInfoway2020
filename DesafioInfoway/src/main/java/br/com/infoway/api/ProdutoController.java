package br.com.infoway.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.infoway.dto.ProdutoDTO;
import br.com.infoway.service.ProdutoService;

@RestController
@RequestMapping(value="/produto")
public class ProdutoController {
	@Autowired
	private ProdutoService produtoService;
	
	@PreAuthorize("hasRole('GESTOR')")
	@PostMapping
	public ResponseEntity<Object> salvarProduto(@RequestBody ProdutoDTO produto){
		try {
			return ResponseEntity.ok(produtoService.inserir(produto));
		} catch(javax.validation.ConstraintViolationException e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(e.getMessage());
		}

	}
}
