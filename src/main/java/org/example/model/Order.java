package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "userID", nullable = false)
    private Integer userID;

    @Column(name = "orderdate", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    @Column(name = "delivery_method", nullable = false)
    private String deliveryMethod;

    @Column(name = "totalprice", nullable = false)
    private Double totalPrice;
}

