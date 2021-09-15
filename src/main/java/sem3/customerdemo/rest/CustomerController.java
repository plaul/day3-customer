package sem3.customerdemo.rest;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sem3.customerdemo.entities.Customer;
import sem3.customerdemo.repositories.CustomerRepository;

import java.util.Optional;

@RestController
//produces is set to get the correct content-type in the swagger documentation
@RequestMapping(path = "/api/customers",produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    private CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    Customer getCustomer(@PathVariable int id){

        return customerRepository.findById(id).orElseThrow();
    }

    @PostMapping
    Customer createCustomer(@RequestBody Customer cust){
        Customer newCust=  customerRepository.save(cust);
        return newCust;
    }

    /**
     * This versions throws an exception if the customer with the provided id does not exist
     */
    @PutMapping("/{id}")
    Customer editCustomer(@RequestBody Customer cust,@PathVariable int id) throws Exception {
        Customer custOrg = customerRepository.findById(id).orElseThrow();
        /* Remember PUT must supply ALL values, if not:
           We must do the following with all values not supplied by the client
           dateEdited will obviously be set by Hibernate by the repositories call to save
        */
        cust.setDateCreated(custOrg.getDateCreated());
        return customerRepository.save(cust);
    }

    /**
     * This version creates a customer with the provided id, if he did NOT exist
     * If PUT creates new entity it MUST return 201 (CREATED)
     * IMPORTANT --> Observe the return type used, in order to set the status types
     */
    @PutMapping(value = "/v2/{id}")
    ResponseEntity<Customer> editOrAddCustomer(@RequestBody Customer cust, @PathVariable int id) throws Exception {
        Optional<Customer> optCustomer = customerRepository.findById(id);
        HttpStatus status = HttpStatus.CREATED;
        if(optCustomer.isPresent()){
           Customer originalCustomer = optCustomer.get();
          // Remember PUT must supply ALL values, if not:
          // We must do the following with all values not supplied by the client
          // dateEdited will obviously be set by Hibernate by the repositories call to save
            cust.setDateCreated(originalCustomer.getDateCreated());
            status = HttpStatus.OK; //Return 200 for existing Customers (normal update)
        }
        return ResponseEntity
                .status(status)
                .body(cust);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("Delete the student with the provided id. The response does NOT contain anything in the body")
    public void deleteCustomer(@PathVariable int id){
        customerRepository.deleteById(id);
    }
}
