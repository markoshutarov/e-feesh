package com.e_feesh.repository;

import com.e_feesh.model.Category;
import com.e_feesh.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public Page<Product> findByCategory(Category category, Pageable pageable);
}
