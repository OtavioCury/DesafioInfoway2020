package br.com.infoway.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.infoway.modelo.enums.StatusPedido;

/**
 * Entidade que representa um Pedido de um Cliente
 * @author Otavio
 *
 */
@Entity
public class Pedido implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Atributo cliente: Preenchimento obrigatório!")
	@ManyToOne
	private Cliente cliente;
	@NotNull(message = "Atributo data: Preenchimento obrigatório!")
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	@NotNull(message = "Atributo status: Preenchimento obrigatório!")
	@Enumerated(EnumType.STRING)
	private StatusPedido status;
	private Double valor;
	private String previsaoEntrega;

	@JsonIgnore
	@OneToMany(mappedBy = "pedido")
	private List<PedidoProduto> pedidosProdutos;

	public Pedido() {
	}

	public Pedido(Cliente cliente, Date data, StatusPedido status) {
		super();
		this.cliente = cliente;
		this.data = data;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public List<PedidoProduto> getPedidosProdutos() {
		return pedidosProdutos;
	}

	public void setPedidosProdutos(List<PedidoProduto> pedidosProdutos) {
		this.pedidosProdutos = pedidosProdutos;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getPrevisaoEntrega() {
		return previsaoEntrega;
	}

	public void setPrevisaoEntrega(String previsaoEntrega) {
		this.previsaoEntrega = previsaoEntrega;
	}

}
