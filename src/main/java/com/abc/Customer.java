package com.abc;

import static java.lang.Math.abs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Customer {

	private String name;
	private List<Account> accounts;

	public Customer(String name) {
		this.name = name;
		this.accounts = new ArrayList<Account>();
	}

	public String getName() {
		return name;
	}

	public Customer openAccount(Account account) {
		accounts.add(account);
		return this;
	}

	public int getNumberOfAccounts() {
		return accounts.size();
	}

	public double totalInterestEarned() {
		double total = 0;
		for (Account a : accounts) {
			switch (a.getAccountType()) {
			case CHECKING:
				total += a.interestEarned();
				break;
			case SAVINGS:
				total += a.interestEarned();
				break;
			case MAXI_SAVINGS:
				total += a.interestEarned();
				break;
			}
		}

		return total;
	}

	public String getStatement() {
		String statement = null;
		statement = "Statement for " + name + "\n";
		double total = 0.0;
		for (Account a : accounts) {
			statement += "\n" + statementForAccount(a) + "\n";
			total += a.sumTransactions();
		}
		statement += "\nTotal In All Accounts " + toDollars(total);
		return statement;
	}

	private String statementForAccount(Account a) {
		String s = "";

		// Translate to pretty account type
		switch (a.getAccountType()) {
		case CHECKING:
			s += "Checking Account\n";
			break;
		case SAVINGS:
			s += "Savings Account\n";
			break;
		case MAXI_SAVINGS:
			s += "Maxi Savings Account\n";
			break;
		}

		// Now total up all the transactions
		double total = 0.0;
		for (Transaction t : a.getTransactions()) {
			s += "  " + (t.amount < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.amount) + "\n";
			total += t.amount;
		}
		s += "Total " + toDollars(total);
		return s;
	}

	private String toDollars(double d) {
		return String.format("$%,.2f", abs(d));
	}

	private boolean isSameAccount(Account account) {
		Iterator<Account> iterator = accounts.listIterator();
		while (iterator.hasNext()) {
			Account account1 = iterator.next();
			if (account1.getAccountId() == account.getAccountId())
				return true;
		}
		return false;
	}

	public boolean interAccountTransfer(double transferAmount, Account sourceAccount, Account destinationAccount) {
		boolean transferSuccessful = false;
		if (sourceAccount == null || destinationAccount == null) {
			throw new NullPointerException("Account not exist");
		}

		if (this.isSameAccount(sourceAccount) && this.isSameAccount(destinationAccount)) {
			if (transferAmount > sourceAccount.sumTransactions())
				throw new IllegalArgumentException("Amount balance is insufficient");
			else {
				sourceAccount.withdraw(transferAmount);
				destinationAccount.deposit(transferAmount);
				transferSuccessful = true;
			}
		}
		return transferSuccessful;

	}
}
