package com.enoca.enoca.service;

import com.enoca.enoca.entity.Customer;
import com.enoca.enoca.entity.PurchaseOrder;
import com.enoca.enoca.repository.CustomerRepository;
import com.enoca.enoca.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Customer addCustomer(String name) {
        Customer customer = new Customer();
        customer.setName(name);
        return customerRepository.save(customer);
    }

    @Override
    public List<PurchaseOrder> getAllOrdersForCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }
}