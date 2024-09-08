package com.api.loja.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.api.loja.facade.PedidoFacade;

@Component
public class PedidoResponseConsumidor {

	@Autowired
	private PedidoFacade pedidoFacade;
	
	@RabbitListener(queues = {"pedido-response-queue"})
	public void receber(@Payload Message message) {
		String payload = String.valueOf(message.getPayload());
		
		System.out.println("Recebeu a mensagem "+payload);
	}
}
