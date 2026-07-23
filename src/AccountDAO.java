import java.sql.*;

public class AccountDAO {

    public void createAccount(Account account){
        String sql = "INSERT INTO account(accountNumber, accountType, balance, status, openedDate, pin,customerId) VALUES(?,?,?,?,?,?,?);";
        try (
            Connection connection = DBConnection.getConnection();PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
                    Connection connection = DBConnection.getConnection();PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
}
