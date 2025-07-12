package com.example.cqrsorderservice.command.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.cqrsorderservice.command.dto.CreateOrderRequest;
import com.example.cqrsorderservice.command.entity.Order;
import com.example.cqrsorderservice.command.entity.OrderStatus;
import com.example.cqrsorderservice.command.repository.OrderRepository;
import com.example.cqrsorderservice.event.model.OrderCreatedEvent;
import com.example.cqrsorderservice.event.producer.OrderEventProducer;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderCommandService {
	
	private final OrderRepository orderRepository;
	private final OrderEventProducer orderEventProducer;
	
	
	public UUID createOrder(CreateOrderRequest request ) {
		 Order order = Order.builder()
	                .id(UUID.randomUUID())
	                .customerName(request.getCustomerName())
	                .amount(request.getAmount())
	                .status(OrderStatus.CREATED)
	                .createdAt(LocalDateTime.now())
	                .build();
		 orderRepository.save(order);
		 
		 OrderCreatedEvent createdEvent = new OrderCreatedEvent(
	                order.getId(),
	                order.getCustomerName(),
	                order.getAmount(),
	                order.getStatus().name(),
	                order.getCreatedAt()
	        );
		 
		 	orderEventProducer.publish(createdEvent);

	        return order.getId();
	
	}
	

}
