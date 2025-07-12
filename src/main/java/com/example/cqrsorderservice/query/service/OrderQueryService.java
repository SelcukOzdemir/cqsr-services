package com.example.cqrsorderservice.query.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.cqrsorderservice.query.projection.OrderView;
import com.example.cqrsorderservice.query.repository.OrderViewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderQueryService {

    private final OrderViewRepository repository;

    public List<OrderView> getAllOrders() {
        return repository.findAll();
    }

    public Optional<OrderView> getOrderById(UUID id) {
        return repository.findById(id);
    }
}
