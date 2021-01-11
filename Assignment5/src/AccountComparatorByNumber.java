/*---------------------------------------
Genuine author: Ron Levi, I.D.: 205508021
 Date: 02-01-2020
---------------------------------------*/
import java.util.Comparator;

public class AccountComparatorByNumber implements Comparator<BankAccount>{

	@Override
	public int compare(BankAccount account1, BankAccount account2) {
		// task 2b
		if (account1==null | account2 == null)
			throw new IllegalArgumentException("can not compare null objects");
		return (int)Math.signum(account1.getAccountNumber()-account2.getAccountNumber());
	}

}
