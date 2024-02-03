package com.enoca.enoca.service;

import com.enoca.enoca.entity.Customer;
import com.enoca.enoca.entity.PurchaseOrder;

import java.util.List;

public interface CustomerService {
    Customer addCustomer(String name);

    List<PurchaseOrder> getAllOrdersForCustomer(Long customerId);
}