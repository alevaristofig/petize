package com.api.loja.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.loja.domain.model.Pedido;
import com.api.loja.model.input.PedidoInput;

@Component
public class PedidoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Pedido toDomainObject(PedidoInput pedidoInput) {
		return modelMapper.map(pedidoInput, Pedido.class);
	}
	
	public void copyToDomainObject(PedidoInput pedidoInput, Pedido pedido) {
		modelMapper.map(pedidoInput, pedido);
	}
}
