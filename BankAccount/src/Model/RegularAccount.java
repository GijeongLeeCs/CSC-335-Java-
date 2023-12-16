/*
 * Gijeong Lee
 * This class extends BankAccount
 */

package Model;

public class RegularAccount extends BankAccount{

	/*
	 * Constructor for the class
	 * @param ID which is a string representing ID
	 * @param double initialBalance which is an initial balance.
	 */
	public RegularAccount(String ID, double initialBalance)
	{
		super(ID, initialBalance);
	}

	/*
	 * withdrawals up to the balance only
	 * @param amount which is double representing an amount of withdraw
	 */
	public void withdraw(double amount) {
		if(balance- amount >=0)
			balance -= amount;
	}


	/*
	 * @param amount which is a double representing an amount of deposit
	 */
	public void deposit(double amount) {
		balance += amount;
		
	}
	

	
}
