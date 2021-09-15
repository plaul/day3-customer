package sem3.customerdemo.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import sem3.customerdemo.dtos.CustomerDTO;
import sem3.customerdemo.dtos.CustomerInput;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase
@EnableAutoConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerTest {

    private final String BASE_PATH = "/api/customers";
    private final HttpHeaders headers = new HttpHeaders();

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;



    @Test
    void getAllCustomers() {
        ResponseEntity<List<CustomerDTO>> response = getAllCustomerDTOs();
        assertEquals(3,response.getBody().size());
    }
    //Utility method used by several tests
    private ResponseEntity<List<CustomerDTO>> getAllCustomerDTOs() {
        HttpEntity<String> entity = new HttpEntity<>(null,headers);
        ResponseEntity<List<CustomerDTO>> response = restTemplate.exchange(makeUrl(BASE_PATH),
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<CustomerDTO>>() {});
        return response;
    }

    @Test
    @Sql("/createCustomers.sql")
    void getCustomer() {
        HttpEntity<String> entity = new HttpEntity<>(null,headers);
        ResponseEntity<CustomerDTO> response = restTemplate.exchange(makeUrl(BASE_PATH+"/100"),
                HttpMethod.GET,
                entity,
                CustomerDTO.class
        );

        assertEquals("aaa",response.getBody().getFirstName());
    }

    @Test
    @Sql("/createCustomers.sql")
    void createCustomer() {
        CustomerInput customerInput = new CustomerInput("Kurt","Wonnegut","a@b.dk","1234");
        HttpEntity<CustomerInput> entity = new HttpEntity<>(customerInput,headers);
        ResponseEntity<CustomerDTO> newCust = restTemplate.exchange(makeUrl(BASE_PATH),HttpMethod.POST,entity,CustomerDTO.class);
        assertEquals(103,newCust.getBody().getCustomerId());
        assertEquals("Kurt",newCust.getBody().getFirstName());
    }

    @Test
    @Sql("/createCustomers.sql")
    void editCustomer() {
        CustomerDTO cust101 = new CustomerDTO(101,"newFirstName","bb2","newMail@a.dk","1234",null);
        HttpEntity<CustomerDTO> entity = new HttpEntity<>(cust101,headers);
        ResponseEntity<CustomerDTO> newCust = restTemplate.exchange(makeUrl(BASE_PATH+"/101"),HttpMethod.PUT,entity,CustomerDTO.class);
        assertEquals(101,newCust.getBody().getCustomerId());
        assertEquals("newFirstName",newCust.getBody().getFirstName());
        assertEquals("bb2",newCust.getBody().getLastName());
        assertEquals("newMail@a.dk",newCust.getBody().getEmail());
    }

    @Test
    @Sql("/createCustomers.sql")
    void deleteCustomer() {
        HttpEntity<String> entity = new HttpEntity<>(null,headers);
        ResponseEntity<CustomerDTO> response = restTemplate.exchange(makeUrl(BASE_PATH+"/100"),
                HttpMethod.DELETE,
                entity,
                CustomerDTO.class
        );
        assertEquals(200,response.getStatusCodeValue());
        ResponseEntity<List<CustomerDTO>> all = getAllCustomerDTOs();
        assertEquals(2,all.getBody().size());

    }

    private String makeUrl(String path){
        String pathBuilded = "http://localhost:"+port+path;
        System.out.println(pathBuilded);
        return pathBuilded;
    }
}