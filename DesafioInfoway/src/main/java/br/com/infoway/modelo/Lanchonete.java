package br.com.infoway.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Entidade que representa uma lanchonete
 * @author Otavio
 *
 */
@Entity
public class Lanchonete implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Atributo nome: Preenchimento obrigatório!")
	@Size(min = 3, max = 50, message = "O nome deve conter entre 3 e 50 caracteres!")
	private String nome;

	public Lanchonete(
			@NotEmpty(message = "Preenchimento obrigatório!") @Size(min = 3, max = 50, message = "O nome deve conter entre 3 e 50 caracteres!") String nome) {
		super();
		this.nome = nome;
	}

	public Lanchonete() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
