package com.enoca.enoca.controller;

import com.enoca.enoca.entity.PurchaseOrder;
import com.enoca.enoca.service.CustomerService;
import com.enoca.enoca.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/add")
    public String showAddCustomerForm() {
        return "add-customer";
    }

    @PostMapping("/add")
    public String addCustomer(@RequestParam String name) {
        customerService.addCustomer(name);
        return "redirect:/customer/list";
    }


    @GetMapping("/{customerId}/orders")
    public String listOrdersForCustomer(@PathVariable Long customerId, Model model) {
        List<PurchaseOrder> purchaseOrders = customerService.getAllOrdersForCustomer(customerId);
        model.addAttribute("orders", purchaseOrders);
        return "order-list";
    }
}
