package com.example.cqrsorderservice.query.projection;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "order_views")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderView {

    @Id
    private UUID orderId;
    private String customerName;
    private Double amount;
    private String status;
    private LocalDateTime createdAt;
}