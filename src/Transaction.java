import java.time.LocalDateTime;

public class Transaction {
    public enum TransactionType{
        DEPOSIT,
        WITHDRAW,
        TRANSFER_IN,
        TRANSFER_OUT
    }
    private final int transactionId;
    private final String accountNumber;
    private final TransactionType transactionType;
    private final double amount;
    private final double balanceAfter;
    private final LocalDateTime transactionDate;

    public Transaction(int transactionId, String accountNumber, TransactionType transactionType, double amount, double balanceAfter, LocalDateTime transactionDate) {
        this.transactionId = 0;
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.transactionDate = LocalDateTime.now();
    }

    public int getTransactionId() {
        return transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

}
