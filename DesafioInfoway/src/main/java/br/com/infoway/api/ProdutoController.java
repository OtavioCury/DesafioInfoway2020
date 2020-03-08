package br.com.infoway.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.infoway.dto.ProdutoDTO;
import br.com.infoway.service.ProdutoService;

/**
 * Controller de endpoints relacionados a entidade Produto
 * @author Otavio
 *
 */
@RestController
@RequestMapping(value="/produto")
public class ProdutoController {
	@Autowired
	private ProdutoService produtoService;

	/**
	 * Endpoint responsável por salvar um produto
	 * @param produto
	 * @return
	 */
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

	/**
	 * Endpoint responsável por atualizar um produto
	 * @param produto
	 * @return
	 */
	@PreAuthorize("hasRole('GESTOR')")
	@PutMapping
	public ResponseEntity<Object> editarProduto(@RequestBody ProdutoDTO produto){
		try {
			return ResponseEntity.ok(produtoService.atualizar(produto));
		} catch(javax.validation.ConstraintViolationException e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(e.getMessage());
		}
	}
	
	/**
	 * Endpoint responsável por remover um produto
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasRole('GESTOR')")
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> removerProduto(@PathVariable long id){
		try {
			return ResponseEntity.ok(produtoService.remover(id));
		} catch(Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(e.getMessage());
		}
	}
	
	/**
	 * Método responsável por listar todos os produtos cadastrados
	 * @return
	 */
	@GetMapping
	public ResponseEntity<Object> listar(){
		try {
			return ResponseEntity.ok(produtoService.listar());
		} catch(Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(e.getMessage());
		}
	}
}
