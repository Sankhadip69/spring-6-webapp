package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.dto.CustomerDto;
import guru.springframework.spring6restmvc.entity.Customer;
import guru.springframework.spring6restmvc.mapper.CustomerMapper;
import guru.springframework.spring6restmvc.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerControllerIT {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerMapper customerMapper;

    @Rollback
    @Transactional
    @Test
    void deleteByIdFound() {
        Customer customer = customerRepository.findAll().get(0);
        ResponseEntity responseEntity = customerController.deleteCustomerById(customer.getId());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));
        assertThat(customerRepository.findById(customer.getId()).isEmpty());
    }

    @Test
    void testDeleteNotFound() {
        assertThrows(NotFoundException.class, () -> {
            customerController.deleteCustomerById(UUID.randomUUID());
        });
    }

    @Test
    void testUpdateNotFound() {
        assertThrows(NotFoundException.class, () -> {
            customerController.updateCustomerByID(UUID.randomUUID(), CustomerDto.builder().build());
        });
    }

    @Rollback
    @Transactional
    @Test
    void updateExistingCustomer() {
        Customer customer = customerRepository.findAll().get(0);
        CustomerDto customerDto = customerMapper.customerToCustomerDto(customer);
        customerDto.setId(null);
        customerDto.setVersion(null);
        final String customerName = "Customer 4";
        customerDto.setName(customerName);

        ResponseEntity responseEntity = customerController.updateCustomerByID(customer.getId(), customerDto);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Customer updatedCustomer = customerRepository.findById(customer.getId()).get();
        assertThat(updatedCustomer.getName()).isEqualTo(customerName);

    }

    //Junit test for positive scenario
    @Test
    void testGetById() {
        Customer customer = customerRepository.findAll().get(0);

        CustomerDto customerDto = customerController.getCustomerById(customer.getId());

        assertThat(customerDto).isNotNull();
    }

    //Junit test for negative scenario
    @Test
    void testGetByIdNotFound() {
        assertThrows(NotFoundException.class, () -> {
            customerController.getCustomerById(UUID.randomUUID());
        });
    }

    //Junit test for positive scenario
    @Test
    void testListAll() {
        List<CustomerDto> customerDtoList = customerController.listAllCustomers();

        assertThat(customerDtoList.size()).isEqualTo(3);
    }
    //Junit test for negative scenario
    @Rollback
    @Transactional
    @Test
    void testListAllEmptyList() {
        customerRepository.deleteAll();
        List<CustomerDto> customerDtoList = customerController.listAllCustomers();


        assertThat(customerDtoList.size()).isEqualTo(0);
    }

}