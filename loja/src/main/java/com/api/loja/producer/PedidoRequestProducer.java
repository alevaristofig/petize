package com.api.loja.producer;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.loja.domain.enums.Status;
import com.api.loja.domain.model.Pedido;
import com.api.loja.model.input.PedidoInput;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Component
public class PedidoRequestProducer {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	public void integrarStatus(PedidoInput pedidoInput) throws JsonProcessingException, AmqpException {		
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		objectMapper.registerModule(new JavaTimeModule());
		
		amqpTemplate.convertAndSend(
			"pedito-status-exchange",
			"pedido-status-queue-rout-key",
			objectMapper.writeValueAsString(pedidoInput)
		);
	}

}
