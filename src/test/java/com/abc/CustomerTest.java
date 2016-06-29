package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.abc.Account.ACCOUNT_TYPE;

public class CustomerTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test // Test customer statement generation
	public void testApp() {

		Account checkingAccount = new Account(ACCOUNT_TYPE.CHECKING, "Checking1");
		Account savingsAccount = new Account(ACCOUNT_TYPE.SAVINGS, " Saving1");

		Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

		checkingAccount.deposit(100.0);
		savingsAccount.deposit(4000.0);
		savingsAccount.withdraw(200.0);
		assertEquals("Statement for Henry\n" + "\n" + "Checking Account\n" + "  deposit $100.00\n" + "Total $100.00\n"
				+ "\n" + "Savings Account\n" + "  deposit $4,000.00\n" + "  withdrawal $200.00\n" + "Total $3,800.00\n"
				+ "\n" + "Total In All Accounts $3,900.00", henry.getStatement());
	}

	@Test
	public void testOneAccount() {
		Customer oscar = new Customer("Oscar").openAccount(new Account(ACCOUNT_TYPE.SAVINGS, "Oscar"));
		assertEquals(1, oscar.getNumberOfAccounts());
	}

	@Test
	public void testTwoAccount() {
		Customer oscar = new Customer("Oscar").openAccount(new Account(ACCOUNT_TYPE.SAVINGS, "Oscar"));
		oscar.openAccount(new Account(ACCOUNT_TYPE.CHECKING, "Oscar"));
		assertEquals(2, oscar.getNumberOfAccounts());
	}

	@Ignore
	public void testThreeAcounts() {
		Customer oscar = new Customer("Oscar").openAccount(new Account(ACCOUNT_TYPE.SAVINGS, "Oscar"));
		oscar.openAccount(new Account(ACCOUNT_TYPE.CHECKING, "Oscar"));
		assertEquals(3, oscar.getNumberOfAccounts());
	}

	@Test
	public void testInterAccountTransferSameCustomer() {
		double transferAmount = 500;

		Account sourceAccount = new Account(ACCOUNT_TYPE.CHECKING, "Harry");

		Account destinationAccount = new Account(ACCOUNT_TYPE.SAVINGS, " Harry");
		Customer customer = new Customer("Harry").openAccount(sourceAccount).openAccount(destinationAccount);

		sourceAccount.deposit(1000);

		assertEquals(true, customer.interAccountTransfer(transferAmount, sourceAccount, destinationAccount));
	}

	@Test
	public void testInterAccountTransferSameCustomerInSufficientBalance() {
		double transferAmount = 500;

		Account sourceAccount = new Account(ACCOUNT_TYPE.CHECKING, "Harry");

		Account destinationAccount = new Account(ACCOUNT_TYPE.SAVINGS, " Harry");
		Customer customer = new Customer("Harry").openAccount(sourceAccount).openAccount(destinationAccount);

		sourceAccount.deposit(100);
		exception.expect(IllegalArgumentException.class);
		customer.interAccountTransfer(transferAmount, sourceAccount, destinationAccount);
	}

	@Test
	public void testInterAccountTransferDifferentCustomer() {
		double transferAmount = 500;

		Account sourceAccount = new Account(ACCOUNT_TYPE.CHECKING, "Harry");
		Customer customer1 = new Customer("Harry").openAccount(sourceAccount);

		Account destinationAccount = new Account(ACCOUNT_TYPE.SAVINGS, " Peter");
		Customer customer2 = new Customer("Peter").openAccount(destinationAccount);

		sourceAccount.deposit(1000);

		assertEquals(false, customer1.interAccountTransfer(transferAmount, sourceAccount, destinationAccount));

	}

}
