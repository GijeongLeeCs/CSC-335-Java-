/*
 * Gijeong Lee
 * This class extends BankAccount
 */

package Model;

public class SafeAccount extends BankAccount{
	double credit = 1000.00;
	
	/*
	 * @param ID which is a string representing ID
	 * @param double initialBalance which is an initial balance.
	 */
	public SafeAccount(String ID, double initialBalance) {
		super(ID, initialBalance);
	}

	/*
	 * return credit which is an double.
	 */
	public double getCredit()
	{
		return credit;
	}
	
	/*
	 * @param amount which is an double representing amount of deposit.
	 * 
	 * if credit is less than 1000.00, deposit amount first goes towards loan amount
	 * Additional funds can then go to the balance
	 */
	public void deposit(double amount)
	{
		if(credit < 1000.00) {
			credit += amount;
			double addit = credit - 1000.00;
			if(addit>0)
			{
				credit = 1000.00;
				balance += addit;
			}
	
		}
		
		else {
			balance += amount;
		}
		
	}
	
	/*
	 * @param amount which is double representing an amount of withdraw
	 * withdrawals can go up to $1000.00 over the balance
	 */
	public void withdraw(double amount) {
		if((balance - amount) >= 0)
		{
			balance -= amount;
		}
		
		else {
			double rest = amount - balance;
			balance = 0.00;
			credit -= rest;
			
		}
		
		
	}

}
