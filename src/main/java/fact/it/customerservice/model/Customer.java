package fact.it.customerservice.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String uuid = generateCustomerUuid();

    @Column(unique = true)
    private String licensePlate;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String carBrand;
    private String carModel;


    // Constructor
    public Customer() {
    }

    public Customer(String firstName, String lastName, String email, String phoneNumber, String carBrand, String carModel, String licensePlate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.licensePlate = licensePlate;
    }

    private String generateCustomerUuid(){
        return "c" + UUID.randomUUID().toString().replace("-", "");
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid(){ return uuid; }

    public void setUuid(){ this.uuid = uuid; }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
