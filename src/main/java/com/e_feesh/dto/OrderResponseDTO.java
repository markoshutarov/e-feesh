package com.e_feesh.dto;

import com.e_feesh.model.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDTO {

    private Long id;

    private OrderStatus status;

    private Double totalPrice;

    private LocalDateTime createdAt;

    private List<OrderItemResponseDTO> orderItemResponseDTOS;

    public OrderResponseDTO() {
    }

    public OrderResponseDTO(Long id, OrderStatus status, Double totalPrice, LocalDateTime createdAt, List<OrderItemResponseDTO> orderItemResponseDTOS) {
        this.id = id;
        this.status = status;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.orderItemResponseDTOS = orderItemResponseDTOS;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<OrderItemResponseDTO> getOrderItemResponseDTOS() {
        return orderItemResponseDTOS;
    }

    public void setOrderItemResponseDTOS(List<OrderItemResponseDTO> orderItemResponseDTOS) {
        this.orderItemResponseDTOS = orderItemResponseDTOS;
    }
}
