package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.model.Review;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryID", referencedColumnName = "id")
    private Category category   ;

    @Column(name = "price")
    private float price;

    @Column(name = "fileurl")
    private String fileurl;

    @OneToMany(mappedBy = "productID", fetch = FetchType.EAGER)
    private List<Review> reviews;

    @Transient
    private Double averageRating;

}
