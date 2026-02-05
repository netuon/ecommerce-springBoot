package com.example.ecommerce.DTO.ResponseDTO;

import com.example.ecommerce.Model.ProductModel;

import javax.xml.stream.events.Comment;

public class ReviewResponseDTO{

    private String Comment;
    private ProductModel Product;

    public ReviewResponseDTO(String comment, ProductModel product) {
        this.Comment = comment;
        this.Product = new ProductModel();
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public ProductModel getProduct() {
        return Product;
    }

    public void setProduct(ProductModel product) {
        this.Product = product;
    }
}
