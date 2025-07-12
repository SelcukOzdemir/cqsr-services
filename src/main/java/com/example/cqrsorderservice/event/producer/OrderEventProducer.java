package com.example.cqrsorderservice.event.producer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.example.cqrsorderservice.event.model.OrderCreatedEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderEventProducer {
	
	private static final Logger log = LoggerFactory.getLogger(OrderEventProducer.class);
	private final KafkaTemplate<String, Object> kafkaTemplate;

	public void publish(OrderCreatedEvent event) {
		 log.info("Publishing OrderCreatedEvent: {}", event);
		 kafkaTemplate.send("order-created-topic", event.orderId().toString(), event);
	}
}
