package com.example.ecommerce.DTO.ResponseDTO;

import com.example.ecommerce.Model.ReviewModel;
import java.math.BigDecimal;


public class ProductResponseDTO {

    private String name;
    private BigDecimal price;
    private ReviewModel review;


    public ProductResponseDTO(String name, BigDecimal price, ReviewModel review) {
        this.name = name;
        this.price = price;
        this.review = review;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ReviewModel getReview() {
        return review;
    }
}
