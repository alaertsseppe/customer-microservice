/* package fact.it.customerservice;

import fact.it.customerservice.controller.CustomerController;
import fact.it.customerservice.model.Customer;
import fact.it.customerservice.repository.CustomerRepository;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerserviceUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
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
    public void whenGetCustomers_thenReturnJsonArray() throws Exception {

        List<Customer> allCustomers = Arrays.asList(customer1, customer2, customer3);

        given(customerRepository.findAll()).willReturn(allCustomers);

        mockMvc.perform(get("/api/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void givenUuid_whenGetCustomers_thenReturnJsonCustomer() throws Exception{

        given(customerRepository.findCustomerByUuid(customer1.getUuid())).willReturn(customer1);

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
    public void givenLicensePlate_whenGetCustomerByLicensePlate_thenReturnJsonCustomer() throws Exception {

        given(customerRepository.findCustomerByLicensePlate(customer2.getLicensePlate())).willReturn(customer2);

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
    public void givenPhoneNumber_whenGetCustomerByPhoneNumber_thenReturnJsonCustomer() throws Exception {

        given(customerRepository.findCustomerByEmail(customer3.getLicensePlate())).willReturn(customer3);

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

        given(customerRepository.findCustomerByEmail(customer1.getEmail())).willReturn(customer1);

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

        given(customerRepository.findCustomerByUuid("WrongUuid")).willReturn(null);

        mockMvc.perform(get("/customers/{uuid}", "WrongUuid")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenNoLicensePlate_whenGetCustomersByLicensePlate_thenStatusNotFound() throws Exception {

        given(customerRepository.findCustomerByUuid("NoWayThisIsALicensePlate")).willReturn(null);

        mockMvc.perform(get("/customers/{licensePlate}", "NoWayThisIsALicensePlate")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenNoPhoneNumber_whenGetCustomersByPhoneNumber_thenStatusNotFound() throws Exception {

        given(customerRepository.findCustomerByUuid("NoPhoneNumber")).willReturn(null);

        mockMvc.perform(get("/customers/{phoneNumber}", "NoPhoneNumber")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenNoEmail_whenGetCustomersByEmail_thenStatusNotFound() throws Exception {

        given(customerRepository.findCustomerByUuid("ThisIsNotAnEmail")).willReturn(null);

        mockMvc.perform(get("/customers/{email}", "ThisIsNotAnEmail")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

} */