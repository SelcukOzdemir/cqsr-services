package com.example.cqrsorderservice.event.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderCreatedEvent (UUID orderId,
        String customerName,
        Double amount,
        String status,
        LocalDateTime createdAt){

}
