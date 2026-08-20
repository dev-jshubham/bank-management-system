package com.shubham;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;

public class AccountDAO {

    private final SessionFactory sessionFactory;
    private final TransactionDAO transactionDAO = new TransactionDAO();

    public AccountDAO() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(Account.class);
        configuration.addAnnotatedClass(bankTransaction.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public void createAccount(Account account) {
        try (
                Session session = sessionFactory.openSession();
        ) {
            Transaction transaction = session.beginTransaction();
            session.persist(account);
            transaction.commit();
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
                    if (getAccountById(accountNumber) == null) {
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
                    session.merge(account);
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
                if (getAccountById(accountNumber) == null) {
                    System.out.println("Account not found.");
                    transaction.rollback();
                    return;
                }
                if (!account.getPin().equals(pin)) {
                    System.out.println("Invalid PIN.");
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
                session.merge(account);
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

//    public void transfer(String senderACCNO, String receiverACCNO, Double money, String pin){
//        String sql = "UPDATE account SET balance = ? WHERE accountNumber = ? AND pin = ?;";
//        String sql1 = "UPDATE account SET balance = ? WHERE accountNumber = ?;";
//        Connection connection = null;
//        try {
//            connection = DBConnection.getConnection();
//            connection.setAutoCommit(false);
//
//            if(Objects.equals(senderACCNO, receiverACCNO)){
//                System.out.println("Invalid transfer.");
//                connection.rollback();
//                return;
//            }
//
//            Account receiver = getAccountById(receiverACCNO);
//            if(receiver==null){
//                System.out.println("com.shubham.Account not found.");
//                connection.rollback();
//                return;
//            }
//
//            Account sender = getAccountById(senderACCNO);
//            if(sender ==null){
//                System.out.println("com.shubham.Account not found.");
//                connection.rollback();
//                return;
//            }
//
//            if(sender.getBalance()<money){
//                System.out.println("Insufficient Amount.");
//                connection.rollback();
//                return;
//            }
//
//            Double newBalance = sender.getBalance() - money;
//            try(
//                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            ){
//                preparedStatement.setDouble(1,newBalance);
//                preparedStatement.setString(2,senderACCNO);
//                preparedStatement.setString(3,pin);
//                int rows = preparedStatement.executeUpdate();
//                if (rows == 0) {
//                    System.out.println("Invalid sender number or PIN.");
//                    connection.rollback();
//                    return;
//                }
//            }
//            bankTransaction transaction = new bankTransaction(
//                    senderACCNO,
//                    bankTransaction.TransactionType.TRANSFER_OUT,
//                    money,
//                    newBalance
//            );
//            transactionDAO.doTransaction(connection,transaction);
//
//
//            Double newBalance1 = receiver.getBalance() + money;
//            try(
//                    PreparedStatement preparedStatement = connection.prepareStatement(sql1);
//            ){
//                preparedStatement.setDouble(1,newBalance1);
//                preparedStatement.setString(2,receiverACCNO);
//                int rows = preparedStatement.executeUpdate();
//                if (rows == 0) {
//                    System.out.println("Invalid sender number or PIN.");
//                    connection.rollback();
//                    return;
//                }
//            }
//            bankTransaction transaction1 = new bankTransaction(
//                    receiverACCNO,
//                    bankTransaction.TransactionType.TRANSFER_IN,
//                    money,
//                    newBalance1
//            );
//            transactionDAO.doTransaction(connection, transaction1);
//            connection.commit();
//            System.out.println("Transfer Successful.");
//            System.out.println("Updated Balance : ₹"+newBalance);
//        } catch (SQLException e) {
//            if(connection!=null){
//                try {
//                    connection.rollback();
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
//            }
//            e.printStackTrace();
//        }
//        finally {
//            if(connection!=null){
//                try {
//                    connection.setAutoCommit(true);
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public void changePin(String accountNumber ,String pin, String currentPin){
//        String sql = "UPDATE account SET pin = ? WHERE accountNumber = ? AND pin = ?";
//        try (
//                Connection connection = DBConnection.getConnection();
//                PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        )
//        {
//            Account account = getAccountById(accountNumber);
//            if(account==null){
//                System.out.println("com.shubham.Account not found.");
//                return;
//            }
//            preparedStatement.setString(1, pin);
//            preparedStatement.setString(2, accountNumber);
//            preparedStatement.setString(3, currentPin);
//            int rows = preparedStatement.executeUpdate();
//            if(rows > 0){
//                System.out.println("PIN updated successfully.");
//            }else{
//                System.out.println("Invalid current PIN.");
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException();
//        }
//    }

}
