package com.example.cqrsorderservice.query.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.cqrsorderservice.query.projection.OrderView;

public interface OrderViewRepository extends MongoRepository<OrderView, UUID>{

}
