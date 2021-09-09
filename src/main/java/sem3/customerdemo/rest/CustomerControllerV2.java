package sem3.customerdemo.rest;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sem3.customerdemo.dtos.CustomerDTO;
import sem3.customerdemo.dtos.CustomerInput;
import sem3.customerdemo.services.CustomerService;

/* IMPORTANT - OBSERVE
   No imports of Entity classes or Repository classes
 */

import java.util.List;


@RestController
@RequestMapping(path = "/api/version2/customers", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerControllerV2 {

    private CustomerService customerService;

    public CustomerControllerV2(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    List<CustomerDTO> getAllCustomers(@RequestParam(required = false) String type){
        boolean simple = false;
        if(type != null && type.equals("simple")){
            simple = true;
        }
        return customerService.getAllCustomers(simple);
    }

    @GetMapping(value = "/{id}")
    @ApiModelProperty(position = 5, hidden = true)
    CustomerDTO getCustomer(@PathVariable int id) {
        return customerService.findCustomerById(id);
    }

    @PostMapping
    @ApiModelProperty(position = 5, hidden = true)  //Does not work with the current version of Swagger
    CustomerDTO createCustomer(@RequestBody CustomerInput cust) {
        return customerService.addCustomer(cust);
    }
}
