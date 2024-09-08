package com.api.loja.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.loja.assembler.PedidoInputDisassembler;
import com.api.loja.assembler.PedidoModelAssembler;
import com.api.loja.consumer.PedidoResponseConsumidor;
import com.api.loja.domain.model.Pedido;
import com.api.loja.domain.service.PedidoService;
import com.api.loja.facade.PedidoFacade;
import com.api.loja.model.PedidoModel;
import com.api.loja.model.input.PedidoInput;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private PedidoInputDisassembler pedidoInputDisassembler;
	
	@Autowired
	private PedidoModelAssembler pedidoModelAssembler;
	
	@Autowired
	private PedidoFacade pedidoFacade;
	
	@Autowired
	private PedidoResponseConsumidor pedidoResponseConsumidor;
	
	@GetMapping
	public List<PedidoModel> listar(){
		List<Pedido> pedidos = pedidoService.listar();
		
		return pedidoModelAssembler.toCollectionModel(pedidos);
	}
	
	@GetMapping("/{id}")
	public PedidoModel buscar(@PathVariable Long id) {
		Pedido pedido = pedidoService.buscarOuFalhar(id);
		
		return pedidoModelAssembler.toModel(pedido);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoModel adicionar(@RequestBody PedidoInput pedidoInput) {		
		
		Pedido pedido = pedidoInputDisassembler.toDomainObject(pedidoInput);
		
		pedido = pedidoService.salvar(pedido);
		
		return pedidoModelAssembler.toModel(pedido);
	}
	
	@PutMapping("/{id}")
	public PedidoModel atualizar(@PathVariable Long id, @RequestBody PedidoInput pedidoInput) {
		Pedido pedido = pedidoService.buscarOuFalhar(id);		
		
		pedidoInputDisassembler.copyToDomainObject(pedidoInput, pedido);
		
		pedido = pedidoService.salvar(pedido);
		
		return pedidoModelAssembler.toModel(pedido);		
	}
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		pedidoService.excluir(id);
	}
	
	@PutMapping("/alterarstatus/{id}")
	public PedidoModel alterarStatus(@PathVariable Long id, @RequestBody PedidoInput pedidoInput) {
		
		Pedido pedido = pedidoService.buscarOuFalhar(id);		
		
		pedidoInputDisassembler.copyToDomainObject(pedidoInput, pedido);
		
		pedidoFacade.enviarMensagem(pedidoInput);
		
		pedidoService.salvar(pedido);
		
		return pedidoModelAssembler.toModel(pedido);
	}
}
