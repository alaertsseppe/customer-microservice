package fact.it.customerservice.controller;

import fact.it.customerservice.model.Customer;
import fact.it.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    // GET /customers
    @GetMapping("/customers")
    public List<Customer> findAllCustomers(){
        return customerRepository.findAll();
    }

    // GET /customers/{licensePlate}
    @GetMapping("/customers/{licensePlate}")
    public List<Customer> findCustomerByLicensePlate(@PathVariable String licensePlate){
        return customerRepository.findCustomerByLicensePlate(licensePlate);
    }

    // POST /customers
    // PUT /customers/{id}
}
