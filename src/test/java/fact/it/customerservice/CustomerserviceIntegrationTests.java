package fact.it.customerservice;

import fact.it.customerservice.model.Customer;
import fact.it.customerservice.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerserviceIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer1 = new Customer("Phil", "Collins", "phil.collins@example.com", "1926765147", "Austin", "A35", "65fsg6");
    private Customer customer2 = new Customer("Rick", "Astley", "rick.astley@example.com", "3266607771", "Reliant", "Rialto", "fer563");
    private Customer customer3 = new Customer("Freddie", "Mercury", "freddie.mercury@example.com", "8178613876", "Rolls Royce", "Silver Shadow", "6551er");

    @BeforeEach
    public void beforeEachTest(){
        customerRepository.deleteAll();
        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
    }

    @AfterEach
    public void afterEachTest(){
        customerRepository.deleteAll();
    }

    @Test
    public void whenFindByUuid_thenReturnCustomer() {
        // Customer1
        Customer found1 = customerRepository.findCustomerByUuid(customer1.getUuid());
        assertThat(found1.getUuid())
                .isEqualTo(customer1.getUuid());

        // Customer2
        Customer found2 = customerRepository.findCustomerByUuid(customer2.getUuid());
        assertThat(found2.getUuid())
                .isEqualTo(customer2.getUuid());

        // Customer3
        Customer found3 = customerRepository.findCustomerByUuid(customer3.getUuid());
        assertThat(found3.getUuid())
                .isEqualTo(customer3.getUuid());
    }

    @Test
    public void whenFindByEmail_thenReturnCustomer() {
        // Customer1
        Customer found1 = customerRepository.findCustomerByEmail(customer1.getEmail());
        assertThat(found1.getEmail())
                .isEqualTo(customer1.getEmail());

        // Customer2
        Customer found2 = customerRepository.findCustomerByEmail(customer2.getEmail());
        assertThat(found2.getEmail())
                .isEqualTo(customer2.getEmail());

        // Customer3
        Customer found3 = customerRepository.findCustomerByEmail(customer3.getEmail());
        assertThat(found3.getEmail())
                .isEqualTo(customer3.getEmail());
    }

    @Test
    public void whenFindByPhoneNumber_thenReturnCustomer() {
        // Customer1
        Customer found1 = customerRepository.findCustomerByPhoneNumber(customer1.getPhoneNumber());
        assertThat(found1.getPhoneNumber())
                .isEqualTo(customer1.getPhoneNumber());

        // Customer2
        Customer found2 = customerRepository.findCustomerByPhoneNumber(customer2.getPhoneNumber());
        assertThat(found2.getPhoneNumber())
                .isEqualTo(customer2.getPhoneNumber());

        // Customer3
        Customer found3 = customerRepository.findCustomerByPhoneNumber(customer3.getPhoneNumber());
        assertThat(found3.getPhoneNumber())
                .isEqualTo(customer3.getPhoneNumber());
    }

    @Test
    public void whenFindByLicensePlate_thenReturnCustomer() {
        // Customer1
        Customer found1 = customerRepository.findCustomerByLicensePlate(customer1.getLicensePlate());
        assertThat(found1.getLicensePlate())
                .isEqualTo(customer1.getLicensePlate());

        // Customer2
        Customer found2 = customerRepository.findCustomerByLicensePlate(customer2.getLicensePlate());
        assertThat(found2.getLicensePlate())
                .isEqualTo(customer2.getLicensePlate());

        // Customer3
        Customer found3 = customerRepository.findCustomerByLicensePlate(customer3.getLicensePlate());
        assertThat(found3.getLicensePlate())
                .isEqualTo(customer3.getLicensePlate());
    }
}
