package com.api.loja.controller;

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

import com.api.loja.assembler.ProdutoInputDisassembler;
import com.api.loja.assembler.ProdutoModelAssembler;
import com.api.loja.domain.model.Produto;
import com.api.loja.domain.service.ProdutoService;
import com.api.loja.model.PedidoModel;
import com.api.loja.model.ProdutoModel;
import com.api.loja.model.input.ProdutoInput;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ProdutoInputDisassembler produtoInputDisassembler;
	
	@Autowired
	private ProdutoModelAssembler produtoModelAssembler;
	
	@GetMapping
	public List<ProdutoModel> listar() {
		List<Produto> produtos = produtoService.listar();
		
		return produtoModelAssembler.toCollectionModel(produtos);
	}
	
	@GetMapping("/{id}")
	public ProdutoModel buscar(@PathVariable Long id) {
		Produto produto = produtoService.buscarOuFalhar(id);
		
		return produtoModelAssembler.toModel(produto);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoModel adicionar(@RequestBody ProdutoInput produtoInput) {
		Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
		
		produto = produtoService.salvar(produto);
		
		return produtoModelAssembler.toModel(produto);
	}
	
	@PutMapping("/{id}")
	public ProdutoModel atualizar(@PathVariable Long id, @RequestBody ProdutoInput produtoInput) {
		Produto produto = produtoService.buscarOuFalhar(id);
		
		produtoInputDisassembler.copyToDomainObject(produtoInput, produto);
		
		produto = produtoService.salvar(produto);
		
		return produtoModelAssembler.toModel(produto);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		produtoService.excluir(id);
	}
}
