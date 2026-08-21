package com.shubham;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AccountDAO {

    private final TransactionDAO transactionDAO = new TransactionDAO();
    private final SessionFactory sessionFactory;

    public AccountDAO() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void createAccount(Account account) {
        try (
                Session session = sessionFactory.openSession();
        ) {
            Transaction transaction = session.beginTransaction();
            try {
                session.persist(account);
                transaction.commit();
                System.out.println("Account added successfully.");

            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    public Account getAccountById(String accountNumber) {
        try (
                Session session = sessionFactory.openSession();
        ) {
            Account account = session.find(com.shubham.Account.class, accountNumber);
            return account;
        }
    }

        public void deposit(String accountNumber, Double money, String pin) {
            try (
                    Session session = sessionFactory.openSession();
            ) {
                Transaction transaction = session.beginTransaction();
                try {
                    Account account = session.find(Account.class, accountNumber);
                    if (account == null) {
                        System.out.println("Account not found.");
                        transaction.rollback();
                        return;
                    }
                    if (!account.getPin().equals(pin)) {
                        System.out.println("Invalid PIN.");
                        transaction.rollback();
                        return;
                    }
                    Double newBalance = money + account.getBalance();
                    account.setBalance(newBalance);
                    bankTransaction bankTxn = new bankTransaction(
                            accountNumber,
                            bankTransaction.TransactionType.DEPOSIT,
                            money,
                            newBalance
                    );
                    transactionDAO.doTransaction(bankTxn,session);
                    transaction.commit();
                    System.out.println("Deposit Successful.");
                    System.out.println("Updated Balance : ₹" + newBalance);
                }catch (Exception e) {
                    transaction.rollback();
                    e.printStackTrace();
                }
            }
        }

        public void withdraw(String accountNumber, Double money, String pin){
        try(
                Session session = sessionFactory.openSession();
        ) {
                Transaction transaction = session.beginTransaction();
            try {
                Account account = session.find(Account.class, accountNumber);
                if (account == null) {
                    System.out.println("Account not found.");
                    transaction.rollback();
                    return;
                }
                if (!account.getPin().equals(pin)) {
                    System.out.println("Invalid PIN.");
                    transaction.rollback();
                    return;
                }
                if (money > account.getBalance()) {
                    System.out.println("Insufficient balance.");
                    transaction.rollback();
                    return;
                }
                Double newBalance = account.getBalance()-money;
                account.setBalance(newBalance);
                bankTransaction bankTxn = new bankTransaction(
                        accountNumber,
                        bankTransaction.TransactionType.WITHDRAW,
                        money,
                        newBalance
                );
                transactionDAO.doTransaction(bankTxn, session);
                transaction.commit();
                System.out.println("Withdraw Successful.");
                System.out.println("Updated Balance : ₹" + newBalance);
            }
            catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        }
        }

    public void transfer(String senderACCNO, String receiverACCNO, Double money, String pin){
        try (
                Session session = sessionFactory.openSession();
        ) {
            Transaction transaction = session.beginTransaction();
            try {
                Account account = session.find(Account.class, senderACCNO);
                if (account == null) {
                    System.out.println("Sender's Account not found.");
                    transaction.rollback();
                    return;
                }
                Account account1 = session.find(Account.class, receiverACCNO);
                if (account1 == null) {
                    System.out.println("Receiver's Account not found.");
                    transaction.rollback();
                    return;
                }
                if(senderACCNO.equals(receiverACCNO)){
                    System.out.println("Invalid transfer.");
                    transaction.rollback();
                    return;
                    }
                if (!account.getPin().equals(pin)) {
                    System.out.println("Invalid PIN.");
                    transaction.rollback();
                    return;
                }
                if (money > account.getBalance()) {
                    System.out.println("Insufficient balance.");
                    transaction.rollback();
                    return;
                }
                Double newBalance = account.getBalance()-money;
                Double newBalance1 = account1.getBalance()+money;
                account.setBalance(newBalance);
                account1.setBalance(newBalance1);
                bankTransaction bankTxn = new bankTransaction(
                        senderACCNO,
                        bankTransaction.TransactionType.TRANSFER_OUT,
                        money,
                        newBalance
                );
                bankTransaction bankTxn1 = new bankTransaction(
                        receiverACCNO,
                        bankTransaction.TransactionType.TRANSFER_IN,
                        money,
                        newBalance1
                );
                transactionDAO.doTransaction(bankTxn,session);
                transactionDAO.doTransaction(bankTxn1,session);
                transaction.commit();
                System.out.println("Transfer Successful.");
                System.out.println("Updated Balance : ₹" + newBalance);
            }catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    public void changePin(String accountNumber ,String pin, String currentPin){
        try (
                Session session = sessionFactory.openSession();
        ) {
            Transaction transaction = session.beginTransaction();
            Account account = session.find(Account.class,accountNumber);
            if (account == null) {
                System.out.println("Account not found.");
                transaction.rollback();
                return;
            }
            if(!account.getPin().equals(currentPin)){
                System.out.println("Wrong PIN.");
                transaction.rollback();
                return;
            }
            account.setPin(pin);
            transaction.commit();
            System.out.println("PIN changed successfully.");
        }
    }

}
