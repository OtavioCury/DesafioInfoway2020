package br.com.infoway.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProdutoDTO {
	
	private Long id;
	private String nome;
	private Double preco;
	private String classe;
	private Integer quantEstoque;
	
	@JsonCreator
	public ProdutoDTO(@JsonProperty("nome") String nome, @JsonProperty("preco") Double preco, 
			@JsonProperty("classe") String classe, @JsonProperty("quantEstoque")Integer quantEstoque) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.classe = classe;
		this.quantEstoque = quantEstoque;
	}
	
	@JsonCreator
	public ProdutoDTO(@JsonProperty("id") Long id, @JsonProperty("nome") String nome, 
			@JsonProperty("preco") Double preco, @JsonProperty("classe") String classe, 
			@JsonProperty("quantEstoque") Integer quantEstoque) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.classe = classe;
		this.quantEstoque = quantEstoque;
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
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public Integer getQuantEstoque() {
		return quantEstoque;
	}
	public void setQuantEstoque(Integer quantEstoque) {
		this.quantEstoque = quantEstoque;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
