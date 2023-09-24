package com.bbc.bbcops.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bbc.bbcops.model.*;

@Repository
public class CustomerDao {

    private SessionFactory sessionFactory;

    @Autowired
    public CustomerDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public Boolean login(Long customerId) {
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(Customer.class);
            criteria.add(Restrictions.eq("customerId", customerId));
            Customer customer = (Customer) criteria.uniqueResult();
             if (customer != null && customer.getCustomerId() == customerId.longValue()) {
                return true;
            } else {
                return false;
                }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public String getCustomerNameByCustomerId(long customerId) {
        try(Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(Customer.class);
            criteria.add(Restrictions.eq("customerId", customerId));
            Customer customer = (Customer) criteria.uniqueResult();

            if (customer != null) {
                return customer.getCustomerName();
            }
            return null;
            }
    }

    private <T> T checkCustomer(Long customerId, Class<T> entityClass) {
        try (Session session = sessionFactory.openSession()) {
            Customer customer = session.get(Customer.class, customerId);
            Criteria criteria = session.createCriteria(entityClass);
            criteria.add(Restrictions.eq("customer", customer));
            T existingEntity = (T) criteria.uniqueResult();
            return existingEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private <T> String addEntity(Long customerId, T entity) {
        try (Session session = sessionFactory.openSession()) {
            T existingEntity = (T) checkCustomer(customerId, entity.getClass());
            if (existingEntity != null) {
                return "Entity already exists.";
            }

            Customer customer = session.get(Customer.class, customerId);
            if (customer == null) {
                return "Customer with ID " + customerId + " not found.";
            }

            if (entity instanceof CreditCard) {
                CreditCard creditCard = (CreditCard) entity;
                creditCard.setCustomer(customer);
            }

            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
            String entityName = entity.getClass().getSimpleName();
            return entityName + " Added Successfully!!!";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while adding the entity.";
        }
    }

    private <T> String removeEntity(Long customerId, Class<T> entityClass) {
        try (Session session = sessionFactory.openSession()) {
            Customer customer = session.get(Customer.class, customerId);
            Criteria criteria = session.createCriteria(entityClass);
            criteria.add(Restrictions.eq("customer", customer));
            T existingEntity = (T) criteria.uniqueResult();

            if (existingEntity == null) {
                String entityName = entityClass.getSimpleName();
                return "Customer does not have an existing " + entityName + " to remove.";
            }

            session.beginTransaction();
            session.delete(existingEntity);
            session.getTransaction().commit();

            String entityName = entityClass.getSimpleName();
            return entityName + " Removed Successfully!!!";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while removing the entity.";
        }
    }

    private <T> String changeEntity(Long customerId, T updatedEntity) {
        try (Session session = sessionFactory.openSession()) {
            Customer customer = session.get(Customer.class, customerId);

            Criteria criteria = session.createCriteria(updatedEntity.getClass());
            criteria.add(Restrictions.eq("customer", customer));
            T existingEntity = (T) criteria.uniqueResult();

            if (existingEntity == null) {
                String entityName = updatedEntity.getClass().getSimpleName();
                return "Customer does not have an existing " + entityName + " to update.";
            }

            session.beginTransaction();
            session.merge(updatedEntity);
            session.getTransaction().commit();

            String entityName = updatedEntity.getClass().getSimpleName();
            return entityName + " Updated Successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while updating the entity.";
        }
    }

    public String addCreditCard(Long customerId, CreditCard creditCard) {
        return addEntity(customerId, creditCard);
    }

    public String removeCreditCard(Long customerId) {
        return removeEntity(customerId, CreditCard.class);
    }

    public String changeCreditCard(Long customerId, CreditCard updatedCreditCard) {
        return changeEntity(customerId, updatedCreditCard);
    }

    public String addWallet(Long customerId, Wallet wallet) {
        return addEntity(customerId, wallet);
    }

    public String changeWallet(Long customerId, Wallet updatedWallet) {
        return changeEntity(customerId, updatedWallet);
    }

    public String removeWallet(Long customerId) {
        return removeEntity(customerId, Wallet.class);
    }

    public String addUPI(Long customerId, Upi upi) {
        return addEntity(customerId, upi);
    }

    public String removeUPI(Long customerId) {
        return removeEntity(customerId, Upi.class);
    }

    public String changeUPI(Long customerId, Upi updatedUPI) {
        return changeEntity(customerId, updatedUPI);
    }

	
}
