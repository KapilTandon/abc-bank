package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.abc.Account.ACCOUNT_TYPE;

public class BankTest {
	private static final double DOUBLE_DELTA = 1e-15;

	@Test
	public void customerSummary() {
		Bank bank = new Bank();
		Customer john = new Customer("John");
		john.openAccount(new Account(ACCOUNT_TYPE.CHECKING, "John"));
		bank.addCustomer(john);
		assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
	}

	@Test
	public void checkingAccount() {
		Bank bank = new Bank();
		Account checkingAccount = new CheckingAccount(ACCOUNT_TYPE.CHECKING, "Bill");
		Customer bill = new Customer("Bill").openAccount(checkingAccount);
		bank.addCustomer(bill);
		checkingAccount.deposit(100.0);
		assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
	}

	@Test
	public void savings_account() {
		Bank bank = new Bank();
		Account checkingAccount = new SavingAccount(ACCOUNT_TYPE.SAVINGS, "Bill");
		bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

		checkingAccount.deposit(1500.0);
		assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
	}

	@Test
	public void maxi_savings_account_deposit() {
		Bank bank = new Bank();
		Account checkingAccount = new MaxiSavingAccount(ACCOUNT_TYPE.MAXI_SAVINGS, "Bill");
		bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

		checkingAccount.deposit(3000.0);
		assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
	}

	@Test
	public void maxi_savings_account_withdraw() {
		Bank bank = new Bank();
		Account checkingAccount = new MaxiSavingAccount(ACCOUNT_TYPE.MAXI_SAVINGS, "Tom");
		bank.addCustomer(new Customer("Tom").openAccount(checkingAccount));

		checkingAccount.deposit(3000.0);
		checkingAccount.withdraw(300.0);
		assertEquals(2.7, bank.totalInterestPaid(), DOUBLE_DELTA);
	}
}
