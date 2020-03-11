package br.com.infoway.config;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String token;
	
	@JsonCreator
	public JwtResponse(@JsonProperty("token") String token) {
		this.token = token;
	}

	public String getToken() {
		return this.token;
	}

}
