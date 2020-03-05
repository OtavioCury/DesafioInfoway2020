package br.com.infoway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoway.repository.GestorRepository;

@Service
public class GestorService {

	@Autowired 
	private GestorRepository gestorRepository;
	
}
