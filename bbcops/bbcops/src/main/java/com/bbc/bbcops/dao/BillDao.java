package com.bbc.bbcops.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bbc.bbcops.model.Bill;

import java.util.List;

@Repository
public class BillDao {

    private SessionFactory sessionFactory;

    @Autowired
    public BillDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Bill> getBillsByCustomerId(Long customerId) {
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(Bill.class);
            criteria.add(Restrictions.eq("customer.customerId", customerId));
            return criteria.list();
        }
    }

    public List<Bill> getPaidBillsByCustomerId(Long customerId) {
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(Bill.class);
            criteria.add(Restrictions.eq("customer.customerId", customerId));
            criteria.add(Restrictions.eq("isPaid", true));
            return criteria.list();
        }
    }

    public List<Bill> getNotPaidBillsByCustomerId(Long customerId) {
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(Bill.class);
            criteria.add(Restrictions.eq("customer.customerId", customerId));
            criteria.add(Restrictions.eq("isPaid", false));
            return criteria.list();
        }
    }
    
    public Bill getBillByCustomerIdAndBillId(long customerId, long billId) {
        try( Session session = sessionFactory.openSession();) {
            Criteria criteria = session.createCriteria(Bill.class);
            criteria.add(Restrictions.eq("customer.customerId", customerId));
            criteria.add(Restrictions.eq("billId", billId));
            Bill bill = (Bill) criteria.uniqueResult();
            return bill; 
        }
    }
    
}
