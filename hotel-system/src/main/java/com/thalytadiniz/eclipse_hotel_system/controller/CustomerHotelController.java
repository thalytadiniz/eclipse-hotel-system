package com.thalytadiniz.eclipse_hotel_system.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.thalytadiniz.eclipse_hotel_system.dto.Customer.CustomerInputDTO;
import com.thalytadiniz.eclipse_hotel_system.dto.Customer.CustomerOutputDTO;
import com.thalytadiniz.eclipse_hotel_system.service.Customer.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerHotelController {

    private final CustomerService customerService;

    public CustomerHotelController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public CustomerOutputDTO createCustomer(@RequestBody CustomerInputDTO customerInputDTO) {
        return customerService.createCustomer(customerInputDTO);
    }

    @GetMapping
    public List<CustomerOutputDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerOutputDTO getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PutMapping("/{id}")
    public CustomerOutputDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerInputDTO customerInputDTO) {
        return customerService.updateCustomer(id, customerInputDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}