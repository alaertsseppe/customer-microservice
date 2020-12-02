package fact.it.customerservice.controller;

import fact.it.customerservice.repository.CustomerRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    private CustomerRepository customerRepository;

    // GET /customers
    // GET /customers/{licensePlate}
    // POST /customers
    // PUT /customers/{id}
}
