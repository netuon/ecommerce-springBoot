package com.example.ecommerce.DTO.CreateDTO;


import java.math.BigDecimal;

public record ProductCreateDTO (String name,
                                BigDecimal price,
                                Long supplierId,
                                Integer stockQuantity)
{ }
