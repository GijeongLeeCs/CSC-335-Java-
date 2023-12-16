/*
 * 
 *Gijeong Lee
 *This is for testing three different objects and their functions.
 */
package TEST;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Model.BankAccount;
import Model.FeeAccount;
import Model.RegularAccount;
import Model.SafeAccount;

class BankAccountTest {

	@Test
	void test() {
		BankAccount b = new RegularAccount("01", 100.0);
		BankAccount f = new FeeAccount("02", 100.0);
		BankAccount s = new SafeAccount("03", 100.0);
		
		
		assertEquals(100.0, ((RegularAccount)b).getBalance()); // Testing initial balance
		((RegularAccount)b).deposit(5.2); 
		assertEquals(105.20, ((RegularAccount)b).getBalance()); // Testing deposit
		((RegularAccount)b).withdraw(50.0);
		assertEquals(55.20, ((RegularAccount)b).getBalance(), 0.001); // Testing withdraw
		((RegularAccount)b).withdraw(100.0);
		assertEquals(55.20, ((RegularAccount)b).getBalance(), 0.001);// Testing when it tries to withdraw more money than the balance
		
		
		assertEquals(100.0, ((FeeAccount)f).getBalance());// Testing initial balance
		((FeeAccount)f).deposit(5.2);
		assertEquals(105.20, ((FeeAccount)f).getBalance());// Testing deposit
		((FeeAccount)f).withdraw(50.0);
		assertEquals(53.20, ((FeeAccount)f).getBalance(), 0.001);// Testing withdraw
		((FeeAccount)f).withdraw(100.0);
		assertEquals(53.20, ((FeeAccount)f).getBalance(), 0.001);// Testing when it tries to withdraw more money than the balance
		
		
		assertEquals(100.0, ((SafeAccount)s).getBalance());// Testing initial balance
		((SafeAccount)s).deposit(5.2);
		assertEquals(105.20, ((SafeAccount)s).getBalance());// Testing deposit
		((SafeAccount)s).withdraw(50.0);
		assertEquals(55.20, ((SafeAccount)s).getBalance(), 0.001);// Testing withdraw
		((SafeAccount)s).withdraw(100.0);
		assertEquals(0.00, ((SafeAccount)s).getBalance(), 0.001);// Testing when it tries to withdraw more money than the balance
		assertEquals(955.20, ((SafeAccount)s).getCredit(), 0.001);// Testing remain credit
		
		((SafeAccount)s).deposit(45.80);
		assertEquals(1.00, ((SafeAccount)s).getBalance(), 0.001);//  Testing remain balance after deposit
		assertEquals(1000.00, ((SafeAccount)s).getCredit(), 0.001);// Testing remain balance after deposit
		((SafeAccount)s).withdraw(1000.0);
		assertEquals(0.00, ((SafeAccount)s).getBalance(), 0.001);// Testing when it tries to withdraw more money than the balance
		assertEquals(1.00, ((SafeAccount)s).getCredit(), 0.001);// Testing credit when it tries to withdraw more money than the balance
	}

}
