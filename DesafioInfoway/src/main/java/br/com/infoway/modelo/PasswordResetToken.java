package br.com.infoway.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.apache.commons.lang.time.DateUtils;

@Entity
public class PasswordResetToken {
	private static final int EXPIRATION = 60 * 24;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String token;

	@OneToOne(targetEntity = Pessoa.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "pessoa_id")
	private Pessoa pessoa;
	
	private Date dataExpira;

	public PasswordResetToken(String token, Pessoa pessoa) {
		super();
		this.token = token;
		this.pessoa = pessoa;
		this.dataExpira = DateUtils.addMinutes(new Date(), EXPIRATION);
	}

	public PasswordResetToken() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Date getDataExpira() {
		return dataExpira;
	}

	public void setDataExpira(Date dataExpira) {
		this.dataExpira = dataExpira;
	}

}
