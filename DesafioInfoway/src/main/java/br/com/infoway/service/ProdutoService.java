package br.com.infoway.service;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.infoway.repository.ProdutoRepository;

public class ProdutoService {
	
	@Autowired 
	private ProdutoRepository produtoRepository;
}
