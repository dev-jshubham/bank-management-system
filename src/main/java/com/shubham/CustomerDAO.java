package com.shubham;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class CustomerDAO {

    private final SessionFactory sessionFactory;
    public CustomerDAO(){
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(Customer.class);
        sessionFactory = configuration.buildSessionFactory();

    }

    public void addCustomer(Customer customer) {
        try (
                Session session = sessionFactory.openSession();
        )
        {
            Transaction transaction = session.beginTransaction();
            session.persist(customer);
            transaction.commit();
        }
        }

    public Customer getCustomerById(int customerId) {
        try (
                Session session = sessionFactory.openSession();
        )
        {
            Customer customer = session.find(com.shubham.Customer.class,customerId);
            return customer;
        }
    }

}
