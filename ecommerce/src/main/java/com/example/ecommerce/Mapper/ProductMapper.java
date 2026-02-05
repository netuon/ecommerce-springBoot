package com.example.ecommerce.Mapper;

import com.example.ecommerce.DTO.ResponseDTO.ProductResponseDTO;
import com.example.ecommerce.Model.ProductModel;


public class ProductMapper {
        public static ProductResponseDTO toDTO(ProductModel productModel) {
            return new ProductResponseDTO(
                   productModel.getName(),
                    productModel.getPrice(),
                    productModel.getReview()
            );

}

}
