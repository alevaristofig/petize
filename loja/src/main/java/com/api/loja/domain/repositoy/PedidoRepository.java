package com.api.loja.domain.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.loja.domain.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
