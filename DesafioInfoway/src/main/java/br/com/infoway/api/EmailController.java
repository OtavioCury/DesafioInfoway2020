package br.com.infoway.api;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.infoway.dto.SenhaDTO;
import br.com.infoway.modelo.PasswordResetToken;
import br.com.infoway.modelo.Pessoa;
import br.com.infoway.service.PasswordResetTokenService;
import br.com.infoway.service.PessoaService;

/**
 * Controller de endpoints relacionados à funcionalidade de recuperação de senha
 * @author Otavio
 *
 */
@RestController
@RequestMapping(value="/alterarSenha")
public class EmailController {

	@Autowired
	private PessoaService pessoaService;
	@Autowired
	private PasswordResetTokenService passwordResetTokenService;

	@GetMapping
	public ResponseEntity<Object> acessoEmail(@RequestParam("id") long id, 
			@RequestParam("token") String token) {
		String resultado = validatePasswordResetToken(id, token);
		if (resultado != null) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(resultado);
		}else {
			Pessoa pessoa = pessoaService.pesquisarPorId(id);
			pessoa.setPermitidoAlterarSenha(true);
			pessoaService.atualizar(pessoa);
			return ResponseEntity.ok("Link válido! Você pode alterar sua senha agora");
		}
	}

	@PostMapping
	public ResponseEntity<Object> alteraPassword(@RequestBody SenhaDTO senhaDto) {
		try {
			pessoaService.atualizarSenha(senhaDto);
			return ResponseEntity.ok("Senha alterado com sucesso!");
		}catch(Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(e.getMessage());
		}

	}

	public String validatePasswordResetToken(long id, String token) {
		PasswordResetToken passToken = passwordResetTokenService.pesquisaToken(token);
		if ((passToken == null) || (passToken.getPessoa()
				.getId() != id)) {
			return "Token inválido";
		}

		Calendar cal = Calendar.getInstance();
		if ((passToken.getDataExpira()
				.getTime() - cal.getTime()
				.getTime()) <= 0) {
			return "Link expirado";
		}
		return null;
	}


}
