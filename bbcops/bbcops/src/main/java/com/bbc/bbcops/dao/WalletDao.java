package com.bbc.bbcops.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bbc.bbcops.model.Bill;
import com.bbc.bbcops.model.Customer;
import com.bbc.bbcops.model.Payment;
import com.bbc.bbcops.model.Wallet;

@Repository
public class WalletDao {

	private SessionFactory sessionFactory;

	@Autowired
	public WalletDao(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	public boolean checkWallet(Long customerId, Wallet wallet) {
		try (Session session = sessionFactory.openSession()) {
			Criteria criteria = session.createCriteria(Wallet.class);
			Customer customer = session.get(Customer.class, customerId);
			criteria.add(Restrictions.eq("customer", customer));
			criteria.add(Restrictions.eq("walletName", wallet.getWalletName()));
			List<Wallet> matchWallet = criteria.list();
			return !matchWallet.isEmpty();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String payBillByWallet(Long customerId, Long billId) {
		try (Session session = sessionFactory.openSession()) {
			Customer customer = getCustomerById(session, customerId);
			if (customer == null) {
				return "Customer not found";
			}

			Wallet customerWallet = getCustomerWallet(session, customer);
			if (customerWallet == null) {
				return "Customer does not have a wallet";
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

			double walletBalance = customerWallet.getAmount();

			if (walletBalance < discountedAmount) {
				return "2";
			}

			Payment payment = createPayment(customer, bill, discountedAmount, billAmount, discount);

			bill.setIsPaid(true);
			customerWallet.setAmount(walletBalance - discountedAmount);

			savePaymentAndBill(session, payment, bill);
			updateWallet(session, customerWallet);

			return "1";
		} catch (Exception e) {
			e.printStackTrace();
			return "An error occurred during payment";
		}
	}
	
	private Customer getCustomerById(Session session, Long customerId) {
		return session.get(Customer.class, customerId);
	}

	private Wallet getCustomerWallet(Session session, Customer customer) {
		Criteria walletCriteria = session.createCriteria(Wallet.class);
		walletCriteria.add(Restrictions.eq("customer", customer));
		return (Wallet) walletCriteria.uniqueResult();
	}

	private void updateWallet(Session session, Wallet customerWallet) {
		session.update(customerWallet);
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

}
