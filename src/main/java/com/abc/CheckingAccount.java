/**
 * 
 */
package com.abc;

/**
 * @author Tandon
 *
 */
public class CheckingAccount extends Account {

	public CheckingAccount(ACCOUNT_TYPE accountType, String accountId) {
		super(accountType, accountId);
		// TODO Auto-generated constructor stub
	}

	private double checking_baseAmount = 1000.00;

	private double checking_baseROI = 0.001;

	private double checking_advanceROI = 0.001;

	public double interestEarned() {
		// TODO Auto-generated method stub
		double amount = sumTransactions();
		double interestEarned = 0;
		if (amount > checking_baseAmount) {
			interestEarned = ((checking_baseAmount * checking_baseROI)
					+ ((amount - checking_baseAmount) * checking_advanceROI));
		} else {
			interestEarned = amount * checking_baseROI;
		}
		return interestEarned;
	}

	public double getChecking_baseAmount() {
		return checking_baseAmount;
	}

	public void setChecking_baseAmount(double checking_baseAmount) {
		this.checking_baseAmount = checking_baseAmount;
	}

	public double getChecking_baseROI() {
		return checking_baseROI;
	}

	public void setChecking_baseROI(double checking_baseROI) {
		this.checking_baseROI = checking_baseROI;
	}

	public double getChecking_advanceROI() {
		return checking_advanceROI;
	}

	public void setChecking_advanceROI(double checking_advanceROI) {
		this.checking_advanceROI = checking_advanceROI;
	}

}
