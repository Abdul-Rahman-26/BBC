package com.bbc.bbcops.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bbc.bbcops.model.Payment;

import java.util.List;

@Repository
public class PaymentDao {

    private final SessionFactory sessionFactory;

    public PaymentDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Payment> getAllPaymentsByCustomerId(Long customerId) {
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(Payment.class);
            
            criteria.createAlias("customer", "c");
            criteria.add(Restrictions.eq("c.customerId", customerId));

            return criteria.list();
        }
    }
}

