package com.example.cqrsorderservice.command.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cqrsorderservice.command.dto.CreateOrderRequest;
import com.example.cqrsorderservice.command.service.OrderCommandService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/commands/orders")
@RequiredArgsConstructor
public class OrderCommandController {
	
	private final OrderCommandService orderCommandService;
	
	 @PostMapping
	    public ResponseEntity<UUID> createOrder(@RequestBody CreateOrderRequest request) {
	        UUID orderId = orderCommandService.createOrder(request);
	        return ResponseEntity.ok(orderId);
	    }

}
