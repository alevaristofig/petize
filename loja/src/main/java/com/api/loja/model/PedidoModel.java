package com.api.loja.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.api.loja.domain.enums.Status;
import com.api.loja.domain.model.ItemPedido;
import com.api.loja.domain.model.Produto;
import com.api.loja.model.input.ItemPedidoInput;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoModel {

	private Long id;
	
	private int quantidade;

	private Status status;
	
	private OffsetDateTime dataPedido;
	
	private List<ItemPedidoInput> itens;
}
