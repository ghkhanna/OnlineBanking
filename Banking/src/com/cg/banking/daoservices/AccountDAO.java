package com.cg.banking.daoservices;

import java.util.ArrayList;

import com.cg.banking.beans.Account;
import com.cg.banking.beans.Transaction;

public interface AccountDAO {
	public Account save(Account account);
	public boolean update(Account account);
	public Account findOne(long accountNo);
	public ArrayList<Account> findAll();
	public ArrayList<Transaction> findAllTransaction(long accountNo);
	public Transaction transactionSave(float amount, String transactionType,Account account);
	
}
