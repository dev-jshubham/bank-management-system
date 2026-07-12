import java.time.LocalDate;

public class Transaction {
    private final int transactionId;
    private final String accountNumber;
    private final String transactionType;
    private final double amount;
    private final double balanceAfter;
    private final LocalDate transactionDate;

    public Transaction(int transactionId, String accountNumber, String transactionType, double amount, double balanceAfter, LocalDate transactionDate) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.transactionDate = transactionDate;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

}
