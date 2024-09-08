package com.api.loja.model.input;

import java.time.OffsetDateTime;
import java.util.List;

import com.api.loja.domain.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoInput {

	private int quantidade;
	
	private Status status;
	
	private OffsetDateTime dataPedido;
	
	private List<ItemPedidoInput> itens;
}
