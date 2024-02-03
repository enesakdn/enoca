package com.enoca.enoca.service;

import com.enoca.enoca.entity.*;
import com.enoca.enoca.repository.CartRepository;
import com.enoca.enoca.repository.OrderRepository;
import com.enoca.enoca.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void placeOrder(Long customerId, Long cartId) {
        Customer customer = new Customer();
        customer.setId(customerId);

        Cart cart = cartRepository.findById(cartId).orElse(null);

        if (cart != null) {
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setCreationDate(new Date());
            purchaseOrder.setCustomer(customer);

            List<ProductInCart> productsInCart = cart.getProducts();

            for (ProductInCart productInCart : productsInCart) {
                Product product = productInCart.getProduct();


                double productPriceAtOrderDate = product.getProductPrice();
                ProductInOrder productInOrder = new ProductInOrder();
                productInOrder.setProduct(product);
                productInOrder.setOrder(purchaseOrder);
                productInOrder.setProductCount(productInCart.getProductCount());
                productInOrder.setProductPriceAtOrderDate(productPriceAtOrderDate);

                purchaseOrder.getProducts().add(productInOrder);
            }

            orderRepository.save(purchaseOrder);


            for (ProductInCart productInCart : productsInCart) {
                Product product = productInCart.getProduct();
                int quantity = productInCart.getProductCount();
                product.setProductStockCount(product.getProductStockCount() - quantity);
                productRepository.save(product);
            }


            cart.getProducts().clear();
            cart.setProductCount(0);
            cart.setTotalPrice(0.0);
            cartRepository.save(cart);
        }
    }

    @Override
    public PurchaseOrder getOrderForCode(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }
}