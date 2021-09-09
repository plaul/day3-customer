package sem3.customerdemo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sem3.customerdemo.entities.Customer;


/**
 * Mapper class for used to MAP JSON for new Customers
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CustomerInput {
    String firstName;
    String lastName;
    String email;
    String phone;

    /**
     * Get a Customer entity, given a customerInput
     * @param ci
     * @return
     */
    public static Customer customerFromCustomerInput(CustomerInput ci){
        return new Customer(ci.getFirstName(), ci.getLastName(), ci.getEmail(), ci.getPhone());
    }
}
