package br.com.infoway.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoway.dto.PedidoClienteDTO;
import br.com.infoway.dto.PedidoGestorDTO;
import br.com.infoway.dto.PedidoProdutoDTO;
import br.com.infoway.exception.MensagemException;
import br.com.infoway.modelo.Cliente;
import br.com.infoway.modelo.Pedido;
import br.com.infoway.modelo.PedidoProduto;
import br.com.infoway.modelo.Produto;
import br.com.infoway.modelo.enums.StatusPedido;
import br.com.infoway.modelo.respostas.RespostaPedido;
import br.com.infoway.repository.PedidoProdutoRepository;
import br.com.infoway.repository.PedidoRepository;
import br.com.infoway.repository.ProdutoRepository;

@Service
public class PedidoService {

	@Autowired 
	private PedidoRepository pedidoRepository;

	@Autowired 
	private PedidoProdutoRepository pedidoProdutoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	public RespostaPedido inserir(PedidoClienteDTO pedidoDTO, Cliente cliente) throws Exception {
		double valorTotal = 0;
		List<Produto> produtos = new ArrayList<Produto>();
		Pedido pedido = pedidoRepository.save(new Pedido(cliente, new Date(), StatusPedido.SOLICITADO));
		for (PedidoProdutoDTO pedidoProduto : pedidoDTO.getPedidosProdutos()) {
			Produto produto = produtoRepository.findById(pedidoProduto.getProduto()).get();
			if (pedidoProduto.getQuantidade() > produto.getQuantEstoque()) {
				throw new Exception("Não temos "+pedidoProduto.getQuantidade()+" unidades do produto "+produto.getNome()+ " no estoque!");
			}
			produto.setQuantEstoque(produto.getQuantEstoque()-pedidoProduto.getQuantidade());
			produtoRepository.save(produto);
			produtos.add(produto);
			valorTotal = valorTotal + (produto.getPreco()*pedidoProduto.getQuantidade());
			PedidoProduto pedidoProdutoEntity = new PedidoProduto(pedido, produto, pedidoProduto.getQuantidade());
			pedidoProdutoRepository.save(pedidoProdutoEntity);
		}
		pedido.setValor(valorTotal);
		pedido.setData(new Date());
		pedido = pedidoRepository.save(pedido);
		RespostaPedido respostaPedido = new RespostaPedido(pedido, produtos);
		return respostaPedido;
	}
	
	public Pedido atualizarStatus(PedidoGestorDTO pedido) throws Exception {
		if (pedido.getStatus().toUpperCase().equals("SOLICITADO") == true) {
			throw new Exception(MensagemException.pedidoStatusGestor);
		}else {
			Pedido pedidoEntity = pedidoRepository.findById(pedido.getId()).get();
			pedidoEntity.setStatus(StatusPedido.valueOf(pedido.getStatus()));
			return pedidoRepository.save(pedidoEntity);
		}
	}
	
	public String statusPedido(long id, Cliente cliente) throws Exception {
		Pedido pedido = pedidoRepository.findById(id).get();
		if(pedido.getCliente().getId() != cliente.getId()) {
			throw new Exception("Identificação errada!");
		}
		return pedido.getStatus().statusPedido();
	}
	
	public String statusPedido(long id) throws Exception {
		Pedido pedido = pedidoRepository.findById(id).get();
		return pedido.getStatus().statusPedido();
	}

	public List<Pedido> pedidosFinalizados(Cliente cliente) {
		return pedidoRepository.pedidosFinalizados(cliente.getId());
	}

	public String cancelar(long id, Cliente cliente) throws Exception {
		Pedido pedido = pedidoRepository.findById(id).get();
		if(pedido.getCliente().getId() != cliente.getId()) {
			throw new Exception("Identificação errada!");
		}
		if (pedido.getStatus().equals(StatusPedido.SOLICITADO) == false) {
			return "Pedido em preparação não podem ser cancelados!";
		}else {
			pedidoRepository.deleteById(pedido.getId());
			return "Pedido cancelado!";
		}
	}

}
