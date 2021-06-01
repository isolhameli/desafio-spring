package com.mercadolibre.desafiospring.services;

import com.mercadolibre.desafiospring.models.Product;
import com.mercadolibre.desafiospring.repositories.ProductRepository;
import com.mercadolibre.desafiospring.requests.ProductRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final UserService userService;
    private final ProductRepository productRepository;

    public ProductServiceImpl(UserService userService, ProductRepository productRepository) {
        this.userService = userService;
        this.productRepository = productRepository;
    }

    @Override
    public Product create(ProductRequest productRequest) {

        Product product = new Product(null,productRequest.getProductName(),productRequest.getType(),
                productRequest.getBrand(), productRequest.getColor(), productRequest.getNotes());

        return productRepository.save(product);
    }
}
