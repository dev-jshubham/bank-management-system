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

    public Account(String accountNumber, int customerId, AccountType accountType, double balance) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.accountType = accountType;
        this.balance = balance;
        this.openedDate = LocalDateTime.now();
        this.status = Status.ACTIVE;
    }

    public Account(String accountNumber, int customerId, AccountType accountType , double balance, LocalDateTime openedDate, Status status) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.accountType = accountType;
        this.balance = balance;
        this.openedDate = openedDate;
        this.status = status;
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

    @Override
    public String toString() {
        return "Account Details\n" +
                "-------------------------------------------------\n" +
                "Account Number : " + accountNumber + "\n" +
                "Customer ID    : " + customerId + "\n" +
                "Account Type   : " + accountType + "\n" +
                "Balance        : ₹" + balance + "\n" +
                "Status         : " + status + "\n" +
                "Opened Date    : " + openedDate + "\n";
    }

}
