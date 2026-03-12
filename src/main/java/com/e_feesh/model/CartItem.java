package com.e_feesh.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cartitems")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id",nullable = false)
    private Cart cart;


    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;
}
