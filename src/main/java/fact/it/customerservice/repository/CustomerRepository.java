package fact.it.customerservice.repository;

import fact.it.customerservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findCustomerByLicensePlate(String licensePlate);
}
