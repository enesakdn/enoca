package com.enoca.enoca.controller;

import com.enoca.enoca.entity.PurchaseOrder;
import com.enoca.enoca.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{orderId}")
    public String viewOrder(@PathVariable Long orderId, Model model) {
        PurchaseOrder purchaseOrder = orderService.getOrderForCode(orderId);
        model.addAttribute("order", purchaseOrder);
        return "order-details";
    }

    @PostMapping("/{customerId}/{cartId}/place")
    public String placeOrder(@PathVariable Long customerId, @PathVariable Long cartId) {
        orderService.placeOrder(customerId, cartId);
        return "redirect:/customer/" + customerId + "/orders";
    }
}
