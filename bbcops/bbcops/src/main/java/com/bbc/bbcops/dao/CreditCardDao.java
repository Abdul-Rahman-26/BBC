package com.bbc.bbcops.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bbc.bbcops.model.Bill;
import com.bbc.bbcops.model.CreditCard;
import com.bbc.bbcops.model.Customer;
import com.bbc.bbcops.model.Payment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class CreditCardDao {

	private final SessionFactory sessionFactory;

	@Autowired
	public CreditCardDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	 public boolean isCreditCardValid(Long customerId, CreditCard creditCard) {
	        try (Session session = sessionFactory.openSession()) {
	            Criteria criteria = session.createCriteria(CreditCard.class);
	            Customer customer = session.get(Customer.class, customerId);
	            criteria.add(Restrictions.eq("customer", customer));
	            criteria.add(Restrictions.eq("cardNumber", creditCard.getCardNumber()));
	            criteria.add(Restrictions.eq("cvv", creditCard.getCvv()));
	            LocalDate currentDate = LocalDate.now();
	            int currentYear = currentDate.getYear();
	            int currentMonth = currentDate.getMonthValue();
	            LocalDate expirationDate = creditCard.getExpiration();
	            int expirationYear = expirationDate.getYear();
	            int expirationMonth = expirationDate.getMonthValue();
	            criteria.add(Restrictions.eq("expiration", expirationDate));
	            if (expirationYear > currentYear || (expirationYear == currentYear && expirationMonth >= currentMonth)) {
	                List<CreditCard> matchingCreditCards = criteria.list();
	                return !matchingCreditCards.isEmpty();
	            } else {
	                return false;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	public String payBillByCreditCard(Long customerId, Long billId) {
		try (Session session = sessionFactory.openSession()) {
			Customer customer = getCustomerById(session, customerId);
			if (customer == null) {
				return "Customer not found";
			}

			CreditCard validCreditCard = getValidCreditCard(session, customer);
			if (validCreditCard == null) {
				return "Not a valid card";
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

			double creditCardBalance = validCreditCard.getAmount();

			if (creditCardBalance < discountedAmount) {
				return "2";
			}

			Payment payment = createPayment(customer, bill, discountedAmount, billAmount, discount);

			bill.setIsPaid(true);
			validCreditCard.setAmount(creditCardBalance - discountedAmount);

			savePaymentAndBill(session, payment, bill);
			updateCreditCard(session, validCreditCard);

			return "1";
		} catch (Exception e) {
			e.printStackTrace();
			return "An error occurred during payment";
		}
	}

	private Customer getCustomerById(Session session, Long customerId) {
		return session.get(Customer.class, customerId);
	}

	private CreditCard getValidCreditCard(Session session, Customer customer) {
		Criteria creditcard = session.createCriteria(CreditCard.class);
		creditcard.add(Restrictions.eq("customer", customer));
		return (CreditCard) creditcard.uniqueResult();
	}

	private Bill getBillById(Session session, Long billId, Long customerId) {
		Criteria billCriteria = session.createCriteria(Bill.class);
		billCriteria.add(Restrictions.eq("billId", billId));
		billCriteria.createAlias("customer", "c");
		billCriteria.add(Restrictions.eq("c.customerId", customerId));
		return (Bill) billCriteria.uniqueResult();
	}

	private Payment createPayment(Customer customer, Bill bill, double discountedAmount, double billAmount,
			double discount) {
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

	private void updateCreditCard(Session session, CreditCard validCreditCard) {
		session.update(validCreditCard);
	}
	
}
