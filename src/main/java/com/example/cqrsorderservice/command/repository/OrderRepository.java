package com.example.cqrsorderservice.command.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cqrsorderservice.command.entity.Order;

public interface OrderRepository extends JpaRepository<Order, UUID>{

}
