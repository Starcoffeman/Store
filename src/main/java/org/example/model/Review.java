package org.example.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "userid", nullable = false)
    private Integer userID;

    @Column(name = "productid",nullable = false)
    private Integer productID;

    @Column(name = "rating",nullable = false)
    private int rating;

    @Column(name = "comment",length = 500)
    private String comment;

    @CreationTimestamp
    @Column(name = "reviewdate", nullable = false, updatable = false)
    private LocalDateTime reviewDate;

}