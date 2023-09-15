package guru.springframework.spring6restmvc.service;

import guru.springframework.spring6restmvc.dto.CustomerDto;
import guru.springframework.spring6restmvc.mapper.CustomerMapper;
import guru.springframework.spring6restmvc.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class CustomerServiceJPA implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    @Override
    public Optional<CustomerDto> getCustomerById(UUID uuid) {

        return Optional
                .ofNullable(customerMapper.customerToCustomerDto(customerRepository.findById(uuid)
                        .orElse(null)));
    }

    @Override
    public List<CustomerDto> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(customerMapper::customerToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto saveNewCustomer(CustomerDto customerDto) {

        return customerMapper
                .customerToCustomerDto(customerRepository
                        .save(customerMapper.customerDtoToCustomer(customerDto)));
    }

    @Override
    public Optional<CustomerDto> updateCustomerById(UUID customerId, CustomerDto customerDto) {

        AtomicReference<Optional<CustomerDto>> atomicReference = new AtomicReference<>();

       customerRepository.findById(customerId).ifPresentOrElse(foundCustomer -> {
           foundCustomer.setName(customerDto.getName());
           atomicReference
                   .set(Optional.of(customerMapper
                           .customerToCustomerDto(customerRepository.save(foundCustomer))));
       }, () -> {
           atomicReference.set(Optional.empty());
       });
       return atomicReference.get();
    }

    @Override
    public Boolean deleteCustomerById(UUID customerId) {
        if(customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
            return true;
        }
        return false;
    }

    @Override
    public Optional<CustomerDto> patchCustomerById(UUID customerId, CustomerDto customerDto) {

        AtomicReference<Optional<CustomerDto>> atomicReference = new AtomicReference<>();

        customerRepository.findById(customerId).ifPresentOrElse(foundCustomer -> {
            if (StringUtils.hasText(customerDto.getName())){
                foundCustomer.setName(customerDto.getName());
            }
            atomicReference.set(Optional.of(customerMapper
                    .customerToCustomerDto(customerRepository.save(foundCustomer))));
        }, () -> {
            atomicReference.set(Optional.empty());
        });

        return atomicReference.get();
    }
}
