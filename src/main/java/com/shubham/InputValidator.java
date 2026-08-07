package com.shubham;

import java.time.LocalDate;
import java.util.Scanner;

public class InputValidator {

    private final Scanner sc = new Scanner(System.in);

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
            } else {
                System.out.println("Invalid email! Enter a Gmail address or press Enter to skip.");
            }
        }
    }

    public String checkAccountNumber() {
        while (true) {
            String input = sc.nextLine().trim();
            if (input.matches("ACC\\d{4}")) {
                return input;
            } else {
                System.out.println("Invalid account number! Enter a valid account number.");
            }
        }
    }

    public String checkPhoneNumberString() {
        while (true) {
            System.out.print("+91 ");
            String input = sc.nextLine().trim();
            if (input.matches("\\d+") && input.length() == 10) {
                return input;
            } else {
                System.out.println("Invalid Input! Please try again.");
            }
        }
    }

    public String checkPinString() {
        while (true) {
            String input = sc.nextLine().trim();
            if (input.matches("\\d+") && input.length() == 4) {
                return input;
            } else {
                System.out.println("Invalid Input! Please try again.");
            }
        }
    }

    public String checkPassword() {
        while (true) {
            String input = sc.nextLine().trim();
            if (input.length() == 8) {
                return input;
            } else {
                System.out.println("Invalid Input! Please try again.");
            }
        }
    }

    public LocalDate checkDate() {
        while (true) {
            try {
                return LocalDate.parse(checkString());
            } catch (Exception e) {
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

    public double checkInitialAmount() {
        while (true) {
            try {
                double initialAmount = Double.parseDouble(sc.nextLine());
                if (initialAmount >= 5000) {
                    return initialAmount;
                }
                System.out.println("Minimum initial deposit is ₹5000.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount! Please enter a valid number.");
            }
        }
    }

    public double checkMoney() {
        while (true) {
            try {
                double amount = Double.parseDouble(sc.nextLine());
                if (amount > 0) {
                    return amount;
                }
                System.out.println("Amount must be greater than 0.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount! Please enter a valid number.");
            }
        }
    }

    public Customer.Gender checkGender() {
        while (true) {
            try {
                return Customer.Gender.valueOf(checkName().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Gender! Enter MALE, FEMALE or OTHER.");
            }
        }
    }

}
