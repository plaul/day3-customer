//package sem3.customerdemo.services;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.jdbc.Sql;
//import sem3.customerdemo.dtos.CustomerDTO;
//import sem3.customerdemo.dtos.CustomerInput;
//import sem3.customerdemo.repositories.CustomerRepository;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.MatcherAssert.assertThat;
//
//@DataJpaTest
//public class CustomerServiceTestV2 {
//
//
//    @Autowired
//    CustomerRepository customerRepository;
//
//    CustomerServiceImp custService;
//
//    @BeforeEach
//    public void initService(){
//        custService = new CustomerServiceImp(customerRepository);
//    }
//
//    @Test
//    @Sql("/createCustomers.sql")
//    void getExpectedNumberOfCustomers() {
//        long count = custService.getAllCustomers(true).size();
//        assertEquals(3,count);
//    }
//
//    /*
//    Verify that dateCreated is null
//    NULL values will not be included in the final JSON mapped from a CustomerDTO class
//     */
//    @Test
//    @Sql("/createCustomers.sql")
//    public void doesNotContainDate(){
//        List<CustomerDTO> customers = custService.getAllCustomers(false);
//        assertEquals(null,customers.get(0).getDateCreated());
//        assertEquals(null,customers.get(1).getDateCreated());
//        assertEquals(null,customers.get(2).getDateCreated());
//    }
//
//    @Test
//    @Sql("/createCustomers.sql")
//    public void doesOnlyContainFirstLastName(){
//        List<CustomerDTO> customers = custService.getAllCustomers(true);
//        CustomerDTO firstCust = customers.get(0);
//        assertEquals(null,firstCust.getCustomerId());
//        assertEquals(null,firstCust.getEmail());
//        assertEquals(null,firstCust.getPhone());
//        assertEquals(null,firstCust.getDateCreated());
//    }package sem3.customerdemo.services;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.jdbc.Sql;
//import sem3.customerdemo.dtos.CustomerDTO;
//import sem3.customerdemo.dtos.CustomerInput;
//import sem3.customerdemo.repositories.CustomerRepository;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.MatcherAssert.assertThat;
//
//    @DataJpaTest
//    public class CustomerServiceTestSimple {
//
//
//        @Autowired
//        CustomerRepository customerRepository;
//
//        CustomerServiceImp custService;
//
//        @BeforeEach
//        public void initService(){
//            custService = new CustomerServiceImp(customerRepository);
//        }
//
//        @Test
//        @Sql("/createCustomers.sql")
//        void getExpectedNumberOfCustomers() {
//            long count = custService.getAllCustomers(true).size();
//            assertEquals(3,count);
//        }
//
//        /*
//        Verify that dateCreated is null
//        NULL values will not be included in the final JSON mapped from a CustomerDTO class
//         */
//        @Test
//        @Sql("/createCustomers.sql")
//        public void doesNotContainDate(){
//            List<CustomerDTO> customers = custService.getAllCustomers(false);
//            assertEquals(null,customers.get(0).getDateCreated());
//            assertEquals(null,customers.get(1).getDateCreated());
//            assertEquals(null,customers.get(2).getDateCreated());
//        }
//
//        @Test
//        @Sql("/createCustomers.sql")
//        public void doesOnlyContainFirstLastName(){
//            List<CustomerDTO> customers = custService.getAllCustomers(true);
//            CustomerDTO firstCust = customers.get(0);
//            assertEquals(null,firstCust.getCustomerId());
//            assertEquals(null,firstCust.getEmail());
//            assertEquals(null,firstCust.getPhone());
//            assertEquals(null,firstCust.getDateCreated());
//        }
//
//        @Test
//        @Sql("/createCustomers.sql")
//        public void addCustomer(){
//            CustomerInput custInput = new CustomerInput("aa","bb","cc","dd");
//            CustomerDTO newCust = custService.addCustomer(custInput);
//            assertEquals(103,newCust.getCustomerId());
//            assertEquals(null,newCust.getDateCreated());
//        }
//
//    }
//
//
//    @Test
//    @Sql("/createCustomers.sql")
//    public void addCustomer(){
//        CustomerInput custInput = new CustomerInput("aa","bb","cc","dd");
//        CustomerDTO newCust = custService.addCustomer(custInput);
//        assertEquals(103,newCust.getCustomerId());
//        assertEquals(null,newCust.getDateCreated());
//    }
//
//}
