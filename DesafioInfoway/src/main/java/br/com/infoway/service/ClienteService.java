package br.com.infoway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.infoway.dto.ClienteDTO;
import br.com.infoway.modelo.Cliente;
import br.com.infoway.repository.ClienteRepository;

/**
 * Classe de Service da entidade Cliente
 * @author Otavio
 *
 */
@Service
public class ClienteService {
	
	@Autowired 
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	/**
	 * Método responsável por inserir um cliente no banco de dados
	 * @param cliente
	 * @return
	 */
	public Cliente inserir(ClienteDTO cliente) {
		Cliente clienteEntity = new Cliente(cliente.getNome(), cliente.getEmail(), bcryptEncoder.encode(cliente.getSenha()), 
				cliente.getDataNascimento(), cliente.getTelefone());
		return clienteRepository.save(clienteEntity);
	}
	
}
