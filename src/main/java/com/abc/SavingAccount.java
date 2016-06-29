/**
 * 
 */
package com.abc;

/**
 * @author Tandon
 *
 */
public class SavingAccount extends Account {

	public SavingAccount(ACCOUNT_TYPE accountType, String accountId) {
		super(accountType, accountId);
		// TODO Auto-generated constructor stub
	}

	private double saving_baseAmount = 1000.00;

	private double saving_baseROI = 0.001;

	private double saving_advanceROI = 0.002;

	public double interestEarned() {
		// TODO Auto-generated method stub
		double amount = sumTransactions();
		double interestEarned = 0;
		if (amount > saving_baseAmount) {
			interestEarned = ((saving_baseAmount * saving_baseROI)
					+ ((amount - saving_baseAmount) * saving_advanceROI));
		} else {
			interestEarned = amount * saving_baseROI;
		}
		return interestEarned;
	}

	public double getSaving_baseAmount() {
		return saving_baseAmount;
	}

	public void setSaving_baseAmount(double saving_baseAmount) {
		this.saving_baseAmount = saving_baseAmount;
	}

	public double getSaving_baseROI() {
		return saving_baseROI;
	}

	public void setSaving_baseROI(double saving_baseROI) {
		this.saving_baseROI = saving_baseROI;
	}

	public double getSaving_advanceROI() {
		return saving_advanceROI;
	}

	public void setSaving_advanceROI(double saving_advanceROI) {
		this.saving_advanceROI = saving_advanceROI;
	}

}
