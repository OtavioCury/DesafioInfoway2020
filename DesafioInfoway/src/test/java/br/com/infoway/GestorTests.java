package br.com.infoway;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.infoway.config.JwtRequest;
import br.com.infoway.config.JwtResponse;
import br.com.infoway.dto.GestorDTO;
import br.com.infoway.dto.ProdutoDTO;
import br.com.infoway.modelo.Gestor;
import br.com.infoway.modelo.respostas.RespostaLogin;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class GestorTests {

	@Autowired
	private MockMvc mvc;
	private String username = "otaviocury.oc@gmail.com", password = "123456";
	private ObjectMapper obj = new ObjectMapper();

	@Test
	@Order(1)
	public void cadastroGestorTeste() throws Exception {
		GestorDTO gestorDTO = new GestorDTO("Otavio Cury", username, password);
		gestorDTO.setLanchonete("Lanchonete Morada Nova");
		mvc.perform(MockMvcRequestBuilders.post("/gestor").contentType(MediaType.APPLICATION_JSON)
				.content(obj.writeValueAsString(gestorDTO)))
		.andExpect(status().isOk());
	}

	@Test
	@Order(2)
	public void loginTeste() throws Exception {
		JwtRequest authenticationRequest = new JwtRequest(username, password);
		mvc.perform(MockMvcRequestBuilders.post("/login").contentType(MediaType.APPLICATION_JSON)
				.content(obj.writeValueAsString(authenticationRequest)))
		.andExpect(status().isOk());
	}

	@Test
	@Order(3)
	public void operacoesProdutoTeste() throws Exception {
		RespostaLogin resposta = login();
		ProdutoDTO produtoDTO = new ProdutoDTO("Cerveja Teresina", 7.50, "BEBIDA", 10);
		ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.post("/produto")
				.header("Authorization", "Bearer " + resposta.getRespostaToken().getToken())
				.contentType(MediaType.APPLICATION_JSON)
				.content(obj.writeValueAsString(produtoDTO)))
				.andExpect(status().isOk());
		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();
		ProdutoDTO produtoDTOUpdate = obj.readValue(contentAsString, ProdutoDTO.class);
		produtoDTOUpdate.setPreco(8.50);
		produtoDTOUpdate.setQuantEstoque(20);
		mvc.perform(MockMvcRequestBuilders.put("/produto")
				.header("Authorization", "Bearer " + resposta.getRespostaToken().getToken())
				.contentType(MediaType.APPLICATION_JSON)
				.content(obj.writeValueAsString(produtoDTOUpdate)))
		.andExpect(status().isOk());
		mvc.perform(MockMvcRequestBuilders.delete("/produto/"+ produtoDTOUpdate.getId())
				.header("Authorization", "Bearer " + resposta.getRespostaToken().getToken()))
		.andExpect(status().isOk());
	}

	@Test
	@Order(4)
	public void listarProdutoTeste() throws Exception {
		RespostaLogin resposta = login();
		mvc.perform(MockMvcRequestBuilders.get("/produto")
				.header("Authorization", "Bearer " + resposta.getRespostaToken().getToken()))
		.andExpect(status().isOk());
	}

	public RespostaLogin login() {
		JwtRequest authenticationRequest = new JwtRequest(username, password);
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
