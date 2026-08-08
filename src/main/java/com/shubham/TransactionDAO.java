//package com.shubham;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class TransactionDAO {
//
//    public void doTransaction(Connection connection, bankTransaction transaction) throws  SQLException{
//        String sql = "INSERT INTO transaction (accountNumber, transactionType, amount, balanceAfter, transactionDate) VALUES(?,?,?,?,?);";
//        try (
//                PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
//        )
//        {
//            preparedStatement.setString(1,transaction.getAccountNumber());
//            preparedStatement.setString(2,transaction.getTransactionType().name());
//            preparedStatement.setDouble(3,transaction.getAmount());
//            preparedStatement.setDouble(4, transaction.getBalanceAfter());
//            preparedStatement.setTimestamp(5, Timestamp.valueOf(transaction.getTransactionDate()));
//            int rows = preparedStatement.executeUpdate();
//            if(rows>0){
//                try(
//                        ResultSet generatedKey = preparedStatement.getGeneratedKeys();
//                ) {
//                    if (generatedKey.next()) {
//                        int transactionId = generatedKey.getInt(1);
//                        transaction.setTransactionId(transactionId);
//                        System.out.println("com.shubham.bankTransaction done successfully.");
//                    }
//                }
//            }
//        }
//    }
//
//    public List<bankTransaction> transactionHistory(String accountNumber)  throws  SQLException{
//        List<bankTransaction> transactionList = new ArrayList<>();
//        String sql = "SELECT * FROM transaction WHERE accountNumber = ? ORDER BY transactionDate DESC;";
//        try (
//                Connection connection = DBConnection.getConnection();
//                PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        )
//        {
//            preparedStatement.setString(1,accountNumber);
//            try(
//                    ResultSet resultSet = preparedStatement.executeQuery();
//            ){
//                while(resultSet.next()){
//                    bankTransaction transaction = new bankTransaction(
//                            resultSet.getInt("transactionId"),
//                            resultSet.getString("accountNumber"),
//                            bankTransaction.TransactionType.valueOf(resultSet.getString("transactionType")),
//                            resultSet.getDouble("amount"),
//                            resultSet.getDouble("balanceAfter"),
//                            resultSet.getTimestamp("transactionDate").toLocalDateTime()
//                    );
//                    transactionList.add(transaction);
//                }
//                return transactionList;
//            }
//        }
//    }
//
//}
