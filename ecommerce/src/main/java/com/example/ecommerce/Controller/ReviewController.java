package com.example.ecommerce.Controller;

import com.example.ecommerce.DTO.CreateDTO.ReviewCreateDTO;
import com.example.ecommerce.DTO.ResponseDTO.ReviewResponseDTO;
import com.example.ecommerce.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;


    @PostMapping
    public ResponseEntity<ReviewResponseDTO> createReview(@RequestBody ReviewCreateDTO reviewCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.saveReviews(reviewCreateDTO));
    }
}
