package fact.it.customerservice.controller;

import fact.it.customerservice.model.Customer;
import fact.it.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
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
    public Customer findCustomerByLicensePlate(@PathVariable String licensePlate){
        return customerRepository.findCustomerByLicensePlate(licensePlate);
    }

    // GET /customers/{phoneNumber}
    @GetMapping("/customers/{phoneNumber}")
    public Customer findCustomerByPhoneNumber(@PathVariable String phoneNumber){
        return customerRepository.findCustomerByPhoneNumber(phoneNumber);
    }

    // GET /customers/{email}
    @GetMapping("/customers/{email}")
    public Customer findCustomerByEmail(@PathVariable String email){
        return customerRepository.findCustomerByEmail(email);
    }

    // GET /customers/{uuid}
    @GetMapping("/customers/{uuid}")
    public Customer findCustomerByUuid(@PathVariable String uuid){
        return customerRepository.findCustomerByUuid(uuid);
    }


    // DB Opvullen
    @PostConstruct
    public void fillDB(){
        if (customerRepository.count() == 0){
            customerRepository.save(new Customer("Jef", "Maes", "jef.maes@example.com", "0495662516", "Volkswagen", "Tiguan", "1-dfs-593"));
            customerRepository.save(new Customer("Karel", "Peeters", "karel.peeters@example.com", "0495662516", "Peugeot", "3008", "1-pdm-379"));
            customerRepository.save(new Customer("Marie", "Jacobs", "marie.jacobs@example.com", "0495662516", "Audi", "A6", "1-coe-285"));
        }
    }

}
