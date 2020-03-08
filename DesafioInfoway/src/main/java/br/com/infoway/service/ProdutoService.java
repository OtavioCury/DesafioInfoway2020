package br.com.infoway.service;

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


	public Produto inserir(ProdutoDTO produto) {
		Produto produtoEntity = new Produto(produto.getNome(), produto.getPreco(), 
				ClasseProduto.valueOf(produto.getClasse().toUpperCase()), produto.getQuantEstoque());
		return produtoRepository.save(produtoEntity);
	}
}
