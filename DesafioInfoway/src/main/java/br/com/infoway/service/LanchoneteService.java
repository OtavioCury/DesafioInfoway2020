package br.com.infoway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoway.dto.LanchoneteDTO;
import br.com.infoway.modelo.Lanchonete;
import br.com.infoway.repository.LanchoneteRepository;

@Service
public class LanchoneteService {
	
	@Autowired 
	private LanchoneteRepository lanchoneteRepository;
	
	/**
	 * Método responsável por inserir uma Lanchonete no banco de dados
	 * @param lanchonete
	 * @return
	 */
	public Lanchonete inserir(LanchoneteDTO lanchonete) {
		Lanchonete lanchoneteEntity = new Lanchonete(lanchonete.getNome());
		return lanchoneteRepository.save(lanchoneteEntity);
	}
	
}
