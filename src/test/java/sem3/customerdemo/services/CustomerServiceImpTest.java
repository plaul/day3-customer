package sem3.customerdemo.services;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sem3.customerdemo.dtos.CustomerDTO;
import sem3.customerdemo.entities.Customer;
import sem3.customerdemo.repositories.CustomerRepository;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


class CustomerServiceImpTest {

    @Mock
    CustomerRepository customerRepository;

    CustomerServiceImp customerServiceImp;

    private AutoCloseable closeable;

    @BeforeEach public void openMocks() {
        closeable = MockitoAnnotations.openMocks(this);
        //Setup your CustomerServiceImp using the Mocked repository
        customerServiceImp = new CustomerServiceImp(customerRepository);

        Iterable<Customer> fakeCustomers = Arrays.asList(
                new Customer(1,"Peter","Pan","pp@a.dk","1234",null,null),
                new Customer(1,"Donald","Duck","dd@a.dk","123456",null,null)
        );
        when(customerRepository.findAll()).thenReturn(fakeCustomers);
    }

    @AfterEach
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    void getExpectedNumberOfCustomers() {
        long count = customerServiceImp.getAllCustomers(true).size();
        assertEquals(2,count);
    }

    @Test
    void customersContainPeterAndDonald() {
        List<CustomerDTO> customers = customerServiceImp.getAllCustomers(false);
        assertThat(customers, containsInAnyOrder(
                hasProperty("firstName", is("Peter")),
                hasProperty("firstName", is("Donald"))
        ));
    }
}