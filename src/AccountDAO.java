import java.sql.*;

public class AccountDAO {

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
                Double newBalance = money + getAccountById(accountNumber).getBalance();
                try(
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
                    preparedStatement.setDouble(1,newBalance);
                    preparedStatement.setString(2, accountNumber);
                    preparedStatement.setString(3, pin);
                    preparedStatement.executeUpdate();
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
                TransactionDAO transactionDAO = new TransactionDAO();
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
                Double newBalance = getAccountById(accountNumber).getBalance() - money;
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
                TransactionDAO transactionDAO = new TransactionDAO();
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
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
}
