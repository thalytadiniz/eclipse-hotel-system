package com.thalytadiniz.eclipse_hotel_system.service.Customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.thalytadiniz.eclipse_hotel_system.dto.Customer.CustomerInputDTO;
import com.thalytadiniz.eclipse_hotel_system.dto.Customer.CustomerOutputDTO;
import com.thalytadiniz.eclipse_hotel_system.entity.CustomerHotel;
import com.thalytadiniz.eclipse_hotel_system.exception.CustomerAlreadyExistsException;
import com.thalytadiniz.eclipse_hotel_system.exception.EntityNotFoundException;
import com.thalytadiniz.eclipse_hotel_system.repository.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerHotel {

    private final CustomerRepository customerRepository;
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerOutputDTO createCustomer(CustomerInputDTO customerInputDTO) {
        logger.info("Criando cliente: {}", customerInputDTO.getName());

        if (customerRepository.existsByEmail(customerInputDTO.getEmail())) {
            throw new CustomerAlreadyExistsException("Já existe um cliente com o e-mail: " + customerInputDTO.getEmail());
        }

        CustomerHotel customer = new CustomerHotel();
        customer.setName(customerInputDTO.getName());
        customer.setEmail(customerInputDTO.getEmail());
        customer.setPhone(customerInputDTO.getPhone());

        CustomerHotel savedCustomer = customerRepository.save(customer);

        return convertToOutputDTO(savedCustomer);
    }

    @Override
    public List<CustomerOutputDTO> getAllCustomers() {
        logger.info("Buscando todos os clientes");
        return customerRepository.findAll().stream()
                .map(this::convertToOutputDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerOutputDTO getCustomerById(Long id) {
        logger.info("Buscando cliente pelo ID: {}", id);
        CustomerHotel customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + id));
        return convertToOutputDTO(customer);
    }

    @Override
    public CustomerOutputDTO updateCustomer(Long id, CustomerInputDTO customerInputDTO) {
        logger.info("Atualizando cliente com ID: {}", id);
        CustomerHotel customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + id));

        customer.setName(customerInputDTO.getName());
        customer.setEmail(customerInputDTO.getEmail());
        customer.setPhone(customerInputDTO.getPhone());

        CustomerHotel updatedCustomer = customerRepository.save(customer);

        return convertToOutputDTO(updatedCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
        logger.info("Deletando cliente com ID: {}", id);
        if (!customerRepository.existsById(id)) {
            throw new EntityNotFoundException("Cliente não encontrado com ID: " + id);
        }
        customerRepository.deleteById(id);
    }

    private CustomerOutputDTO convertToOutputDTO(CustomerHotel customer) {
        CustomerOutputDTO outputDTO = new CustomerOutputDTO();
        outputDTO.setId(customer.getId());
        outputDTO.setName(customer.getName());
        outputDTO.setEmail(customer.getEmail());
        outputDTO.setPhone(customer.getPhone());
        outputDTO.setCreatedAt(customer.getCreatedAt());
        return outputDTO;
    }
}