package fact.it.customerservice;

import fact.it.customerservice.model.Customer;
import fact.it.customerservice.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    public void whenGetCustomers_thenReturnJsonCustomer() throws Exception {
        mockMvc.perform(get("/customers"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void givenCustomer_whenGetCustomerByUuid_thenReturnJsonCustomer() throws Exception {
        mockMvc.perform(get("/customers/{uuid}", customer1.getUuid()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.id", is(customer1.getId())))
                .andExpect(jsonPath("$.uuid", is(customer1.getUuid())))
                .andExpect(jsonPath("$.licensePlate", is(customer1.getLicensePlate())))
                .andExpect(jsonPath("$.firstName", is(customer1.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(customer1.getLastName())))
                .andExpect(jsonPath("$.email", is(customer1.getEmail())))
                .andExpect(jsonPath("$.phoneNumber", is(customer1.getPhoneNumber())))
                .andExpect(jsonPath("$.carBrand", is(customer1.getCarBrand())))
                .andExpect(jsonPath("$.carModel", is(customer1.getCarModel())));
    }

    @Test
    public void givenCustomer_whenGetCustomerByLicensePlate_thenReturnJsonCustomer() throws Exception {
        mockMvc.perform(get("/customers/{licensePlate}", customer2.getLicensePlate()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.id", is(customer2.getId())))
                .andExpect(jsonPath("$.uuid", is(customer2.getUuid())))
                .andExpect(jsonPath("$.licensePlate", is(customer2.getLicensePlate())))
                .andExpect(jsonPath("$.firstName", is(customer2.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(customer2.getLastName())))
                .andExpect(jsonPath("$.email", is(customer2.getEmail())))
                .andExpect(jsonPath("$.phoneNumber", is(customer2.getPhoneNumber())))
                .andExpect(jsonPath("$.carBrand", is(customer2.getCarBrand())))
                .andExpect(jsonPath("$.carModel", is(customer2.getCarModel())));
    }

    @Test
    public void givenCustomer_whenGetCustomerByPhoneNumber_thenReturnJsonCustomer() throws Exception {
        mockMvc.perform(get("/customers/{phoneNumber}", customer3.getPhoneNumber()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.id", is(customer3.getId())))
                .andExpect(jsonPath("$.uuid", is(customer3.getUuid())))
                .andExpect(jsonPath("$.licensePlate", is(customer3.getLicensePlate())))
                .andExpect(jsonPath("$.firstName", is(customer3.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(customer3.getLastName())))
                .andExpect(jsonPath("$.email", is(customer3.getEmail())))
                .andExpect(jsonPath("$.phoneNumber", is(customer3.getPhoneNumber())))
                .andExpect(jsonPath("$.carBrand", is(customer3.getCarBrand())))
                .andExpect(jsonPath("$.carModel", is(customer3.getCarModel())));
    }

    @Test
    public void givenCustomer_whenGetCustomerByEmail_thenReturnJsonCustomer() throws Exception {
        mockMvc.perform(get("/customers/{email}", customer1.getEmail()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.id", is(customer1.getId())))
                .andExpect(jsonPath("$.uuid", is(customer1.getUuid())))
                .andExpect(jsonPath("$.licensePlate", is(customer1.getLicensePlate())))
                .andExpect(jsonPath("$.firstName", is(customer1.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(customer1.getLastName())))
                .andExpect(jsonPath("$.email", is(customer1.getEmail())))
                .andExpect(jsonPath("$.phoneNumber", is(customer1.getPhoneNumber())))
                .andExpect(jsonPath("$.carBrand", is(customer1.getCarBrand())))
                .andExpect(jsonPath("$.carModel", is(customer1.getCarModel())));
    }

    @Test
    public void givenNoUuid_whenGetCustomersByUuid_thenStatusNotFound() throws Exception {
        mockMvc.perform(get("/customers/{uuid}", "WrongUuid")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenNoLicensePlate_whenGetCustomersByLicensePlate_thenStatusNotFound() throws Exception {
        mockMvc.perform(get("/customers/{licensePlate}", "NoWayThisIsALicensePlate")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenNoPhoneNumber_whenGetCustomersByPhoneNumber_thenStatusNotFound() throws Exception {
        mockMvc.perform(get("/customers/{phoneNumber}", "NoPhoneNumber")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenNoEmail_whenGetCustomersByEmail_thenStatusNotFound() throws Exception {
        mockMvc.perform(get("/customers/{email}", "ThisIsNotAnEmail")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}