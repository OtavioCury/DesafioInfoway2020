package br.com.infoway.dto;

public class ProdutoDTO {
	
	private String nome;
	private Double preco;
	private String classe;
	private Integer quantEstoque;
	
	public ProdutoDTO(String nome, Double preco, String classe, Integer quantEstoque) {
		super();
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
	
}
