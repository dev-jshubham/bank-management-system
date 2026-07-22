import java.time.LocalDate;
import java.time.LocalDateTime;

public class Customer {
    public enum Gender{
        MALE, FEMALE, OTHER
    }
    private int customerId;
    private String name;
    private LocalDate dob;
    private Gender gender;
    private String phoneNumber;
    private String email;
    private String address;
    private String idProofType;
    private String idProofNumber;
    private String password;
    private final LocalDateTime registrationDate;
    private boolean active;

    public Customer() {
        this.registrationDate = LocalDateTime.now();
        this.active = true;
    }

    public Customer(int customerId, LocalDateTime registrationDate, boolean active) {
        this.customerId = customerId;
        this.registrationDate = registrationDate;
        this.active = active;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdProofNumber() {
        return idProofNumber;
    }

    public void setIdProofNumber(String idProofNumber) {
        this.idProofNumber = idProofNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdProofType() {
        return idProofType;
    }

    public void setIdProofType(String idProofType) {
        this.idProofType = idProofType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


}
