package com.e_feesh.controller;

import com.e_feesh.dto.OrderRequestDTO;
import com.e_feesh.dto.OrderResponseDTO;
import com.e_feesh.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> placeOrder(@Valid @RequestBody OrderRequestDTO request){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.placeOrder(request));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getMyOrders(){
        return ResponseEntity.ok(orderService.getMyOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById( @PathVariable Long id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
}
