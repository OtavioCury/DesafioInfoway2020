package br.com.infoway;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.infoway.config.JwtRequest;
import br.com.infoway.config.JwtResponse;
import br.com.infoway.modelo.Gestor;
import br.com.infoway.modelo.respostas.RespostaLogin;

public class GenericTest {
	@Autowired
	protected MockMvc mvc;
	protected String usernameGestor = "otaviocury.oc@gmail.com", passwordGestor = "123456";
	protected String usernameCliente = "cliente@gmail.com", passwordCliente = "123456";
	protected ObjectMapper obj = new ObjectMapper();
	
	protected RespostaLogin login(JwtRequest authenticationRequest) {
		ResultActions resultActions;
		try {
			resultActions = mvc.perform(MockMvcRequestBuilders.post("/login").contentType(MediaType.APPLICATION_JSON)
					.content(obj.writeValueAsString(authenticationRequest)))
					.andExpect(status().isOk());
			MvcResult result = resultActions.andReturn();
			String contentAsString = result.getResponse().getContentAsString();
			JSONObject jsonObj = new JSONObject(contentAsString);
			String stringPessoa = jsonObj.getJSONObject("pessoa").toString();
			String stringToken = jsonObj.getJSONObject("respostaToken").toString();
			Gestor gestor = obj.readValue(stringPessoa, Gestor.class);
			JwtResponse jwt = obj.readValue(stringToken, JwtResponse.class);
			return new RespostaLogin(jwt, gestor);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
