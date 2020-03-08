package br.com.infoway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.infoway.dto.GestorDTO;
import br.com.infoway.modelo.Gestor;
import br.com.infoway.modelo.Lanchonete;
import br.com.infoway.repository.GestorRepository;

@Service
public class GestorService {

	@Autowired 
	private GestorRepository gestorRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	public Gestor inserir(GestorDTO gestor, Lanchonete lanchonete) {
		Gestor gestorEntity = new Gestor(gestor.getNome(), gestor.getEmail(), bcryptEncoder.encode(gestor.getSenha()), lanchonete);
		return gestorRepository.save(gestorEntity);
	}
}
