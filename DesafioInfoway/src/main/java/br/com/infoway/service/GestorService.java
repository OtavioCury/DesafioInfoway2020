package br.com.infoway.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.infoway.dto.GestorDTO;
import br.com.infoway.exception.MensagemException;
import br.com.infoway.modelo.Gestor;
import br.com.infoway.modelo.Lanchonete;
import br.com.infoway.repository.GestorRepository;

@Service
public class GestorService {

	@Autowired 
	private GestorRepository gestorRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	/**
	 * Método responsável por inserir um Gestor no banco de dados
	 * @param gestor
	 * @param lanchonete
	 * @return
	 * @throws Exception 
	 */
	public Gestor inserir(GestorDTO gestor, Lanchonete lanchonete) throws Exception {
		List<Gestor> gestores = gestorRepository.findAll();
		if (gestores == null || gestores.size() == 0) {
			Gestor gestorEntity = new Gestor(gestor.getNome(), gestor.getEmail(), bcryptEncoder.encode(gestor.getSenha()), lanchonete);
			return gestorRepository.save(gestorEntity);
		}else {
			throw new Exception(MensagemException.jaExisteGestor);
		}
		
	}
}
