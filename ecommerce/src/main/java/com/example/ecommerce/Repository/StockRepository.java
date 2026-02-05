package com.example.ecommerce.Repository;

import com.example.ecommerce.Model.ProductModel;
import com.example.ecommerce.Model.StockModel;
import org.springframework.data.jpa.repository.JpaRepository;
import tools.jackson.databind.ser.jdk.UUIDSerializer;


import java.util.UUID;

public interface StockRepository extends JpaRepository<StockModel, Long> {}
