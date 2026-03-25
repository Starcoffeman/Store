package org.example.service;

import org.example.model.Order;
import org.example.model.Product;
import org.example.model.Review;
import org.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Integer id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Заказ не найден"));
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public void update(Integer id, Order order) {
        Order existingOrder = findById(id);
        existingOrder.setOrderDate(order.getOrderDate());
        existingOrder.setStatus(order.getStatus());
        existingOrder.setTotalPrice(order.getTotalPrice());
        existingOrder.setUserID(order.getUserID());
        orderRepository.save(existingOrder);
    }

    public List<Order> getByUserId(Integer id) {
        return orderRepository.findByUserID(id);
    }

    public void delete(Integer id) {
        orderRepository.deleteById(id);
    }

    public List<Product> findAllProductById(Integer id){
        return orderRepository.findAllProductById(id);
    }

}
