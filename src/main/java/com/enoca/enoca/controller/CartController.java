package com.enoca.enoca.controller;

import com.enoca.enoca.entity.Cart;
import com.enoca.enoca.service.CartService;
import com.enoca.enoca.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @GetMapping("/{customerId}")
    public String viewCart(@PathVariable Long customerId, Model model) {
        Cart cart = cartService.getCart(customerId);
        model.addAttribute("cart", cart);
        return "cart";
    }

    @PostMapping("/{customerId}/add")
    public String addProductToCart(@PathVariable Long customerId,
                                   @RequestParam Long productId,
                                   @RequestParam int quantity) {
        cartService.addProductToCart(customerId, productId, quantity);
        return "redirect:/cart/" + customerId;
    }

    @PostMapping("/{customerId}/remove")
    public String removeProductFromCart(@PathVariable Long customerId,
                                        @RequestParam Long productId) {
        cartService.removeProductFromCart(customerId, productId);
        return "redirect:/cart/" + customerId;
    }

    @PostMapping("/{cartId}/empty")
    public String emptyCart(@PathVariable Long cartId) {
        cartService.emptyCart(cartId);
        return "redirect:/cart/" + cartId;
    }
}