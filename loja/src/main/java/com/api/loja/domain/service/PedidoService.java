package com.api.loja.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.api.loja.domain.exception.PedidoNaoEncontradoException;
import com.api.loja.domain.model.Pedido;
import com.api.loja.domain.repositoy.PedidoRepository;

import jakarta.transaction.Transactional;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Transactional
	public Pedido salvar(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}
	
	public List<Pedido> listar(){
		return pedidoRepository.findAll();
	}
	
	public Pedido buscarOuFalhar(Long id) {
		return pedidoRepository.findById(id)
				.orElseThrow(() -> new PedidoNaoEncontradoException(id));
	}
	
	@Transactional
	public void excluir(Long id) {
		try {
			pedidoRepository.deleteById(id);
			pedidoRepository.flush();
		} catch(EmptyResultDataAccessException e) {
			throw new PedidoNaoEncontradoException(id);
		} 
	}
}
