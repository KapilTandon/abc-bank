/**
 * 
 */
package com.abc;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.abc.Transaction.TRANSACTION_TYPE;

/**
 * @author Tandon
 *
 */
public class MaxiSavingAccount extends Account {

	public MaxiSavingAccount(ACCOUNT_TYPE accountType, String accountId) {
		super(accountType, accountId);
		// TODO Auto-generated constructor stub
	}

	private double maxsaving_baseAmount = 1000.00;

	private double maxsaving_superbaseAmount = 1000.00;

	private double maxsaving_baseROI = 0.02;

	private double maxsaving_superbaseROI = 0.05;

	private double maxsaving_advanceROI = 0.10;

	public double getMaxsaving_baseAmount() {
		return maxsaving_baseAmount;
	}

	public void setMaxsaving_baseAmount(double maxsaving_baseAmount) {
		this.maxsaving_baseAmount = maxsaving_baseAmount;
	}

	public double getMaxsaving_superbaseAmount() {
		return maxsaving_superbaseAmount;
	}

	public void setMaxsaving_superbaseAmount(double maxsaving_superbaseAmount) {
		this.maxsaving_superbaseAmount = maxsaving_superbaseAmount;
	}

	public double getMaxsaving_baseROI() {
		return maxsaving_baseROI;
	}

	public void setMaxsaving_baseROI(double maxsaving_baseROI) {
		this.maxsaving_baseROI = maxsaving_baseROI;
	}

	public double getMaxsaving_superbaseROI() {
		return maxsaving_superbaseROI;
	}

	public void setMaxsaving_superbaseROI(double maxsaving_superbaseROI) {
		this.maxsaving_superbaseROI = maxsaving_superbaseROI;
	}

	public double getMaxsaving_advanceROI() {
		return maxsaving_advanceROI;
	}

	public void setMaxsaving_advanceROI(double maxsaving_advanceROI) {
		this.maxsaving_advanceROI = maxsaving_advanceROI;
	}

	public double interestEarned() {
		// TODO Auto-generated method stub
		double amount = sumTransactions();

		double interestEarned = 0;
		boolean withdrawalCondition = calculateInterestRate();

		if (withdrawalCondition) {
			interestEarned = amount * 0.001;
		} else {

			if (amount > maxsaving_superbaseAmount) {
				interestEarned = ((maxsaving_baseAmount * maxsaving_baseROI)
						+ (maxsaving_superbaseAmount * maxsaving_superbaseROI)
						+ ((amount - (maxsaving_baseAmount + maxsaving_superbaseAmount)) * maxsaving_advanceROI));
			} else if (amount < maxsaving_superbaseAmount && amount > maxsaving_baseAmount) {
				interestEarned = (maxsaving_baseAmount * maxsaving_baseROI)
						+ ((amount - maxsaving_baseAmount) * maxsaving_superbaseROI);
			} else {
				interestEarned = amount * maxsaving_baseROI;
			}

		}
		return interestEarned;
	}

	private boolean calculateInterestRate() {

		Date now = DateProvider.getInstance().now();
		boolean dateCheck = false;
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.DATE, -10);
		now = cal.getTime();

		List<Transaction> transaction = this.getTransactions();
		Collections.sort(transaction, new TransactionDateComparator());
		for (Transaction t : this.getTransactions()) {
			if (t.getTransactionType() == TRANSACTION_TYPE.WITHDRAW_MONEY) {
				if (t.getTransactionDate().after(now)) {
					dateCheck = true;
				}
			}
		}
		return dateCheck;

	}

	public class TransactionDateComparator implements Comparator<Transaction> {
		public int compare(Transaction t1, Transaction t2) {
			// TODO Auto-generated method stub
			return t1.getTransactionDate().compareTo(t2.getTransactionDate());
		}
	}
}
