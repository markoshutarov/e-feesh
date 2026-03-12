package com.e_feesh.repository;

import com.e_feesh.model.Order;
import com.e_feesh.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    public List<Order> findByUser(User user);
}
