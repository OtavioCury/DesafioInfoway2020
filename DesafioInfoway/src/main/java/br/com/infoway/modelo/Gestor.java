package br.com.infoway.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade que representa um Gestor da lanchonete
 * @author Otavio
 *
 */
@Entity
public class Gestor extends Pessoa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Atributo lanchonete: Preenchimento obrigatório!")
	@OneToOne
	private Lanchonete lanchonete;

	public Gestor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Gestor(
			@NotEmpty(message = "Preenchimento obrigatório!") @Size(min = 3, max = 50, message = "O nome deve conter entre 3 e 50 caracteres!") String nome,
			@NotEmpty(message = "Preenchimento obrigatório!") @Size(min = 3, max = 30, message = "O email deve conter entre 3 e 50 caracteres!") String email,
			@NotEmpty(message = "Preenchimento obrigatório!") @Size(min = 3, max = 30, message = "A senha deve conter entre 6 e 30 caracteres!") String senha,
			Lanchonete lanchonete) {
		super(nome, email, senha);
		this.lanchonete = lanchonete;
		setRole("GESTOR");
	}

	public Lanchonete getLanchonete() {
		return lanchonete;
	}

	public void setLanchonete(Lanchonete lanchonete) {
		this.lanchonete = lanchonete;
	}

}
