package com.shubham;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
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

    @Id
    private String accountNumber;
    private int customerId;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private double balance;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime openedDate;
    private String pin;

    public Account() {

    }

        public Account(String accountNumber, int customerId, AccountType accountType, double balance) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.accountType = accountType;
        this.balance = balance;
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

    @Override
    public String toString() {
        return "Account Details\n" +
                "-------------------------\n" +
                "Account Number : " + accountNumber + "\n" +
                "Customer ID    : " + customerId + "\n" +
                "Account Type   : " + accountType + "\n" +
                "Balance        : ₹" + balance + "\n" +
                "Status         : " + status + "\n" +
                "Opened Date    : " + openedDate + "\n";
    }

}
