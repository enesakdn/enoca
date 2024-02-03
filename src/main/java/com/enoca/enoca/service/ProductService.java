package com.enoca.enoca.service;

import com.enoca.enoca.entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(String productName, int stockCount, double price);

    Product getProduct(Long productId);

    List<Product> getAllProducts();

    void updateProduct(Long productId, String productName, int stockCount, double price);

    void deleteProduct(Long productId);
}