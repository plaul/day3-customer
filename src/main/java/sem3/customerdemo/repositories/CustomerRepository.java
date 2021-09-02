package sem3.customerdemo.repositories;

import org.springframework.data.repository.CrudRepository;
import sem3.customerdemo.entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer,Integer> { }
