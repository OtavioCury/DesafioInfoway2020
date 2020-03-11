package br.com.infoway;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.infoway.config.JwtRequest;
import br.com.infoway.dto.ProdutoDTO;
import br.com.infoway.modelo.respostas.RespostaLogin;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class ProdutoTests extends GenericTest{
	
	@Test
	@Order(1)
	public void operacoesGestorProdutoTeste() throws Exception {
		RespostaLogin resposta = loginGestor();
		ProdutoDTO produtoDTO = new ProdutoDTO("Cerveja Teresina", 7.50, "BEBIDA", 20);
		ProdutoDTO produtoDTO2 = new ProdutoDTO("Cerveja Timon", 7.50, "BEBIDA", 20);
		mvc.perform(MockMvcRequestBuilders.post("/produto")
				.header("Authorization", "Bearer " + resposta.getRespostaToken().getToken())
				.contentType(MediaType.APPLICATION_JSON)
				.content(obj.writeValueAsString(produtoDTO)))
		.andExpect(status().isOk());
		ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.post("/produto")
				.header("Authorization", "Bearer " + resposta.getRespostaToken().getToken())
				.contentType(MediaType.APPLICATION_JSON)
				.content(obj.writeValueAsString(produtoDTO2)))
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
	@Order(2)
	public void listarProdutoGestorTeste() throws Exception {
		RespostaLogin resposta = loginGestor();
		mvc.perform(MockMvcRequestBuilders.get("/produto")
				.header("Authorization", "Bearer " + resposta.getRespostaToken().getToken()))
		.andExpect(status().isOk());
	}

	private RespostaLogin loginGestor() {
		JwtRequest authenticationRequest = new JwtRequest(usernameGestor, passwordGestor);
		return login(authenticationRequest);
	}
}
