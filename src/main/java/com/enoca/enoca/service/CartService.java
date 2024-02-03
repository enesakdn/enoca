package com.enoca.enoca.service;

import com.enoca.enoca.entity.Cart;

public interface CartService {
    Cart getCart(Long customerId);

    void updateCart(Cart cart);

    void emptyCart(Long cartId);

    void addProductToCart(Long cartId, Long productId, int quantity);

    void removeProductFromCart(Long cartId, Long productId);
}