package br.com.infoway;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.infoway.config.JwtRequest;
import br.com.infoway.dto.GestorDTO;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class GestorTests extends GenericTest{

	@Autowired
	private MockMvc mvc;

	@Test
	@Order(1)
	public void cadastroGestorTeste() throws Exception {
		GestorDTO gestorDTO = new GestorDTO("Otavio Cury", usernameGestor, passwordGestor);
		gestorDTO.setLanchonete("Lanchonete Morada Nova");
		mvc.perform(MockMvcRequestBuilders.post("/gestor").contentType(MediaType.APPLICATION_JSON)
				.content(obj.writeValueAsString(gestorDTO)))
		.andExpect(status().isOk());
	}

	@Test
	@Order(2)
	public void loginTeste() throws Exception {
		JwtRequest authenticationRequest = new JwtRequest(usernameGestor, passwordGestor);
		mvc.perform(MockMvcRequestBuilders.post("/login").contentType(MediaType.APPLICATION_JSON)
				.content(obj.writeValueAsString(authenticationRequest)))
		.andExpect(status().isOk());
	}

}
