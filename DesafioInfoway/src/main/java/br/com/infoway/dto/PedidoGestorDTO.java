package br.com.infoway.dto;

public class PedidoGestorDTO {
	
	private long id;
	private String status;
	
	public PedidoGestorDTO(long id, String status) {
		super();
		this.id = id;
		this.status = status;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
