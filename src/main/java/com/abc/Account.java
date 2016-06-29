package com.abc;

import java.util.ArrayList;
import java.util.List;

import com.abc.Transaction.TRANSACTION_TYPE;

public class Account {

	private ACCOUNT_TYPE accountType;

	public static enum ACCOUNT_TYPE {
		CHECKING(0), SAVINGS(1), MAXI_SAVINGS(2);

		private ACCOUNT_TYPE(int value) {
		}
	};

	public static final int CHECKING = 0;
	public static final int SAVINGS = 1;
	public static final int MAXI_SAVINGS = 2;

	public List<Transaction> transactions;

	private String accountId;

	public Account(ACCOUNT_TYPE accountType, String accountId) {
		this.accountType = accountType;
		this.accountId = accountId;
		this.transactions = new ArrayList<Transaction>();
	}

	public void deposit(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero");
		} else {
			transactions.add(new Transaction(amount, TRANSACTION_TYPE.DEPOSIT_MONEY));
		}
	}

	public void withdraw(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero");
		} else {
			transactions.add(new Transaction(-amount, TRANSACTION_TYPE.WITHDRAW_MONEY));
		}
	}

	/*
	 * public double interestEarned() { double amount = sumTransactions();
	 * switch (accountType) { case SAVINGS: if (amount <= 1000) return amount *
	 * 0.001; else return 1 + (amount - 1000) * 0.002; // case SUPER_SAVINGS: //
	 * if (amount <= 4000) // return 20; case MAXI_SAVINGS: if (amount <= 1000)
	 * return amount * 0.02; if (amount <= 2000) return 20 + (amount - 1000) *
	 * 0.05; return 70 + (amount - 2000) * 0.1; default: return amount * 0.001;
	 * } }
	 */
	public double interestEarned() {
		return 0;
	};

	public double sumTransactions() {
		return checkIfTransactionsExist(true);
	}

	private double checkIfTransactionsExist(boolean checkAll) {
		double amount = 0.0;
		for (Transaction t : transactions)
			amount += t.getAmount();
		return amount;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public ACCOUNT_TYPE getAccountType() {
		return accountType;
	}

	public void setAccountType(ACCOUNT_TYPE accountType) {
		this.accountType = accountType;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

}
