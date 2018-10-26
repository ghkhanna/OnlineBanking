package com.cg.banking.daoservices;

import java.util.ArrayList;
 


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
 


import com.cg.banking.beans.Account;
import com.cg.banking.beans.Transaction;
import com.cg.banking.util.EntityManagerFactoryProvider;
 
public class AccountDAOImpl implements AccountDAO {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA-PU");
	//EntityManagerFactoryProvider.getEntitymanagerFactory();
	//EntityManager entityManager = factory.createEntityManager();
 
	@Override
	public Account save(Account account) {
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(account);
		entityManager.getTransaction().commit();
		entityManager.close();
		return account;
	}
	
	@Override
	public Transaction transactionSave(float amount, String transactionType,Account account) {
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		Transaction transaction = new Transaction(amount, transactionType,account);
		entityManager.persist(transaction);
		entityManager.getTransaction().commit();
		entityManager.close();
		return transaction;
	}
 
	@Override
	public boolean update(Account account) {
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(account);
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;
	}
	@Override
	public Account findOne(long accountNo) {
		EntityManager entityManager = factory.createEntityManager();
		return entityManager.find(Account.class, accountNo);
	}
 
	@Override
	public ArrayList<Account> findAll() {
		EntityManager entityManager = factory.createEntityManager();
		Query query = entityManager.createQuery("from Account a");
		@SuppressWarnings("unchecked")
		ArrayList<Account> list = (ArrayList<Account>)query.getResultList();
		return list;
	}
 
	@Override
	public ArrayList<Transaction> findAllTransaction(long accountNo) {
		EntityManager entityManager = factory.createEntityManager();
		Query query = entityManager.createNamedQuery("getTransactions");
		query.setParameter("accountNo", accountNo);
		@SuppressWarnings("unchecked")
		ArrayList<Transaction> list = (ArrayList<Transaction>)query.getResultList();
		return list;
	}
}