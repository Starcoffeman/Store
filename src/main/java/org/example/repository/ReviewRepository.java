package org.example.repository;

import org.example.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByProductID(Integer productID);
    List<Review> findByUserID(Integer userId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.productID = :productId")
    Double findAverageRatingByProductId(@Param("productId") Integer productId);


}
