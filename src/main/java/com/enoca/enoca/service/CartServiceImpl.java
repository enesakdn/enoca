package com.enoca.enoca.service;

import com.enoca.enoca.entity.Cart;
import com.enoca.enoca.entity.Product;
import com.enoca.enoca.entity.ProductInCart;
import com.enoca.enoca.repository.CartRepository;
import com.enoca.enoca.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Cart getCart(Long customerId) {
        return cartRepository.findByCustomerId(customerId).orElse(null);
    }

    @Override
    public void updateCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public void emptyCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart != null) {
            cart.getProducts().clear();
            cart.setProductCount(0);
            cart.setTotalPrice(0.0);
            updateCart(cart);
        }
    }

    @Override
    public void addProductToCart(Long cartId, Long productId, int quantity) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);

        if (cart != null && product != null && product.getProductStockCount() >= quantity) {
            ProductInCart productInCart = new ProductInCart();
            productInCart.setProduct(product);
            productInCart.setCart(cart);
            productInCart.setProductCount(quantity);

            cart.getProducts().add(productInCart);
            cart.setProductCount(cart.getProductCount() + quantity);
            cart.setTotalPrice(cart.getTotalPrice() + (quantity * product.getProductPrice()));

            updateCart(cart);

            product.setProductStockCount(product.getProductStockCount() - quantity);
            productRepository.save(product);
        }
    }

    @Override
    public void removeProductFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);

        if (cart != null && product != null) {
            Optional<ProductInCart> productInCartOptional = cart.getProducts().stream()
                    .filter(p -> p.getProduct().getId().equals(productId))
                    .findFirst();

            if (productInCartOptional.isPresent()) {
                ProductInCart productInCart = productInCartOptional.get();
                int quantity = productInCart.getProductCount();

                cart.getProducts().remove(productInCart);
                cart.setProductCount(cart.getProductCount() - quantity);
                cart.setTotalPrice(cart.getTotalPrice() - (quantity * product.getProductPrice()));

                updateCart(cart);

                product.setProductStockCount(product.getProductStockCount() + quantity);
                productRepository.save(product);
            }
        }
    }
}