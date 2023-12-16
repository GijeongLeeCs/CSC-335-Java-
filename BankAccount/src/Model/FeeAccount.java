/*
 * Gijeong Lee
 * This class extends BankAccount
 */

package Model;

public class FeeAccount extends BankAccount {

	/*
	 * Constructor for the class
	 * @param ID which is a string representing ID
	 * @param double initialBalance which is an initial balance.
	 */
	public FeeAccount(String ID, double initialBalance) {
		super(ID, initialBalance);
		
	}

	/*
	 * @param amount which is a double representing an amount of deposit
	 */
	public void deposit(double amount) {
		balance += amount;
		
	}
	
	/*
	 * Add the $2.00 fee to the amount being withdrawn
	 *  If there is not enough to allow the balance plus this $2.00 fee,
	 *  do not allow the transaction.
	 * @param amount which is double representing an amount of withdraw
	 */
	public void withdraw(double amount) {
		double fee = 2.00;
		
		if(balance- (fee+ amount) >=0)
		{
			balance -= fee;
			balance -= amount;
		}
		
	}
	
	

}
