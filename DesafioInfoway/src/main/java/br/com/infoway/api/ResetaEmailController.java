package br.com.infoway.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.infoway.config.JwtTokenUtil;
import br.com.infoway.config.JwtUserDetailsService;
import br.com.infoway.exception.MensagemException;
import br.com.infoway.modelo.Pessoa;
import br.com.infoway.service.PasswordResetTokenService;
import br.com.infoway.service.PessoaService;

/**
 * Controller de endpoints relacionados à funcionalidade de recuperação de senha
 * @author Otavio
 *
 */
@RestController
@RequestMapping(value="/email")
public class ResetaEmailController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private JwtUserDetailsService userDetailsService;
	@Autowired
	private PessoaService pessoaService;
	@Autowired
	private PasswordResetTokenService passwordResetTokenService;
	@Autowired 
	private JavaMailSender mailSender;
	@Autowired
	private Environment env;

	@PostMapping
	public ResponseEntity<Object> resetaEmailPassword(@RequestParam("email") String email) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(email);
		String token = jwtTokenUtil.generateToken(userDetails);
		if (userDetails == null) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Email não encontrado!");
		}
		Pessoa pessoa = pessoaService.pesquisarPorEmail(email);
		passwordResetTokenService.criaToken(pessoa, token);
		try {
			mailSender.send(constructResetTokenEmail(token, pessoa));
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(MensagemException.falhaEnvioEmail);
		}

		return ResponseEntity.ok("Email enviado para "+pessoa.getEmail());
	}

	private SimpleMailMessage constructResetTokenEmail(String token, Pessoa pessoa) {
		String url = "localhost:8080/alterarSenha?id=" + 
				pessoa.getId() + "&token=" + token;
		return constructEmail("Recupera senha", url, pessoa);
	}

	private SimpleMailMessage constructEmail(String subject, String body, 
			Pessoa pessoa) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject(subject);
		email.setText("Link para recuperação de email: "+body);
		email.setTo(pessoa.getEmail());
		email.setFrom(env.getProperty("spring.mail.username"));
		return email;
	}

}
