package com.enoca.enoca.service;

import com.enoca.enoca.entity.PurchaseOrder;

public interface OrderService {
    void placeOrder(Long customerId, Long cartId);

    PurchaseOrder getOrderForCode(Long orderId);
}