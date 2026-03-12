package com.e_feesh.repository;

import com.e_feesh.model.Cart;
import com.e_feesh.model.CartItem;
import com.e_feesh.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    public Optional<CartItem> findByCartAndProduct(Cart cart, Product product);

}
