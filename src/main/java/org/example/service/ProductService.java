package org.example.service;

import org.example.model.Product;
import org.example.repository.ProductRepository;
import org.example.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Product> findAll() {
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            Double averageRating = reviewRepository.findAverageRatingByProductId(product.getId());
            product.setAverageRating(averageRating != null ? averageRating : 0.0);
        }
        return products;
    }

    public Product findById(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Товар не найден"));
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void update(Integer id, Product product) {
        Product existingProduct = findById(id);
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setFileurl(product.getFileurl());
        productRepository.save(existingProduct);
    }

    public void delete(Integer id) {
        productRepository.deleteById(id);
    }
}