import java.time.LocalDateTime;

public class Account {

    public enum AccountType{
        SAVINGS,
        CURRENT
    }

    public enum Status{
        ACTIVE,
        BLOCKED,
        CLOSED
    }

    private final String accountNumber;
    private final int customerId;
    private final AccountType accountType;
    private double balance;
    private Status status;
    private final LocalDateTime openedDate;
    private String pin;

    public Account(String accountNumber, int customerId, AccountType accountType , double initialAmount ) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.accountType = accountType;
        this.balance = initialAmount;
        this.openedDate = LocalDateTime.now();
        this.status = Status.ACTIVE;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getOpenedDate() {
        return openedDate;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

}
