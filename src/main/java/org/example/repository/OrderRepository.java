package org.example.repository;

import org.example.model.Order;
import org.example.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUserID(Integer id);

    List<Product> findAllProductById(Integer id);
}
