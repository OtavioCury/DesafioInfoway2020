package br.com.infoway.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoway.dto.ProdutoDTO;
import br.com.infoway.modelo.Produto;
import br.com.infoway.modelo.enums.ClasseProduto;
import br.com.infoway.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired 
	private ProdutoRepository produtoRepository;

	/**
	 * Método que insere um produto no banco de dados
	 * @param produto
	 * @return
	 */
	public Produto inserir(ProdutoDTO produto) {
		Produto produtoEntity = new Produto(produto.getNome(), produto.getPreco(), 
				ClasseProduto.valueOf(produto.getClasse().toUpperCase()), produto.getQuantEstoque());
		return produtoRepository.save(produtoEntity);
	}

	/**
	 * Método que atualiza um produto no banco de dados
	 * @param produto
	 * @return
	 */
	public Produto atualizar(ProdutoDTO produto) {
		Produto produtoEntity = new Produto(produto.getId(), produto.getNome(), produto.getPreco(), 
				ClasseProduto.valueOf(produto.getClasse().toUpperCase()), produto.getQuantEstoque());
		return produtoRepository.save(produtoEntity);
	}

	/**
	 * Método responsável por remover um produto do banco de dados
	 * @param id
	 * @return
	 */
	public String remover(long id) {
		try {
			produtoRepository.deleteById(id);
			return "Produto removido com sucesso!";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	/**
	 * Método responsável por listar todos os produtos do banco de dados
	 * @return
	 */
	public List<Produto> listar(){
		return produtoRepository.findAll();
	}
}
