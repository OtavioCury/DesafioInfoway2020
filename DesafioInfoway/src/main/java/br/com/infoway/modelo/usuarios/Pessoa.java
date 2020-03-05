package br.com.infoway.modelo.usuarios;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Size(min = 3, max = 50, message = "O nome deve conter entre 3 e 50 caracteres!")
	private String nome;
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Size(min = 3, max = 30, message = "O email deve conter entre 3 e 50 caracteres!")
	@Column(unique = true)
	private String email;
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Size(min = 3, max = 30, message = "O login deve conter entre 3 e 20 caracteres!")
	@Column(unique = true)
	private String login;
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Size(min = 3, max = 30, message = "A senha deve conter entre 6 e 30 caracteres!")
	private String senha;
	
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
