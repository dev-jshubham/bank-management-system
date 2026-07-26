import java.time.LocalDate;
import java.util.Scanner;

public class BankManagement {
    private final Scanner sc = new Scanner(System.in);
    private final CustomerDAO customerDAO = new CustomerDAO();
    private final AccountDAO  accountDAO = new AccountDAO();

    public String checkString() {
        while (true) {
            String input = sc.nextLine();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("This field cannot be empty.");
        }
    }

    public int checkInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input! Please try again.");
            }
        }
    }

    public String checkNumberString() {
        while (true) {
            String input = sc.nextLine().trim();
            if (input.matches("\\d+")) {
                return input;
            } else {
                System.out.println("Invalid Input! Please try again.");
            }
        }
    }

    public String checkEmail() {
        while (true) {
            String input = sc.nextLine().trim();
            if (input.endsWith("@gmail.com")) {
                return input;
            }
            if (input.isEmpty()) {
                return null;
            }
            else {
                System.out.println("Invalid email! Enter a Gmail address or press Enter to skip.");
            }
        }
    }

    public String checkPhoneNumberString() {
        while (true) {
            System.out.print("+91 ");
            String input = sc.nextLine().trim();
            if (input.matches("\\d+") && input.length() ==10) {
                return input;
            } else {
                System.out.println("Invalid Input! Please try again.");
            }
        }
    }

    public String checkPinString() {
        while (true) {
            String input = sc.nextLine().trim();
            if (input.matches("\\d+") && input.length() ==4) {
                return input;
            } else {
                System.out.println("Invalid Input! Please try again.");
            }
        }
    }

    public LocalDate checkDate(){
        while(true) {
            try {
                return LocalDate.parse(checkString());
            } catch (Exception e ) {
                System.out.println("Invalid Input! Please try again.");
            }
        }
    }

    public String checkName() {
        while (true) {
            String name = sc.nextLine();
            if (name.matches("[a-zA-Z ]+")) {
                return name;
            } else {
                System.out.println("Invalid Input! Please try again.");
            }
        }
    }

    public double checkDouble() {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input! Please enter a valid amount.");
            }
        }
    }

    public Customer.Gender checkGender() {
        while (true) {
            try{
                return Customer.Gender.valueOf(checkName().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Gender! Enter MALE, FEMALE or OTHER.");
            }
        }
    }

    public void start() {
        while (true) {
            System.out.println("\n========== BANK MANAGEMENT SYSTEM ==========");
            System.out.println("1. Register Customer");
            System.out.println("2. View Customer");
            System.out.println("3. Open Account");
            System.out.println("4. View Account");
            System.out.println("5. Exit");

            System.out.print("\nEnter your choice: ");
            int select = checkInt();
            switch (select) {
                case 1 -> registerCustomer();
                case 2 -> viewCustomer();
                case 3 -> openAccount();
                case 4 -> viewAccount();
                case 5 -> {
                    System.out.println("Thank you for coming.....");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

        private void registerCustomer(){
            Customer customer = new Customer();
            System.out.println("Enter Name: ");
                customer.setName(checkName());
            System.out.println("Enter date of birth (yyyy-mm-dd) :");
                customer.setDob(checkDate());
            System.out.print("Enter Gender (MALE/FEMALE/OTHER): ");
                customer.setGender(checkGender());
            System.out.println("Enter phone number:");
                customer.setPhoneNumber(checkPhoneNumberString());
            System.out.println("Enter email (optional) :");
                customer.setEmail(checkEmail());
            System.out.println("Enter address:");
                customer.setAddress(checkString());
            System.out.println("Enter type of proof:");
                customer.setIdProofType(checkName());
            System.out.println("Enter proof number:");
            customer.setIdProofNumber(checkNumberString());
            System.out.println("Enter password");
                customer.setPassword(checkString());
            customerDAO.addCustomer(customer);
        }

        public void viewCustomer(){
            System.out.println("Enter the ID of customer:");
            Customer fetchedCustomer = customerDAO.getCustomerById(checkInt());
            if(fetchedCustomer!=null){
                System.out.println(fetchedCustomer);
            }
            else{
                System.out.println("Customer not found.");
            }
        }

        public void openAccount(){
            System.out.println("Enter Customer ID:");
            Customer fetchedCustomer = customerDAO.getCustomerById(checkInt());
            if(fetchedCustomer==null){
                System.out.println("Customer not found.");
                return;
            }
            System.out.println("Enter account number:");
            String accountNumber = checkString();
            System.out.println("Select Account Type:");
            System.out.println("1. Savings");
            System.out.println("2. Current");
            int choice = checkInt();
            Account.AccountType type;
            switch(choice){
                case 1-> type =  Account.AccountType.SAVINGS;
                case 2-> type =  Account.AccountType.CURRENT;
                default -> {
                    System.out.println("Invalid account type.");
                    return;
                }
            }
            System.out.print("Enter Initial Deposit: ");
            double balance = checkDouble();
            Account account = new Account(
                    accountNumber,
                    fetchedCustomer.getCustomerId(),
                    type,
                    balance
            );
            System.out.println("Enter PIN:");
            account.setPin(checkPinString());
            accountDAO.createAccount(account);
        }

        public void viewAccount(){
            System.out.println("Enter your account number:");
            Account fetchedCustomer = accountDAO.getAccountById(checkString());
            if(fetchedCustomer!=null){
                System.out.println(fetchedCustomer);
            }
            else{
                System.out.println("Account not found.");
            }
        }
}

