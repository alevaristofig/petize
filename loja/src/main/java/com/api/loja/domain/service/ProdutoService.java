package com.api.loja.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.api.loja.domain.exception.ProdutoNaoEncontradoException;
import com.api.loja.domain.model.Produto;
import com.api.loja.domain.repositoy.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProdutoService {
	
	private static final String MSG_PRODUTO_EM_USO 
	= "Produto de código %d não pode ser removido, pois está em uso";

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Transactional
	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public List<Produto> listar() {
		return produtoRepository.findAll();
	}
	
	public Produto buscarOuFalhar(Long id) {
		return produtoRepository.findById(id)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(id));
	}
	
	@Transactional
	public void excluir(Long id) {
		try {
			produtoRepository.deleteById(id);
			produtoRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new ProdutoNaoEncontradoException(id);
		} 
	}
}
