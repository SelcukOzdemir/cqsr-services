package com.example.cqrsorderservice.command.dto;

import lombok.Data;

@Data
public class CreateOrderRequest {
	
	private String customerName;
	private Double amount;

}
