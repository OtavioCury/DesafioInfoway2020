package br.com.infoway.exception;

/**
 * Classe que guarda as mensagens de erros utilizadas nos endpoints
 * @author Otavio
 *
 */
public class MensagemException{

	static public String emailExistente = "Email indisponível! Tente outro";
	static public String produtoEsgotado = "Produto esgotado!";
	static public String pedidoIdentificacao = "Pedido com id incorreto!";
	static public String pedidoStatusGestor = "Status inválido!";
	static public String falhaEnvioEmail = "Falha ao enviar email!";
	static public String jaExisteGestor = "Já existe um gestor cadastrado na aplicação!";
}
