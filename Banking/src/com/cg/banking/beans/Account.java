package com.cg.banking.beans;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.engine.spi.CascadeStyle;
@Entity
/*@NamedQueries({
	@NamedQuery(name="getAllAccount", query="select a from Account a"),
	@NamedQuery(name="getFewAssociate", query="select a from Account a where a.accountNo=:accountNo")
})
*/
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator="accountid")
	@SequenceGenerator(name="accountid", sequenceName="sequence1",initialValue=1001,allocationSize=0)
	private long accountNo;
	private int pinNumber;
	private String accountType, status;
	private float accountBalance;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="account")
	private List<Transaction> transaction;
	
	 
	public Account(int pinNumber, String accountType, float accountBalance) {
		super();
		this.pinNumber = pinNumber;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
		this.transaction = transaction;
	}

	public Account() {
		super();
	}
 
	public Account(String accountType, float accountBalance) {
		super();
		this.accountType = accountType;
		this.accountBalance =accountBalance;
	}
 
	public Account(int pinNumber, String accountType, String status,
			float accountBalance, long accountNo, List<Transaction> transaction) {
		super();
		this.pinNumber = pinNumber;
		this.accountType = accountType;
		this.status = status;
		this.accountBalance = accountBalance;
		this.accountNo = accountNo;
		this.transaction = transaction;
	}
 
	public Account(long accountNo, String accountType, String status,
			float accountBalance, int pinNumber, List<Transaction> transaction) {
		super();
		this.accountNo = accountNo;
		this.accountType = accountType;
		this.status = status;
		this.accountBalance = accountBalance;
		this.pinNumber = pinNumber;	
		this.transaction = transaction;
	}
 
	public Account(String accountType, String status, float accountBalance,
			int pinNumber, List<Transaction> transaction) {
		super();
		this.accountType = accountType;
		this.status = status;
		this.accountBalance = accountBalance;
		this.pinNumber = pinNumber;	
		this.transaction = transaction;
	}
 
	public List<Transaction> getTransaction() {
		return transaction;
	}
 
	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}
 
	public int getPinNumber() {
		return pinNumber;
	}
 
	public void setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
	}
 
	public String getAccountType() {
		return accountType;
	}
 
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
 
	public String getStatus() {
		return status;
	}
 
	public void setStatus(String status) {
		this.status = status;
	}
 
	public float getAccountBalance() {
		return accountBalance;
	}
 
	public void setAccountBalance(float accountBalance) {
		this.accountBalance = accountBalance;
	}
 
	public long getAccountNo() {
		return accountNo;
	}
 
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(accountBalance);
		result = prime * result + (int) (accountNo ^ (accountNo >>> 32));
		result = prime * result
				+ ((accountType == null) ? 0 : accountType.hashCode());
		result = prime * result + pinNumber;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((transaction == null) ? 0 : transaction.hashCode());
		return result;
	}
 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (Float.floatToIntBits(accountBalance) != Float
				.floatToIntBits(other.accountBalance))
			return false;
		if (accountNo != other.accountNo)
			return false;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		if (pinNumber != other.pinNumber)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (transaction == null) {
			if (other.transaction != null)
				return false;
		} else if (!transaction.equals(other.transaction))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Account [pinNumber=" + pinNumber + ", accountType="
				+ accountType + ", status=" + status + ", accountBalance="
				+ accountBalance + ", accountNo=" + accountNo
				+ ", transaction=" + transaction + "]";
	}
}