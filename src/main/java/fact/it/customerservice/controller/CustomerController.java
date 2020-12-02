package fact.it.customerservice.controller;

import fact.it.customerservice.model.Customer;
import fact.it.customerservice.repository.CustomerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class CustomerController {

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

    // DB Opvullen
    @PostConstruct
    public void fillDB(){
        if (customerRepository.count() == 0){
            customerRepository.save(new Customer(1, "Jef", "Maes", "jef.maes@example.com", "0495662516", "Volkswagen", "Tiguan", "1-dfs-593"));
            customerRepository.save(new Customer(2, "Karel", "Peeters", "karel.peeters@example.com", "0495662516", "Peugeot", "3008", "1-pdm-379"));
            customerRepository.save(new Customer(3, "Marie", "Jacobs", "marie.jacobs@example.com", "0495662516", "Audi", "A6", "1-coe-285"));
        }
    }

}
