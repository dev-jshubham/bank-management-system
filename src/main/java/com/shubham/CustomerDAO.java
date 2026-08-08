package com.shubham;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

//public class CustomerDAO {
//    public void addCustomer(Customer customer) {
//        Configuration configuration = new Configuration();
//        configuration.configure();
//        configuration.addAnnotatedClass(com.shubham.Customer.class);
//        try (
//                SessionFactory sessionFactory = configuration.buildSessionFactory();
//                Session session = sessionFactory.openSession();
//        )
//        {
//            bankTransaction transaction = session.beginTransaction();
//            session.persist(customer);
//            transaction.commit();
//        }
//        }

//    public Customer getCustomerById(int customerId) {
//        String sql = "select * from customer where customerId = ?;";
//        try (
//                Connection connection = DBConnection.getConnection();
//                PreparedStatement preparedStatement = connection.prepareStatement(sql);
//                )
//        {
//            preparedStatement.setInt(1, customerId);
//            try(
//                    ResultSet resultSet = preparedStatement.executeQuery();
//            ) {
//                if (resultSet.next()) {
//                    Customer customer = new Customer(
//                            resultSet.getInt("customerId"),
//                            resultSet.getTimestamp("registrationDate").toLocalDateTime(),
//                            resultSet.getBoolean("active")
//                    );
//                    customer.setName(resultSet.getString("name"));
//                    customer.setDob(resultSet.getDate("dob").toLocalDate());
//                    customer.setGender(Customer.Gender.valueOf(resultSet.getString("gender").toUpperCase()));
//                    customer.setPhoneNumber(resultSet.getString("phoneNumber"));
//                    customer.setEmail(resultSet.getString("email"));
//                    customer.setAddress(resultSet.getString("address"));
//                    customer.setIdProofType(resultSet.getString("idProofType"));
//                    customer.setIdProofNumber(resultSet.getString("idProofNumber"));
//                    customer.setPassword(resultSet.getString("password"));
//                    return customer;
//                }
//            }
//            return null;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
