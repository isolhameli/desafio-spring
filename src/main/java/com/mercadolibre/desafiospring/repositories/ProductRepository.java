package com.mercadolibre.desafiospring.repositories;

import com.mercadolibre.desafiospring.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
