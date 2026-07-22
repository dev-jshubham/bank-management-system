import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO customer (name,dob,gender,phoneNumber,email,address,idProofType,idProofNumber,password,registrationDate,active) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setDate(2,java.sql.Date.valueOf(customer.getDob()));
            preparedStatement.setString(3,customer.getGender().name());
            preparedStatement.setString(4,customer.getPhoneNumber());
            preparedStatement.setString(5, customer.getEmail());
            preparedStatement.setString(6, customer.getAddress());
            preparedStatement.setString(7, customer.getIdProofType());
            preparedStatement.setString(8, customer.getIdProofNumber());
            preparedStatement.setString(9, customer.getPassword());
            preparedStatement.setTimestamp(10, java.sql.Timestamp.valueOf(customer.getRegistrationDate()));
            preparedStatement.setBoolean(11, customer.isActive());
            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                ResultSet generatedKey = preparedStatement.getGeneratedKeys();
                if(generatedKey.next()){
                    int newId = generatedKey.getInt(1);
                    customer.setCustomerId(newId);
                    System.out.println("Customer added with ID: " + newId);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Customer getCustomerById(int customerId) {
        String sql = "select * from customer where customerId = ?;";
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                )
        {
            preparedStatement.setInt(1, customerId);
            try(
                    ResultSet resultSet = preparedStatement.executeQuery();
            ) {
                if (resultSet.next()) {
                    Customer customer = new Customer(
                            resultSet.getInt("customerId"),
                            resultSet.getTimestamp("registrationDate").toLocalDateTime(),
                            resultSet.getBoolean("active")
                    );
                    customer.setName(resultSet.getString("name"));
                    customer.setDob(resultSet.getDate("dob").toLocalDate());
                    customer.setGender(Customer.Gender.valueOf(resultSet.getString("gender").toUpperCase()));
                    customer.setPhoneNumber(resultSet.getString("phoneNumber"));
                    customer.setEmail(resultSet.getString("email"));
                    customer.setAddress(resultSet.getString("address"));
                    customer.setIdProofType(resultSet.getString("idProofType"));
                    customer.setIdProofNumber(resultSet.getString("idProofNumber"));
                    customer.setPassword(resultSet.getString("password"));
                    return customer;
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
