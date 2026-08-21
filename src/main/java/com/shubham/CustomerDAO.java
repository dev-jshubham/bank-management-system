package com.shubham;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class CustomerDAO {

    private final SessionFactory sessionFactory;

    public CustomerDAO(){
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void addCustomer(Customer customer) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.persist(customer);
                transaction.commit();
                System.out.println("Customer added successfully.");

            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
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
