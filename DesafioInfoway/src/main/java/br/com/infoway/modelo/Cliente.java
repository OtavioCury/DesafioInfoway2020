package br.com.infoway.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Cliente extends Pessoa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Size(min = 3, max = 50, message = "O telefone deve conter entre 8 e 15 caracteres!")
	private String telefone;
	
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cliente(
			@NotEmpty(message = "Preenchimento obrigatório!") @Size(min = 3, max = 50, message = "O nome deve conter entre 3 e 50 caracteres!") String nome,
			@NotEmpty(message = "Preenchimento obrigatório!") @Size(min = 3, max = 30, message = "O email deve conter entre 3 e 50 caracteres!") String email,
			@NotEmpty(message = "Preenchimento obrigatório!") @Size(min = 3, max = 30, message = "A senha deve conter entre 6 e 30 caracteres!") String senha,
			Date dataNascimento, String telefone) {
		super(nome, email, senha);
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		setRole("CLIENTE");
	}
	
	
	
	
}
