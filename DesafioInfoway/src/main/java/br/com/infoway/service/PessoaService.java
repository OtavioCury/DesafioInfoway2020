package br.com.infoway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoway.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired 
	private PessoaRepository pessoaRepository;
	
	
}
