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

	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Size(min = 3, max = 50, message = "O telefone deve conter entre 8 e 15 caracteres!")
	private String telefone;
	
	
}
