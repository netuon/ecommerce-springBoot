package com.example.ecommerce.Model;


import com.example.ecommerce.DTO.ResponseDTO.ProductResponseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;
import java.io.Serializable;
import java.math.BigDecimal;


@Entity
@Table(name = "TB_PRODUCT")
public class ProductModel implements Serializable {
    private static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    public ProductModel(ProductResponseDTO productDTO) {
        BeanUtils.copyProperties(productDTO, this);
    }
    public ProductModel() {}

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)//apenas recebe dados, não mostra
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private StockModel stock;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private SupplierModel supplier;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private ReviewModel review;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ReviewModel getReview() {
        return review;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long serialVersionUID) {
        ProductModel.serialVersionUID = serialVersionUID;
    }

    public StockModel getStock() {
        return stock;
    }

    public void setStock(StockModel stock) {
        this.stock = stock;
    }

    public void setReview(ReviewModel review) {
        this.review = review;
    }

    public SupplierModel getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierModel supplier) {
        this.supplier = supplier;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
