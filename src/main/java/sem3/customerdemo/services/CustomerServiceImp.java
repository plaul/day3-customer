package sem3.customerdemo.services;

import org.springframework.stereotype.Service;
import sem3.customerdemo.dtos.CustomerDTO;
import sem3.customerdemo.dtos.CustomerInput;
import sem3.customerdemo.entities.Customer;
import sem3.customerdemo.repositories.CustomerRepository;

import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService {
    CustomerRepository customerRepository;

    public CustomerServiceImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Returns a REPRESENTATION of all Customers
     * @param simple If true, only firstName,lastName will be included otherwise all fields from the underlying DB except dateCreated
     * @return
     */
    @Override
    public List<CustomerDTO> getAllCustomers(boolean simple){
        Iterable<Customer> allCustomers = customerRepository.findAll();
        return CustomerDTO.getCustomerDTOs(allCustomers,simple);
    }

    @Override
    public CustomerDTO addCustomer(CustomerInput customerInput) {
        Customer newCustomer = customerRepository.save(CustomerInput.customerFromCustomerInput(customerInput));
        return new CustomerDTO(newCustomer);
    }

    @Override
    public CustomerDTO findCustomerById(int id) {
        Customer found = customerRepository.findById(id).orElseThrow();
        return new CustomerDTO(found);
    }

}
