package com.enoca.enoca.service;

import com.enoca.enoca.entity.Product;
import com.enoca.enoca.repository.ProductRepository;
import com.enoca.enoca.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(String productName, int stockCount, double price) {
        Product product = new Product();
        product.setProductName(productName);
        product.setProductStockCount(stockCount);
        product.setProductPrice(price);
        return productRepository.save(product);
    }

    @Override
    public Product getProduct(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void updateProduct(Long productId, String productName, int stockCount, double price) {
        Product existingProduct = getProduct(productId);
        if (existingProduct != null) {
            existingProduct.setProductName(productName);
            existingProduct.setProductStockCount(stockCount);
            existingProduct.setProductPrice(price);
            productRepository.save(existingProduct);
        }
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}