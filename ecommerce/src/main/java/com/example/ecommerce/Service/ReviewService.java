package com.example.ecommerce.Service;

import com.example.ecommerce.DTO.CreateDTO.ReviewCreateDTO;
import com.example.ecommerce.DTO.ResponseDTO.ReviewResponseDTO;
import com.example.ecommerce.Model.ProductModel;
import com.example.ecommerce.Model.ReviewModel;
import com.example.ecommerce.Repository.ProductRepository;
import com.example.ecommerce.Repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ProductRepository productRepository;

    public ReviewResponseDTO saveReviews(ReviewCreateDTO reviewCreateDTO) {
        ReviewModel review = new ReviewModel();
        review.setComment(reviewCreateDTO.comment());
        ProductModel product = productRepository.findById(reviewCreateDTO.productid()).orElseThrow(() -> new EntityNotFoundException("Product not found"));;
        review.setProduct(product);

        ReviewModel saved = reviewRepository.save(review);
        return new ReviewResponseDTO(saved.getComment(), saved.getProduct());
    }
}
