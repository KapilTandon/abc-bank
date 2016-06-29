package com.abc;

import java.util.Date;

public class Transaction {
	public final double amount;

	public static enum TRANSACTION_TYPE {
		DEPOSIT_MONEY(0), WITHDRAW_MONEY(1);

		private TRANSACTION_TYPE(int value) {
		}
	};

	private final TRANSACTION_TYPE transactionType;

	private Date transactionDate;

	public Transaction(double amount, TRANSACTION_TYPE transactionType) {
		this.amount = amount;
		this.transactionType = transactionType;
		this.transactionDate = DateProvider.getInstance().now();
	}

	public double getAmount() {
		return amount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public TRANSACTION_TYPE getTransactionType() {
		return transactionType;
	}

}
