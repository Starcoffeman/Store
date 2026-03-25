package org.example.service;

import org.example.model.Review;
import org.example.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public void save(Review review) {
        System.out.println("Saving review: " + review);
        reviewRepository.save(review);
    }

    public List<Review> findByProductID(Integer productID) {
        return reviewRepository.findByProductID(productID);
    }

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Review findById(Integer id) {
        return reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Отзыв не найден"));
    }

    public void add(Review review) {
        reviewRepository.save(review);
    }

    public void update(Integer id, Review reviewDto) {
        Review review = findById(id);
        review.setUserID(reviewDto.getUserID());
        review.setProductID(reviewDto.getProductID());
        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());
        reviewRepository.save(review);
    }

    public void delete(Integer id) {
        reviewRepository.deleteById(id);
    }

    public List<Review> getByUserId(Integer id) {
        return reviewRepository.findByUserID(id);
    }

}