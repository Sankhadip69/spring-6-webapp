package guru.springframework.spring6restmvc.service.impl;

import guru.springframework.spring6restmvc.dto.CustomerDto;
import guru.springframework.spring6restmvc.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private Map<UUID,CustomerDto> customerMap;

    public CustomerServiceImpl() {

        CustomerDto customeDto1 = CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("Customer 1")
                .version(1)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        CustomerDto customeDto2 = CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("Customer 2")
                .version(1)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        CustomerDto customeDto3 = CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("Customer 3")
                .version(1)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        customerMap = new HashMap<>();
        customerMap.put(customeDto1.getId(), customeDto1);
        customerMap.put(customeDto2.getId(), customeDto2);
        customerMap.put(customeDto3.getId(), customeDto3);
    }

    @Override
    public Optional<CustomerDto> getCustomerById(UUID uuid) {

        return Optional.of(customerMap.get(uuid));
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public CustomerDto saveNewCustomer(CustomerDto customerDto) {
        CustomerDto savedCustomer = CustomerDto.builder()
                .id(UUID.randomUUID())
                .name(customerDto.getName())
                .version(customerDto.getVersion())
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        customerMap.put(savedCustomer.getId(),savedCustomer);
        return savedCustomer;
    }

    @Override
    public Optional<CustomerDto> updateCustomerById(UUID customerId, CustomerDto customerDto) {
        CustomerDto existing = customerMap.get(customerId);
        existing.setName(customerDto.getName());
        return Optional.of(existing);
    }

    @Override
    public Boolean deleteCustomerById(UUID customerId) {

        customerMap.remove(customerId);
        return true;
    }

    @Override
    public Optional<CustomerDto> patchCustomerById(UUID customerId, CustomerDto customerDto) {
        CustomerDto existing = customerMap.get(customerId);

        if (StringUtils.hasText(customerDto.getName())) {
            existing.setName(customerDto.getName());
        }

        return Optional.of(existing);
    }
}
