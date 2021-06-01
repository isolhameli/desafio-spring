package com.mercadolibre.desafiospring.services;

import com.mercadolibre.desafiospring.models.Product;
import com.mercadolibre.desafiospring.requests.PostRequest;
import com.mercadolibre.desafiospring.requests.ProductRequest;

public interface ProductService {
    Product create(ProductRequest productRequest);
}
