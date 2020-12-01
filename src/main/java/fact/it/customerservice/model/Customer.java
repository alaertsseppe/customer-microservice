package fact.it.customerservice.model;

import javax.persistence.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String carBrand;
    private String carModel;

    @Column(unique = true)
    private String licencePlate;


}
