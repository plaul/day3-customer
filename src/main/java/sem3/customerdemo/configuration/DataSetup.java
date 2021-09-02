package sem3.customerdemo.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import sem3.customerdemo.entities.Customer;
import sem3.customerdemo.repositories.CustomerRepository;

@Configuration
public class DataSetup implements CommandLineRunner {

    private CustomerRepository customerRepository;

    public DataSetup(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        customerRepository.save(new Customer("xxx","yyy","hhhh"));
    }
}
