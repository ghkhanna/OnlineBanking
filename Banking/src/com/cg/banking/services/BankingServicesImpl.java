package com.cg.banking.services;

import java.util.ArrayList;
import java.util.List;

import com.cg.banking.beans.Account;
import com.cg.banking.beans.Transaction;
import com.cg.banking.daoservices.AccountDAO;
import com.cg.banking.daoservices.AccountDAOImpl;
import com.cg.banking.exceptions.AccountBlockedException;
import com.cg.banking.exceptions.AccountNotFoundException;
import com.cg.banking.exceptions.BankingServicesDownException;
import com.cg.banking.exceptions.InsufficientAmountException;
import com.cg.banking.exceptions.InvalidAccountTypeException;
import com.cg.banking.exceptions.InvalidAmountException;
import com.cg.banking.exceptions.InvalidPinNumberException;

public class BankingServicesImpl implements BankingServices{
	private AccountDAO accountDAO = new AccountDAOImpl() ;
	 
	@Override
	public long openAccount(String accountType, float initBalance)
			throws InvalidAmountException, InvalidAccountTypeException, BankingServicesDownException {
		Account account = new Account(accountType, initBalance);
		if(initBalance<0)
			throw new InvalidAmountException();
			account.setStatus("Active");
			List<Transaction>transactions= new ArrayList<>();
			transactions.add(new Transaction(initBalance, "Deposit"));
			account.setTransaction(transactions);
			account=accountDAO.save(account);
			return account.getAccountNo();
	}
 
	@Override
	public float depositAmount(long accountNo, float amount)
			throws AccountNotFoundException, BankingServicesDownException, AccountBlockedException {
		Account account = getAccountDetails(accountNo);
		if(account==null)
			throw new AccountNotFoundException();
		if(account.getStatus().equals("Blocked"))
			throw new AccountBlockedException();
		else{
			account.setAccountBalance(account.getAccountBalance()+amount);
			accountDAO.transactionSave(amount, "Deposit",  account);
			accountDAO.update(account);
		}
		return account.getAccountBalance();
	}
 
	@Override
	public float withdrawAmount(long accountNo, float amount) throws InsufficientAmountException,
	AccountNotFoundException,BankingServicesDownException, AccountBlockedException {
		Account account = getAccountDetails(accountNo);
		if(account==null)
			throw new AccountNotFoundException();
		if(account.getAccountBalance()<amount )
			return -1;
//			throw new InsufficientAmountException();
		if(account.getStatus().equals("Blocked"))
			throw new AccountBlockedException();
		else{
			account.setAccountBalance(account.getAccountBalance()-amount);
			accountDAO.transactionSave(amount, "Withdraw",  account);
			accountDAO.update(account);
		}
		return account.getAccountBalance();
	}
 
	@Override
	public boolean fundTransfer(long accountNoTo, long accountNoFrom, float transferAmount)
			throws InsufficientAmountException, AccountNotFoundException,
			BankingServicesDownException, AccountBlockedException {	
		Account account1 = getAccountDetails(accountNoTo);
		Account account2 = getAccountDetails(accountNoFrom);
		if(account1==null||account2==null)
			throw new AccountNotFoundException();
		if(account2.getAccountBalance()<transferAmount)
			throw new InsufficientAmountException();
		if(account1.getStatus().equals("Blocked")||account2.getStatus().equals("Blocked"))
			throw new AccountBlockedException();
		//if(pinNumber==account2.getPinNumber()){
			account1.setAccountBalance(account1.getAccountBalance()+transferAmount);
			account2.setAccountBalance(account2.getAccountBalance()-transferAmount);
			accountDAO.transactionSave(transferAmount, "Deposit",  account1);
			accountDAO.update(account1);
			accountDAO.transactionSave(transferAmount, "Withdraw",  account2);
			accountDAO.update(account2);
			return true;
	}
 
	@Override
	public Account getAccountDetails(long accountNo)throws AccountNotFoundException, BankingServicesDownException {
		Account account  = accountDAO.findOne(accountNo);
		if(account==null) throw new AccountNotFoundException("Account details not found");
		return account;
	}
 
	@Override
	public List<Account> getAllAccountDetails() throws BankingServicesDownException {
		return accountDAO.findAll();
	}
 
	@Override
	public List<Transaction> getAccountAllTransaction(long accountNo){
		return accountDAO.findAllTransaction(accountNo);
	}
 
	@Override
	public String accountStatus(long accountNo)
			throws BankingServicesDownException, AccountNotFoundException, AccountBlockedException {
		Account account = getAccountDetails(accountNo);
		if(account==null)
			throw new AccountNotFoundException();
		if(account.getStatus().equals("Blocked"))
			throw new AccountBlockedException();
		return account.getStatus();
	}

	@Override
	public Account change(long accountNo,int pinNumber) throws AccountNotFoundException, BankingServicesDownException {
		Account account = getAccountDetails(accountNo);
		account.setPinNumber(pinNumber);
		accountDAO.update(account);
		return account;
	}
}
