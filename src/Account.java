import java.time.LocalDate;

public class Account {
    private final String accountNumber;
    private final int customerId;
    private final String accountType;
    private double balance;
    private String status;
    private final LocalDate openedDate;
    private String pin;

    public Account(String accountNumber, int customerId, String accountType , int initialAmount ) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.accountType = accountType;
        this.balance = initialAmount;
        this.openedDate = LocalDate.now();
        this.status = "Active";
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getOpenedDate() {
        return openedDate;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

}
