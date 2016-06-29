package com.abc;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.abc.Transaction.TRANSACTION_TYPE;

public class TransactionTest {
	@Test
	public void transaction() {
		Transaction t = new Transaction(5, TRANSACTION_TYPE.DEPOSIT_MONEY);
		assertTrue(t instanceof Transaction);
	}
}
