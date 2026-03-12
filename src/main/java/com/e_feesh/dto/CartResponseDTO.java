package com.e_feesh.dto;

import com.e_feesh.model.CartItem;

import java.util.List;

public class CartResponseDTO {
    private Long cartId;

    private List<CartResponseDTO> items;

    private Double totalPrice;

    public CartResponseDTO() {

    }

    public CartResponseDTO(Long cartId, List<CartResponseDTO> items, Double totalPrice) {
        this.cartId = cartId;
        this.items = items;
        this.totalPrice = totalPrice;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public List<CartResponseDTO> getItems() {
        return items;
    }

    public void setItems(List<CartResponseDTO> items) {
        this.items = items;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
