package br.com.infoway.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.infoway.config.JwtRequest;
import br.com.infoway.config.JwtResponse;
import br.com.infoway.config.JwtTokenUtil;
import br.com.infoway.config.JwtUserDetailsService;
import br.com.infoway.modelo.respostas.RespostaLogin;
import br.com.infoway.service.PessoaService;

/**
 * Controller reponsável por login e geração de tokens
 * @author Otavio
 *
 */
@RestController
@CrossOrigin
public class LoginController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	private PessoaService pessoaService;

	/**
	 * Endpoint responsável por verificar as credenciais de um usuário, 
	 * gerar um token e enviá-lo junto com o objeto Pessoa
	 * @param authenticationRequest
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest){
		try {
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

			final String token = jwtTokenUtil.generateToken(userDetails);

			RespostaLogin resposta = new RespostaLogin(new JwtResponse(token), 
					pessoaService.login(userDetails.getUsername(), userDetails.getPassword()));

			return ResponseEntity.ok(resposta);

		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(e.getMessage());
		}
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("Usuário desabilitado!", e);
		} catch (BadCredentialsException e) {
			throw new Exception("Credenciais inválidas!", e);
		}
	}
}
