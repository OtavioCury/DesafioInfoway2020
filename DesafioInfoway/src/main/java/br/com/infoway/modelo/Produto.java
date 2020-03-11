package br.com.infoway.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@NotEmpty(message = "Atributo nome: Preenchimento obrigatório!")
	@Size(min = 3, max = 50, message = "O nome deve conter entre 3 e 50 caracteres!")
	private String nome;
	@NotNull(message = "Atributo preco: Preenchimento obrigatório!")
	@DecimalMin(value = "0", message = "O preço deve ser maior que 0!")
	private Double preco;
	@NotNull(message = "Atributo classe: Preenchimento obrigatório!")
	@Enumerated(EnumType.STRING)
	private ClasseProduto classe;
	@NotNull(message = "Atributo quantEstoque: Preenchimento obrigatório!")
	private Integer quantEstoque;
	
	@JsonIgnore
	@OneToMany(mappedBy = "produto")
    private List<PedidoProduto> pedidosProdutos;
	
	public Produto(
			@NotEmpty(message = "Preenchimento obrigatório!") @Size(min = 3, max = 50, message = "O nome deve conter entre 3 e 50 caracteres!") String nome,
			@NotNull(message = "Preenchimento obrigatório!") @DecimalMin(value = "0", message = "O preço deve ser maior que 0!") Double preco,
			ClasseProduto classe, @NotNull(message = "Preenchimento obrigatório!") Integer quantEstoque) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.classe = classe;
		this.quantEstoque = quantEstoque;
	}

	public Produto(Long id,
			@NotEmpty(message = "Preenchimento obrigatório!") @Size(min = 3, max = 50, message = "O nome deve conter entre 3 e 50 caracteres!") String nome,
			@NotNull(message = "Preenchimento obrigatório!") @DecimalMin(value = "0", message = "O preço deve ser maior que 0!") Double preco,
			ClasseProduto classe, @NotNull(message = "Preenchimento obrigatório!") Integer quantEstoque) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.classe = classe;
		this.quantEstoque = quantEstoque;
	}



	public Produto() {
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
	public Integer getQuantEstoque() {
		return quantEstoque;
	}
	public void setQuantEstoque(Integer quantEstoque) {
		this.quantEstoque = quantEstoque;
	}
	public List<PedidoProduto> getPedidosProdutos() {
		return pedidosProdutos;
	}
	public void setPedidosProdutos(List<PedidoProduto> pedidosProdutos) {
		this.pedidosProdutos = pedidosProdutos;
	}

}
