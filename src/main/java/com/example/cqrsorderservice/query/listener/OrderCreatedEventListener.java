package com.example.cqrsorderservice.query.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.cqrsorderservice.event.model.OrderCreatedEvent;
import com.example.cqrsorderservice.query.projection.OrderView;
import com.example.cqrsorderservice.query.repository.OrderViewRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderCreatedEventListener {

    private final OrderViewRepository orderViewRepository;

    @KafkaListener(topics = "order-created-topic", groupId = "cqrs-read-group", containerFactory = "kafkaListenerContainerFactory")
    public void handleOrderCreated(OrderCreatedEvent event) {
        log.info("Received OrderCreatedEvent: {}", event);
        OrderView view = OrderView.builder()
                .orderId(event.orderId())
                .customerName(event.customerName())
                .amount(event.amount())
                .status(event.status())
                .createdAt(event.createdAt())
                .build();

        orderViewRepository.save(view);
    }
}
