package com.e_feesh.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class OrderRequestDTO {

    @NotEmpty
    private List<OrderItemRequestDTO> items;

    public OrderRequestDTO() {
    }

    public OrderRequestDTO(List<OrderItemRequestDTO> items) {
        this.items = items;
    }

    public List<OrderItemRequestDTO> getOrderItemRequestDTOS() {
        return items;
    }

    public void setOrderItemRequestDTOS(List<OrderItemRequestDTO> items) {
        this.items = items;
    }
}

