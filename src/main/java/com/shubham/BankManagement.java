package com.shubham;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class BankManagement {
    private final InputValidator input = new InputValidator();
    private final CustomerDAO customerDAO = new CustomerDAO();
    private final AccountDAO accountDAO = new AccountDAO();
    private final TransactionDAO transactionDAO = new TransactionDAO();

    public void start() {
        while (true) {
            System.out.println("\n========== BANK MANAGEMENT SYSTEM ==========");
            System.out.println("1. Register Customer");
            System.out.println("2. View Customer");
            System.out.println("3. Open Account");
            System.out.println("4. View Account");
            System.out.println("5. Deposit Money");
            System.out.println("6. Withdraw Money");
            System.out.println("7. Transfer Money");
            System.out.println("8. View History");
            System.out.println("9. Change PIN");
            System.out.println("10. Exit");
            System.out.print("\nEnter your choice: ");
            int select = input.checkInt();
            switch (select) {
                case 1 -> registerCustomer();
                case 2 -> viewCustomer();
                case 3 -> openAccount();
                case 4 -> viewAccount();
                case 5 -> depositMoney();
                case 6 -> withdrawMoney();
                case 7 -> transferMoney();
                case 8 -> transactionHistory();
//                case 9 -> changePin();
                case 10 -> {
                    System.out.println("Thank you for coming.....");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void registerCustomer() {
        Customer customer = new Customer();
        System.out.println("Enter Name: ");
        customer.setName(input.checkName());
        System.out.println("Enter date of birth (yyyy-mm-dd) :");
        customer.setDob(input.checkDate());
        System.out.print("Enter Gender (MALE/FEMALE/OTHER): ");
        customer.setGender(input.checkGender());
        System.out.println("Enter phone number:");
        customer.setPhoneNumber(input.checkPhoneNumberString());
        System.out.println("Enter email (optional) :");
        customer.setEmail(input.checkEmail());
        System.out.println("Enter address:");
        customer.setAddress(input.checkString());
        System.out.println("Enter type of proof:");
        customer.setIdProofType(input.checkName());
        System.out.println("Enter proof number:");
        customer.setIdProofNumber(input.checkNumberString());
        System.out.println("Enter password");
        customer.setPassword(input.checkPassword());
        customerDAO.addCustomer(customer);
    }

    private void viewCustomer() {
        System.out.println("Enter the ID of customer:");
        Customer fetchedCustomer = customerDAO.getCustomerById(input.checkInt());
        if (fetchedCustomer != null) {
            System.out.println(fetchedCustomer);
        } else {
            System.out.println("Customer not found.");
        }
    }

    private void openAccount() {
        System.out.println("Enter Customer ID:");
        Customer fetchedCustomer = customerDAO.getCustomerById(input.checkInt());
        if (fetchedCustomer == null) {
            System.out.println("Customer not found.");
            return;
        }
        System.out.println("Enter your account number (e.g. ACC0000) :");
        String accountNumber = input.checkAccountNumber();
        System.out.println("Select Account Type:");
        System.out.println("1. Savings");
        System.out.println("2. Current");
        int choice =input. checkInt();
        Account.AccountType type;
        switch (choice) {
            case 1 -> type = Account.AccountType.SAVINGS;
            case 2 -> type = Account.AccountType.CURRENT;
            default -> {
                System.out.println("Invalid account type.");
                return;
            }
        }
        System.out.print("Enter Initial Deposit (minimum : ₹5000) : ");
        double balance = input.checkInitialAmount();
        Account account = new Account(
                accountNumber,
                fetchedCustomer.getCustomerId(),
                type,
                balance
        );
        System.out.println("Enter PIN:");
        account.setPin(input.checkPinString());
        accountDAO.createAccount(account);
    }

    private void viewAccount() {
        System.out.println("Enter your account number (e.g. ACC0000) :");
        Account fetchedAccount = accountDAO.getAccountById(input.checkString());
        if (fetchedAccount != null) {
            System.out.println(fetchedAccount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private void depositMoney() {
        System.out.println("Enter your account number (e.g. ACC0000) :");
        String accountNumber =input.checkAccountNumber();
        System.out.println("Enter amount:");
        Double money = input.checkMoney();
        System.out.println("Enter PIN:");
        String pin = input.checkPinString();
        accountDAO.deposit(accountNumber, money, pin);
    }

    private void withdrawMoney() {
        System.out.println("Enter your account number (e.g. ACC0000) :");
        String accountNumber = input.checkString();
        System.out.println("Enter amount:");
        Double money = input.checkMoney();
        System.out.println("Enter PIN:");
        String pin =input.checkPinString();
        accountDAO.withdraw(accountNumber, money,pin);
    }

    private void transferMoney() {
        System.out.println("Enter Sender Account Number:");
        String senderACCNO = input.checkAccountNumber();
        System.out.println("Enter Receiver Account Number:");
        String receiverACCNO = input.checkAccountNumber();
        System.out.println("Enter amount:");
        Double money = input.checkMoney();
        System.out.println("Enter PIN:");
        String pin = input.checkPinString();
        accountDAO.transfer(senderACCNO, receiverACCNO,money,pin);
    }

    private void transactionHistory() {
        System.out.println("Enter Account Number.");
        String accountNumber = input.checkAccountNumber();
        List<bankTransaction> transactionList = null;
            transactionList = transactionDAO.transactionHistory(accountNumber);
        if (transactionList.isEmpty()) {
            System.out.println("No transaction history found.");
        } else {
            for (bankTransaction transaction : transactionList) {
                System.out.println(transaction);
            }
        }
    }

//    private void changePin() {
//        System.out.println("Enter account Number: ");
//        String accountNumber = input.checkAccountNumber();
//        System.out.println("Enter current pin: ");
//        String currentPin = input.checkPinString();
//        System.out.println("Enter new pin: ");
//        String pin = input.checkPinString();
//        if (Objects.equals(currentPin, pin)) {
//                System.out.println("You entered the same PIN.");
//                return;
//            }
//        accountDAO.changePin(accountNumber,pin,currentPin);
//    }

}

