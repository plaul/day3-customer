package sem3.customerdemo.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sem3.customerdemo.entities.Customer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@NoArgsConstructor
@Getter @Setter
@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO {
    private Integer customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    //@JsonFormat(pattern = "dd-MM-YYYY HH:mm")
    private LocalDateTime dateCreated;

    public CustomerDTO(Customer c) {
        this.customerId = c.getCustomerId();
        this.firstName = c.getFirstName();
        this.lastName = c.getLastName();
        this.email = c.getEmail();
        this.phone = c.getPhone();
    }

    public CustomerDTO(Integer customerId, String firstName, String lastName) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /*
    Use this constructor with exactly the fields needed by passing in null values, for fields that should NOT be included
     */
    public CustomerDTO(Integer customerId, String firstName, String lastName, String email, String phone, LocalDateTime dateCreated) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.dateCreated = dateCreated;
    }

    /**
     * Use this method to get List<CustomerDTO> either with all fields from the DB, except dateCreated (see constructor)
     * or with only firstName and lastName if simple=true
     * @param all The List of Customers (Entities) that must be converted
     * @param simple If true, only firsName and lastName are included
     * @return
     */
    public static List<CustomerDTO> getCustomerDTOs(Iterable<Customer> all, boolean simple) {
        List<CustomerDTO> dtos = StreamSupport.stream(all.spliterator(), false)
                .map(c -> simple ? new CustomerDTO(null, c.getFirstName(), c.getLastName()) : new CustomerDTO(c))
                .collect(Collectors.toList());
        return dtos;
    }
}
