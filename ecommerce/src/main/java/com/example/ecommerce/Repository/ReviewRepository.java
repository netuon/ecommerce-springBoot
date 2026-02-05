package com.example.ecommerce.Repository;

import com.example.ecommerce.Model.ReviewModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<ReviewModel, Long> {}
