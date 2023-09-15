package guru.springframework.spring6restmvc.mapper;

import guru.springframework.spring6restmvc.dto.CustomerDto;
import guru.springframework.spring6restmvc.entity.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDto customerDto);

    CustomerDto customerToCustomerDto(Customer customer);
}
