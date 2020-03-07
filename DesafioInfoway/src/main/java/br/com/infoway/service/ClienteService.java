package br.com.infoway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoway.dto.ClienteDTO;
import br.com.infoway.modelo.Cliente;
import br.com.infoway.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired 
	private ClienteRepository clienteRepository;

	public Cliente inserir(ClienteDTO cliente) {
		Cliente clienteEntity = new Cliente(cliente.getNome(), cliente.getEmail(), cliente.getSenha(), 
				cliente.getDataNascimento(), cliente.getTelefone());
		return clienteRepository.save(clienteEntity);
	}
	
}
