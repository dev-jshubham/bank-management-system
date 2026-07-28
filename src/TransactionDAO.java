import java.sql.*;

public class TransactionDAO {

    public void doTransaction(Connection connection, Transaction transaction){
        String sql = "INSERT INTO transaction (accountNumber, transactionType, amount, balanceAfter, transactionDate) VALUES(?,?,?,?,?);";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        )
        {
            preparedStatement.setString(1,transaction.getAccountNumber());
            preparedStatement.setString(2,transaction.getTransactionType().name());
            preparedStatement.setDouble(3,transaction.getAmount());
            preparedStatement.setDouble(4, transaction.getBalanceAfter());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(transaction.getTransactionDate()));
            int rows = preparedStatement.executeUpdate();
            if(rows>0){
                ResultSet generatedKey = preparedStatement.getGeneratedKeys();
                if(generatedKey.next()){
                    int transactionId = generatedKey.getInt(1);
                    transaction.setTransactionId(transactionId);
                    System.out.println("Transaction done successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
