package com.thalytadiniz.eclipse_hotel_system.service.Customer;

import java.util.List;

import com.thalytadiniz.eclipse_hotel_system.dto.Customer.CustomerInputDTO;
import com.thalytadiniz.eclipse_hotel_system.dto.Customer.CustomerOutputDTO;

public interface ICustomerHotel {
    CustomerOutputDTO createCustomer(CustomerInputDTO customerInputDTO); 
    List<CustomerOutputDTO> getAllCustomers(); 
    CustomerOutputDTO getCustomerById(Long id); 
    CustomerOutputDTO updateCustomer(Long id, CustomerInputDTO customerInputDTO); 
    void deleteCustomer(Long id);
}