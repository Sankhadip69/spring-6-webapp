package guru.springframework.spring6restmvc.service;

import guru.springframework.spring6restmvc.dto.CustomerDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {

    Optional<CustomerDto> getCustomerById(UUID uuid);

    List<CustomerDto> getAllCustomers();

    CustomerDto saveNewCustomer(CustomerDto customerDto);

    Optional<CustomerDto> updateCustomerById(UUID customerId, CustomerDto customerDto);

    Boolean deleteCustomerById(UUID customerId);

    Optional<CustomerDto> patchCustomerById(UUID customerId, CustomerDto customerDto);
}
