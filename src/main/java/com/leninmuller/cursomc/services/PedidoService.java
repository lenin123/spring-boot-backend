package com.leninmuller.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leninmuller.cursomc.domain.ItemPedido;
import com.leninmuller.cursomc.domain.PagamentoComBoleto;
import com.leninmuller.cursomc.domain.Pedido;
import com.leninmuller.cursomc.domain.enums.EstadoPagamento;
import com.leninmuller.cursomc.repositories.ItemPedidoRepository;
import com.leninmuller.cursomc.repositories.PagamentoRepository;
import com.leninmuller.cursomc.repositories.PedidoRepository;
import com.leninmuller.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteService clienteService;

	
	public Pedido find(Integer id) {  
		Optional<Pedido> obj = repo.findById(id);  
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName())); 
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for(ItemPedido itemPedido : obj.getItemsPedido()) {
			itemPedido.setDesconto(0.0);
			itemPedido.setProduto(produtoService.find(itemPedido.getProduto().getId()));
			itemPedido.setPreco(itemPedido.getProduto().getPreco());
			itemPedido.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItemsPedido());
		System.out.println(obj);
		return obj;
	}
		
}
