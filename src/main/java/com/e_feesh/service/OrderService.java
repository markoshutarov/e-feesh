package com.e_feesh.service;

import com.e_feesh.dto.OrderItemRequestDTO;
import com.e_feesh.dto.OrderItemResponseDTO;
import com.e_feesh.dto.OrderRequestDTO;
import com.e_feesh.dto.OrderResponseDTO;
import com.e_feesh.exception.EmptyOrderException;
import com.e_feesh.exception.InsufficientStockException;
import com.e_feesh.exception.OrderNotFoundException;
import com.e_feesh.exception.ProductNotFoundException;
import com.e_feesh.model.*;
import com.e_feesh.repository.OrderItemRepository;
import com.e_feesh.repository.OrderRepository;
import com.e_feesh.repository.ProductRepository;
import com.e_feesh.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(UserRepository userRepository, OrderRepository orderRepository, ProductRepository productRepository, OrderItemRepository orderItemRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
    }

    //              ↓↓↓↓↓↓ made with the help of AI ↓↓↓↓↓↓↓
    public OrderResponseDTO placeOrder(OrderRequestDTO request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        List<OrderItemRequestDTO> items = request.getItems();
        if (items == null || items.isEmpty()) {
            throw new EmptyOrderException("Order cannot be empty");
        }

        List<OrderItem> orderItems = new ArrayList<>();
        double totalprice = 0.0;
        for (OrderItemRequestDTO item : items) {
            Product product = productRepository.findById(item.getProductId()).orElseThrow(() -> new ProductNotFoundException("Product not found."));
            if (product.getStock() < item.getQuantity()) {
                throw new InsufficientStockException("Not enough items for product: " + product.getName());
            }
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPriceAtPurchase(product.getPrice());
            orderItems.add(orderItem);
            totalprice += orderItem.getPriceAtPurchase() * orderItem.getQuantity();
        }
        Order order = new Order();
        order.setUser(user);
        order.setOrderItems(orderItems);
        order.setTotalPrice(totalprice);
        order.setOrderStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);

        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(savedOrder);
            orderItemRepository.save(orderItem);
            Product product = orderItem.getProduct();
            product.setStock(product.getStock() - orderItem.getQuantity());
            productRepository.save(product);
        }
        List<OrderItemResponseDTO> itemResponseDTOS = orderItems.stream()
                .map(item -> new OrderItemResponseDTO(
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getPriceAtPurchase(),
                        item.getPriceAtPurchase() * item.getQuantity()
                ))
                .toList();

        return new OrderResponseDTO(
                savedOrder.getId(),
                savedOrder.getOrderStatus(),
                savedOrder.getTotalPrice(),
                savedOrder.getCreatedAt(),
                itemResponseDTOS
        );
    }

    //              ↑↑↑↑↑↑↑ made with the help of AI ↑↑↑↑↑↑↑
    public List<OrderResponseDTO> getMyOrders() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        return orderRepository.findByUser(user)
                .stream()
                .map(order -> new OrderResponseDTO(
                        order.getId(),
                        order.getOrderStatus(),
                        order.getTotalPrice(),
                        order.getCreatedAt(),
                        order.getOrderItems().stream()
                                .map(item -> new OrderItemResponseDTO(
                                        item.getProduct().getName(),
                                        item.getQuantity(),
                                        item.getPriceAtPurchase(),
                                        item.getPriceAtPurchase() * item.getQuantity()
                                ))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    public OrderResponseDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found."));

        return new OrderResponseDTO(
                order.getId(),
                order.getOrderStatus(),
                order.getTotalPrice(),
                order.getCreatedAt(),
                order.getOrderItems().stream()
                        .map(item -> new OrderItemResponseDTO(
                                item.getProduct().getName(),
                                item.getQuantity(),
                                item.getPriceAtPurchase(),
                                item.getPriceAtPurchase() * item.getQuantity()
                        ))
                        .collect(Collectors.toList()));

    }

}
