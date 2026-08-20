package com.shubham;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.List;

public class TransactionDAO {

    private final SessionFactory sessionFactory;

    public TransactionDAO() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(Account.class);
        configuration.addAnnotatedClass(bankTransaction.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public void doTransaction(bankTransaction transaction, Session session) throws  SQLException{
        session.persist(transaction);
        System.out.println("Transaction recorded successfully.");
    }

    public List<bankTransaction> transactionHistory(String accountNumber){
        try (
                Session session = sessionFactory.openSession();
        ) {
            String hql = "FROM bankTransaction WHERE accountNumber = :accNo ORDER BY transactionDate DESC";
            Query<bankTransaction>  query = session.createQuery(hql, bankTransaction.class);
            query.setParameter("accNo",accountNumber);
            return query.list();
    }
}

}
