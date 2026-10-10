package com.app.ecom.controller;

import com.app.ecom.dto.OrderResponse;
import com.app.ecom.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderservice;

    @PostMapping
    public ResponseEntity<Optional<OrderResponse>> createOrder(@RequestHeader("X-User-ID") String userId) {
        Optional<OrderResponse> order = orderservice.createOrder(userId);
        return (order.isEmpty()) ? ResponseEntity.badRequest().build() : ResponseEntity.ok(order);
    }



}
