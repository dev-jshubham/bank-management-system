package com.shubham;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

        private static final SessionFactory sessionFactory;

        static {
            try {
            Configuration configuration = new Configuration();
            configuration.configure();
            configuration.addAnnotatedClass(Account.class);
            configuration.addAnnotatedClass(Customer.class);
            configuration.addAnnotatedClass(bankTransaction.class);
            sessionFactory = configuration.buildSessionFactory();
            } catch (HibernateException e) {
                throw new RuntimeException(e);
            }
        }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
