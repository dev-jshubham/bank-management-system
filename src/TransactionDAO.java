import java.sql.*;

public class TransactionDAO {
    public void doTransaction(Transaction transaction){
        String sql = "INSERT INTO account(transactionId, accountNumber, transactionType, amount, balanceAfter, transactionDate) VALUES(?,?,?,?,?,?);";
        try (
                Connection connection = DBConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        )
        {
            preparedStatement.setInt(1,transaction.getTransactionId());
            preparedStatement.setString(2,transaction.getAccountNumber());
            preparedStatement.setString(3,transaction.getTransactionType().name());
            preparedStatement.setDouble(4,transaction.getAmount());
            preparedStatement.setDouble(5, transaction.getBalanceAfter());
            preparedStatement.setTimestamp(6, Timestamp.valueOf(transaction.getTransactionDate()));
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
