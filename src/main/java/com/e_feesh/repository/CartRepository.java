package com.e_feesh.repository;

import com.e_feesh.model.Cart;
import com.e_feesh.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {

        public Optional<Cart> findByUser(User user);

}
