package br.com.infoway.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Gestor extends Pessoa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToOne
	private Lanchonete lanchonete;

	public Lanchonete getLanchonete() {
		return lanchonete;
	}

	public void setLanchonete(Lanchonete lanchonete) {
		this.lanchonete = lanchonete;
	}

}
