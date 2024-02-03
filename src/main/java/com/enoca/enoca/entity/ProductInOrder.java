package com.enoca.enoca.entity;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Data
public class ProductInOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private PurchaseOrder order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int productCount;

    private double productPriceAtOrderDate;
}