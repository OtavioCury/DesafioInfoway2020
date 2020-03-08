package br.com.infoway.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("ROLE_"+pessoa.getRole()));
			org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(
					pessoa.getEmail(), pessoa.getSenha(), authorities);
			return user;
		} else {
			throw new UsernameNotFoundException(String.format("Usuário '%s' não encontrado", username));
		}
	}

}
