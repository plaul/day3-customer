package sem3.customerdemo.services;

import sem3.customerdemo.dtos.CustomerDTO;
import sem3.customerdemo.dtos.CustomerInput;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers(boolean simple);
    CustomerDTO addCustomer(CustomerInput customerInput);
    CustomerDTO findCustomerById(int id);
}
