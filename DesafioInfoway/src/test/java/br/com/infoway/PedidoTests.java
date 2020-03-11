package br.com.infoway;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.infoway.config.JwtRequest;
import br.com.infoway.dto.PedidoClienteDTO;
import br.com.infoway.dto.PedidoProdutoDTO;
import br.com.infoway.modelo.Pedido;
import br.com.infoway.modelo.respostas.RespostaLogin;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class PedidoTests extends GenericTest{
	
	@Test
	@Order(1)
	public void salvarPedidoClienteTeste() throws Exception {
		RespostaLogin resposta = loginCliente();
		List<PedidoProdutoDTO> lista = new ArrayList<PedidoProdutoDTO>();
		lista.add(new PedidoProdutoDTO(1, 3));
		lista.add(new PedidoProdutoDTO(1, 2));
		PedidoClienteDTO pedidoClienteDTO = new PedidoClienteDTO(lista);
		ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.post("/pedido/cliente")
				.header("Authorization", "Bearer " + resposta.getRespostaToken().getToken())
				.contentType(MediaType.APPLICATION_JSON)
				.content(obj.writeValueAsString(pedidoClienteDTO)))
				.andExpect(status().isOk());
		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();
		String pedidoJson = new JSONObject(contentAsString).getJSONObject("pedido").toString();
		Pedido pedido = obj.readValue(pedidoJson, Pedido.class);
		mvc.perform(MockMvcRequestBuilders.get("/pedido/cliente/"+ pedido.getId())
				.header("Authorization", "Bearer " + resposta.getRespostaToken().getToken()))
		.andExpect(status().isOk());
		mvc.perform(MockMvcRequestBuilders.delete("/pedido/cliente/"+ pedido.getId())
				.header("Authorization", "Bearer " + resposta.getRespostaToken().getToken()))
		.andExpect(status().isOk());
	}

	@Test
	@Order(2)
	public void pedidosFinalizadosClienteTeste() throws Exception {
		RespostaLogin resposta = loginCliente();
		mvc.perform(MockMvcRequestBuilders.get("/pedido/cliente")
				.header("Authorization", "Bearer " + resposta.getRespostaToken().getToken()))
		.andExpect(status().isOk());
	}

	private RespostaLogin loginCliente() {
		JwtRequest authenticationRequest = new JwtRequest(usernameCliente, passwordCliente);
		return login(authenticationRequest);
	}

}
