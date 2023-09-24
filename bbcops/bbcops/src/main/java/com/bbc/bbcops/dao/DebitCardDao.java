package com.bbc.bbcops.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bbc.bbcops.model.Bill;
import com.bbc.bbcops.model.Customer;
import com.bbc.bbcops.model.DebitCard;
import com.bbc.bbcops.model.Payment;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DebitCardDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public DebitCardDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean isDebitCardValid(Long customerId, DebitCard debitCard) {
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(DebitCard.class);
            Customer customer = session.get(Customer.class, customerId);
            criteria.add(Restrictions.eq("customer", customer));
            criteria.add(Restrictions.eq("cardNumber", debitCard.getCardNumber()));
            criteria.add(Restrictions.eq("cvv", debitCard.getCvv()));
            List<DebitCard> matchingDebitCards = criteria.list();
            return !matchingDebitCards.isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public String payBillByDebitCard(Long customerId, Long billId) {
        try (Session session = sessionFactory.openSession()) {
            Customer customer = getCustomerById(session, customerId);
            if (customer == null) {
                return "Customer not found";
            }

            DebitCard validDebitCard = getValidDebitCard(session, customer);
            if (validDebitCard == null) {
                return "Not a valid debit card";
            }

            Bill bill = getBillById(session, billId, customerId);
            if (bill == null) {
                return "Bill not found";
            }

            if (bill.isPaid()) {
                return "Bill is already paid";
            }

            double billAmount = bill.getBillAmount();
            double discount = 0.05;
            double discountedAmount = billAmount - (billAmount * discount);

            double debitCardBalance = validDebitCard.getBalance();

            if (debitCardBalance < discountedAmount) {
                return "2";
            }

            Payment payment = createPayment(customer, bill, discountedAmount, billAmount, discount);

            bill.setIsPaid(true);
            validDebitCard.setBalance(debitCardBalance - discountedAmount);

            savePaymentAndBill(session, payment, bill);
            updateDebitCard(session, validDebitCard);

            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred during payment";
        }
    }

    private Customer getCustomerById(Session session, Long customerId) {
        return session.get(Customer.class, customerId);
    }

    private DebitCard getValidDebitCard(Session session, Customer customer) {
        Criteria debitCardCriteria = session.createCriteria(DebitCard.class);
        debitCardCriteria.add(Restrictions.eq("customer", customer));
        return (DebitCard) debitCardCriteria.uniqueResult();
    }

    private Bill getBillById(Session session, Long billId, Long customerId) {
        Criteria billCriteria = session.createCriteria(Bill.class);
        billCriteria.add(Restrictions.eq("billId", billId));
        billCriteria.createAlias("customer", "c");
        billCriteria.add(Restrictions.eq("c.customerId", customerId));
        return (Bill) billCriteria.uniqueResult();
    }

    private Payment createPayment(Customer customer, Bill bill, double discountedAmount, double billAmount, double discount) {
        Payment payment = new Payment();
        payment.setAmount(billAmount);
        payment.setDiscountAmount(billAmount * discount);
        payment.setFinalAmount(discountedAmount);
        payment.setPaidCurrency(false);
        payment.setPaidinOnline(true);
        payment.setCustomer(customer);
        payment.setBill(bill);
        payment.setPaymentDate(LocalDate.now());
        return payment;
    }

    private void savePaymentAndBill(Session session, Payment payment, Bill bill) {
        session.save(payment);
        bill.setPayment(payment);
        session.saveOrUpdate(bill);
        session.beginTransaction().commit();
    }

    private void updateDebitCard(Session session, DebitCard validDebitCard) {
        session.update(validDebitCard);
    }
}
