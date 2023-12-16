/*
 * Gijeong Lee
 * This is an abstract class BankAccount 
 * It has three subclasses including RegularAccount, SafeAccount, 
 * and FeeAccount in separate files
 */

package Model;

public abstract class BankAccount {
	
	protected String ID;
	protected double balance;
	
	/*
	 * Constructor for the class
	 * @param ID which is a string representing ID
	 * @param double initialBalance which is an initial balance.
	 */
	public BankAccount(String ID, double initialBalance) {
		this.ID = ID;
		balance = initialBalance;
		
	}

	/*
	 * @return balance which is double representing current balance
	 */
	public double getBalance() {
		return balance;
	}
	
	
	/*
	 *@param amount which is a double representing an amount of deposit
	 */
	abstract public void deposit(double amount);
	
	/*
	 * @param amount which is double representing an amount of withdraw
	 */
	abstract public void withdraw(double amount);

}

