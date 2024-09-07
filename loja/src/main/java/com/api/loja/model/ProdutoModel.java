package com.api.loja.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoModel {

	private Long id;
	
	private String nome;
	
	private String descricao;
	
	private BigDecimal preco;
	
	private OffsetDateTime dataCadastro;
}
