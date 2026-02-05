package com.example.ecommerce.Service;

import com.example.ecommerce.DTO.CreateDTO.ProductCreateDTO;
import com.example.ecommerce.DTO.ResponseDTO.ProductResponseDTO;
import com.example.ecommerce.Mapper.ProductMapper;
import com.example.ecommerce.Model.ProductModel;
import com.example.ecommerce.Model.StockModel;
import com.example.ecommerce.Model.SupplierModel;
import com.example.ecommerce.Repository.ProductRepository;
import com.example.ecommerce.Repository.ReviewRepository;
import com.example.ecommerce.Repository.StockRepository;
import com.example.ecommerce.Repository.SupplierRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;

    public ProductService(ReviewRepository reviewRepository, StockRepository stockRepository, SupplierRepository supplierRepository, ProductRepository productRepository) {
        this.supplierRepository = supplierRepository;
        this.productRepository = productRepository;
    }

    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toDTO)
                .toList();

    }

    @Transactional
    public ProductResponseDTO saveProduct(ProductCreateDTO productCreateDTO) {
        ProductModel product = new ProductModel();
        product.setName(productCreateDTO.name());
        product.setPrice((productCreateDTO.price()));

        SupplierModel supplier = supplierRepository.findById(productCreateDTO.supplierId())
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found"));
        product.setSupplier(supplier);

        StockModel stock = new StockModel();
        stock.setQuantity(productCreateDTO.stockQuantity());
        stock.setProduct(product);
        product.setStock(stock);

        ProductModel saved = productRepository.save(product);
        return new ProductResponseDTO(saved .getName(), saved.getPrice(), saved.getReview());
    };
}
