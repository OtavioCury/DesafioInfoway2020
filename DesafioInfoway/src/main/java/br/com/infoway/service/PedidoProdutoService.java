package br.com.infoway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoway.repository.PedidoProdutoRepository;

@Service
public class PedidoProdutoService {
	
	@Autowired 
	private PedidoProdutoRepository pedidoProdutoRepository;

}
