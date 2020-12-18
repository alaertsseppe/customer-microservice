package fact.it.customerservice;

import fact.it.customerservice.controller.CustomerController;
import fact.it.customerservice.model.Customer;
import fact.it.customerservice.repository.CustomerRepository;
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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerserviceUnitTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerRepository repository;

    @Test
    public void givenCustomers_whenGetCustomers_thenReturnJsonArray()
            throws Exception {

        Customer customer = new Customer("c45700", "Phil", "Collins", "phil.collins@example.com", "1926765147", "Austin", "A35", "65fsg6");

        List<Customer> allCustomers = Arrays.asList(customer);

        given(repository.findAll()).willReturn(allCustomers);

        mvc.perform(get("/api/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is(customer.getFirstName())));
    }

}
