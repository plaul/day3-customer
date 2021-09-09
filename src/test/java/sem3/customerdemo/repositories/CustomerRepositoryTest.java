package sem3.customerdemo.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import sem3.customerdemo.entities.Customer;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;

    @Test
    @Sql("/createCustomers.sql")
    public void testCount(){
        long result = customerRepository.count();
        assertEquals(3,result);
    }

    @Test
    @Sql("/createCustomers.sql")
    public void addCustomer(){
        Customer customer = new Customer("zzz","yyy","ttt");
        assertEquals(0,customer.getCustomerId());
        customerRepository.save(customer);
       assertTrue(customer.getCustomerId()>0);
    }
}