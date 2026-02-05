package com.example.ecommerce.Repository;

import com.example.ecommerce.Model.SupplierModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SupplierRepository extends JpaRepository<SupplierModel, Long> {}
