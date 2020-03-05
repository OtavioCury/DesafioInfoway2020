package br.com.infoway.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.infoway.modelo.enums.ClasseProduto;

@Entity
public class Produto implements Serializable{

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
	@NotNull(message = "Preenchimento obrigatório!")
	@DecimalMin(value = "0", message = "O preço deve ser maior que 0!")
	private Double preco;
	@Enumerated(EnumType.STRING)
	private ClasseProduto classe;
	@NotNull(message = "Preenchimento obrigatório!")
	private Integer quantEstoque;
	
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
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public ClasseProduto getClasse() {
		return classe;
	}
	public void setClasse(ClasseProduto classe) {
		this.classe = classe;
	}

}
