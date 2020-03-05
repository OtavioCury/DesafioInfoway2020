package br.com.infoway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoway.repository.LanchoneteRepository;

@Service
public class LanchoneteService {
	
	@Autowired 
	private LanchoneteRepository lanchoneteRepository;
	
}
