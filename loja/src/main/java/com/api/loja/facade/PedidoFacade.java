package com.api.loja.facade;

import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.loja.domain.enums.Status;
import com.api.loja.domain.model.Pedido;
import com.api.loja.model.input.PedidoInput;
import com.api.loja.producer.PedidoRequestProducer;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class PedidoFacade {
	
	@Autowired
	private PedidoRequestProducer pedidoRequestProducer;
	
	public void enviarMensagem(PedidoInput pedidoInput) {
		try {
			pedidoRequestProducer.integrarStatus(pedidoInput);
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
		} catch (AmqpException e) {			
			e.printStackTrace();
		}
	}

}
