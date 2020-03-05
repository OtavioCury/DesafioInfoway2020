package br.com.infoway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoway.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired 
	private PedidoRepository pedidoRepository;
	
}
