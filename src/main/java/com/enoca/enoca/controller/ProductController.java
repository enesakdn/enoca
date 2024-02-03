package com.enoca.enoca.controller;

import com.enoca.enoca.entity.Product;
import com.enoca.enoca.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public String listAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping("/{productId}")
    public String viewProduct(@PathVariable Long productId, Model model) {
        Product product = productService.getProduct(productId);
        model.addAttribute("product", product);
        return "product-details";
    }

    @GetMapping("/add")
    public String showAddProductForm() {
        return "add-product";
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam String productName,
                             @RequestParam int stockCount,
                             @RequestParam double price) {
        productService.createProduct(productName, stockCount, price);
        return "redirect:/product/list";
    }

    @GetMapping("/{productId}/edit")
    public String showEditProductForm(@PathVariable Long productId, Model model) {
        Product product = productService.getProduct(productId);
        model.addAttribute("product", product);
        return "edit-product";
    }

    @PostMapping("/{productId}/edit")
    public String editProduct(@PathVariable Long productId,
                              @RequestParam String productName,
                              @RequestParam int stockCount,
                              @RequestParam double price) {
        productService.updateProduct(productId, productName, stockCount, price);
        return "redirect:/product/list";
    }

    @PostMapping("/{productId}/delete")
    public String deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return "redirect:/product/list";
    }
}
