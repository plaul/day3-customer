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
        customerRepository.deleteAll();
        customerRepository.save(new Customer("Kurt","Wonnegut","kw@somewhere.com"));
        customerRepository.save(new Customer("Hanne","Wonnegut","hw@somewhere.com"));
        Customer pp = new Customer("Peter","Pan","pp@somewhere.com");
        customerRepository.save(pp);
        pp.setEmail("a@b.dk");
        customerRepository.save(pp);
    }
}
