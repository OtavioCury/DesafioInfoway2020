package br.com.infoway;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.infoway.config.JwtRequest;
import br.com.infoway.dto.ClienteDTO;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class ClienteTests extends GenericTest{

	@Test
	@Order(1)
	public void cadastroClienteTeste() throws Exception {
		ClienteDTO clienteDTO = new ClienteDTO("Otavio Cury", usernameCliente, passwordCliente,
				"(86)99806-3134", new Date());
		mvc.perform(MockMvcRequestBuilders.post("/cliente").contentType(MediaType.APPLICATION_JSON)
				.content(obj.writeValueAsString(clienteDTO)))
		.andExpect(status().isOk());
	}

	@Test
	@Order(2)
	public void loginTeste() throws Exception {
		JwtRequest authenticationRequest = new JwtRequest(usernameCliente, passwordCliente);
		mvc.perform(MockMvcRequestBuilders.post("/login").contentType(MediaType.APPLICATION_JSON)
				.content(obj.writeValueAsString(authenticationRequest)))
		.andExpect(status().isOk());
	}

}
