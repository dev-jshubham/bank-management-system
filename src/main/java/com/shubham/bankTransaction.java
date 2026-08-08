package com.shubham;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class bankTransaction {

    public enum TransactionType{
        DEPOSIT,
        WITHDRAW,
        TRANSFER_IN,
        TRANSFER_OUT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;
    private final String accountNumber;
    @Enumerated(EnumType.STRING)
    private final TransactionType transactionType;
    private final double amount;
    private final double balanceAfter;
    private final LocalDateTime transactionDate;

    public bankTransaction(int transactionId,
                           String accountNumber,
                           TransactionType transactionType,
                           double amount,
                           double balanceAfter,
                           LocalDateTime transactionDate) {

        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.transactionDate = transactionDate;
    }

    public bankTransaction(String accountNumber, TransactionType transactionType, double amount, double balanceAfter) {
        this.transactionId = 0;
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.transactionDate = LocalDateTime.now();
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
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

    @Override
    public String toString() {
        return "com.shubham.bankTransaction Details\n" +
                "-------------------------\n" +
                "com.shubham.bankTransaction ID : " + transactionId + "\n" +
                "com.shubham.Account Number : " + accountNumber + "\n" +
                "Type           : " + transactionType + "\n" +
                "Amount         : ₹" + amount + "\n" +
                "Balance After  : ₹" + balanceAfter + "\n" +
                "Date           : " + transactionDate + "\n";
    }

}
