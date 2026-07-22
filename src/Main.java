import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.setName("Test User");
        customer.setDob(LocalDate.of(2000, 1, 1));
        customer.setGender(Customer.Gender.MALE);
        customer.setPhoneNumber("9876543210");
        customer.setEmail("test@example.com");
        customer.setAddress("Some Address");
        customer.setIdProofType("Aadhar");
        customer.setIdProofNumber("123456789012");
        customer.setPassword("test123");

        CustomerDAO dao = new CustomerDAO();
//        dao.addCustomer(customer);
        Customer fetchedCustomer = dao.getCustomerById(1);
        if(fetchedCustomer!=null){
            System.out.println("Customer ID: " + fetchedCustomer.getCustomerId());
            System.out.println("Name: " + fetchedCustomer.getName());
            System.out.println("DOB: " + fetchedCustomer.getDob());
            System.out.println("Gender: " + fetchedCustomer.getGender());
            System.out.println("Phone: " + fetchedCustomer.getPhoneNumber());
            System.out.println("Email: " + fetchedCustomer.getEmail());
            System.out.println("Address: " + fetchedCustomer.getAddress());
            System.out.println("ID Proof: " +fetchedCustomer.getIdProofType() +" - "+fetchedCustomer.getIdProofNumber());
            System.out.println("Registered: " + fetchedCustomer.getRegistrationDate());
            System.out.println("Active: " + fetchedCustomer.isActive());
        }
        else{
            System.out.println("Customer not found.");
        }
    }
}