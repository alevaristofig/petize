package com.api.loja.domain.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.loja.domain.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
