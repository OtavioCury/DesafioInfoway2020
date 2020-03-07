package br.com.infoway.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.infoway.modelo.Pessoa;
import br.com.infoway.repository.PessoaRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Pessoa pessoa = pessoaRepository.findByEmail(username);
		if (pessoa != null) {
			org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(
					pessoa.getEmail(), pessoa.getSenha(), new ArrayList<>());
			return user;
		} else {
			throw new UsernameNotFoundException(String.format("Usuário '%s' não encontrado", username));
		}
	}

}
