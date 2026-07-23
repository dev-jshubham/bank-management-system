import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

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
                    System.out.println("Account added");
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
