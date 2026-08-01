import java.sql.*;
import java.util.Objects;

public class AccountDAO {
    private final TransactionDAO transactionDAO = new TransactionDAO();
    public void createAccount(Account account){
        String sql = "INSERT INTO account(accountNumber, accountType, balance, status, openedDate, pin,customerId) VALUES(?,?,?,?,?,?,?);";
        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            )
            {
                preparedStatement.setString(1,account.getAccountNumber());
                preparedStatement.setString(2,account.getAccountType().name());
                preparedStatement.setDouble(3,account.getBalance());
                preparedStatement.setString(4,account.getStatus().name());
                preparedStatement.setTimestamp(5, Timestamp.valueOf(account.getOpenedDate()));
                preparedStatement.setString(6,account.getPin());
                preparedStatement.setInt(7,account.getCustomerId());
                int rows = preparedStatement.executeUpdate();
                if(rows>0){
                    System.out.println("Account added of customer id = " +account.getCustomerId());
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public Account getAccountById(String accountNumber){
            String sql = "SELECT * FROM account WHERE accountNumber = ?;";
            try (
                    Connection connection = DBConnection.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
            )
            {
             preparedStatement.setString(1, accountNumber);
             try(
                ResultSet resultSet = preparedStatement.executeQuery();
             )
             {
                 if(resultSet.next()) {
                    Account account1 = new Account(
                            resultSet.getString("accountNumber"),
                            resultSet.getInt("customerId"),
                            Account.AccountType.valueOf(resultSet.getString("accountType").toUpperCase()),
                            resultSet.getDouble("balance"),
                            resultSet.getTimestamp("openedDate").toLocalDateTime(),
                            Account.Status.valueOf(resultSet.getString("status").toUpperCase())
                    );
                     return account1;
                 }
             }
             return null;
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }

        public void deposit(String accountNumber, Double money, String pin) {
            String sql = "UPDATE account SET balance = ? WHERE accountNumber = ? AND pin = ?;";
            Connection connection = null;
            try {
                connection = DBConnection.getConnection();
                connection.setAutoCommit(false);
                Account account = getAccountById(accountNumber);
                if(account==null){
                    System.out.println("Account not found.");
                    connection.rollback();
                    return;
                }
                Double newBalance = money + account.getBalance();
                try(
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
                    preparedStatement.setDouble(1,newBalance);
                    preparedStatement.setString(2, accountNumber);
                    preparedStatement.setString(3, pin);
                    int rows = preparedStatement.executeUpdate();
                    if (rows == 0) {
                        System.out.println("Invalid account number or PIN.");
                        connection.rollback();
                        return;
                    }
                }
                Transaction transaction = new Transaction(
                        accountNumber,
                        Transaction.TransactionType.DEPOSIT,
                        money,
                        newBalance
                );
                transactionDAO.doTransaction(connection,transaction);
                connection.commit();
                System.out.println("Deposit Successful.");
                System.out.println("Updated Balance : ₹"+newBalance);
            } catch (SQLException e) {
                if(connection!=null){
                    try {
                        connection.rollback();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                    e.printStackTrace();
            }
            finally {
                if(connection!=null){
                    try {
                        connection.setAutoCommit(true);
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public void withdraw(String accountNumber, Double money, String pin){
            String sql = "UPDATE account SET balance = ? WHERE accountNumber = ? AND pin = ?;";
            Connection connection = null;
            try {
                connection = DBConnection.getConnection();
                connection.setAutoCommit(false);
                Account account = getAccountById(accountNumber);
                if(account==null){
                    System.out.println("Account not found.");
                    connection.rollback();
                    return;
                }
                if(account.getBalance()<money){
                    System.out.println("Insufficient Amount.");
                    connection.rollback();
                    return;
                }
                Double newBalance = account.getBalance() - money;
                try(
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        ){
                    preparedStatement.setDouble(1,newBalance);
                    preparedStatement.setString(2,accountNumber);
                    preparedStatement.setString(3,pin);
                    int rows = preparedStatement.executeUpdate();
                    if (rows == 0) {
                        System.out.println("Invalid account number or PIN.");
                        connection.rollback();
                        return;
                    }
                }
                Transaction transaction = new Transaction(
                        accountNumber,
                        Transaction.TransactionType.WITHDRAW,
                        money,
                        newBalance
                );
                transactionDAO.doTransaction(connection,transaction);
                connection.commit();
                System.out.println("Withdrawal Successful.");
                System.out.println("Updated Balance : ₹"+newBalance);
            } catch (SQLException e) {
                if(connection!=null){
                    try {
                        connection.rollback();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                e.printStackTrace();
            }
            finally {
                if(connection!=null){
                    try {
                        connection.setAutoCommit(true);
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    public void transfer(String senderACCNO, String receiverACCNO, Double money, String pin){
        String sql = "UPDATE account SET balance = ? WHERE accountNumber = ? AND pin = ?;";
        String sql1 = "UPDATE account SET balance = ? WHERE accountNumber = ?;";
        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            connection.setAutoCommit(false);

            if(Objects.equals(senderACCNO, receiverACCNO)){
                System.out.println("Invalid transfer.");
                connection.rollback();
                return;
            }

            Account receiver = getAccountById(receiverACCNO);
            if(receiver==null){
                System.out.println("Account not found.");
                connection.rollback();
                return;
            }

            Account sender = getAccountById(senderACCNO);
            if(sender ==null){
                System.out.println("Account not found.");
                connection.rollback();
                return;
            }

            if(sender.getBalance()<money){
                System.out.println("Insufficient Amount.");
                connection.rollback();
                return;
            }

            Double newBalance = sender.getBalance() - money;
            try(
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ){
                preparedStatement.setDouble(1,newBalance);
                preparedStatement.setString(2,senderACCNO);
                preparedStatement.setString(3,pin);
                int rows = preparedStatement.executeUpdate();
                if (rows == 0) {
                    System.out.println("Invalid sender number or PIN.");
                    connection.rollback();
                    return;
                }
            }
            Transaction transaction = new Transaction(
                    senderACCNO,
                    Transaction.TransactionType.TRANSFER_OUT,
                    money,
                    newBalance
            );
            transactionDAO.doTransaction(connection,transaction);


            Double newBalance1 = receiver.getBalance() + money;
            try(
                    PreparedStatement preparedStatement = connection.prepareStatement(sql1);
            ){
                preparedStatement.setDouble(1,newBalance1);
                preparedStatement.setString(2,receiverACCNO);
                int rows = preparedStatement.executeUpdate();
                if (rows == 0) {
                    System.out.println("Invalid sender number or PIN.");
                    connection.rollback();
                    return;
                }
            }
            Transaction transaction1 = new Transaction(
                    receiverACCNO,
                    Transaction.TransactionType.TRANSFER_IN,
                    money,
                    newBalance1
            );
            transactionDAO.doTransaction(connection, transaction1);
            connection.commit();
            System.out.println("Transfer Successful.");
            System.out.println("Updated Balance : ₹"+newBalance);
        } catch (SQLException e) {
            if(connection!=null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        }
        finally {
            if(connection!=null){
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
