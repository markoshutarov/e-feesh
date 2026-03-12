package com.e_feesh.service;

import com.e_feesh.dto.ProductRequestDTO;
import com.e_feesh.dto.ProductResponseDTO;
import com.e_feesh.exception.ProductNotFoundException;
import com.e_feesh.model.Category;
import com.e_feesh.model.Product;
import com.e_feesh.model.User;
import com.e_feesh.repository.ProductRepository;
import com.e_feesh.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Page<ProductResponseDTO> getAllProducts(int page, int size) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable)
                .map(product -> new ProductResponseDTO(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getStock(),
                        product.getCategory()
                ));
    }

    public Optional<ProductResponseDTO> getProductById(Long id) {
        return Optional.ofNullable(productRepository.findById(id)
                .map(product -> new ProductResponseDTO(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getStock(),
                        product.getCategory()
                ))
                .orElseThrow(() -> new ProductNotFoundException("Product not found.")));
    }

    public Page<ProductResponseDTO> getProductsByCategory(Category category, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findByCategory(category, pageable)
                .map(product -> new ProductResponseDTO(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getStock(),
                        product.getCategory()
                ));
    }

    public ProductResponseDTO createProduct(ProductRequestDTO requestDTO) {
        Product product = new Product();
        product.setCategory(requestDTO.getCategory());
        product.setStock(requestDTO.getStock());
        product.setName(requestDTO.getName());
        product.setPrice(requestDTO.getPrice());
        product.setDescription(requestDTO.getDescription());
        Product savedProduct = productRepository.save(product);
        return new ProductResponseDTO(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getDescription(),
                savedProduct.getPrice(),
                savedProduct.getStock(),
                savedProduct.getCategory()
        );
    }

    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO requestDTO) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found."));
        product.setCategory(requestDTO.getCategory());
        product.setStock(requestDTO.getStock());
        product.setName(requestDTO.getName());
        product.setPrice(requestDTO.getPrice());
        product.setDescription(requestDTO.getDescription());
        Product savedProduct = productRepository.save(product);
        return new ProductResponseDTO(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getDescription(),
                savedProduct.getPrice(),
                savedProduct.getStock(),
                savedProduct.getCategory()
        );
    }

    public void deleteProductById(Long id) {
        productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found."));
        productRepository.deleteById(id);
    }

}
