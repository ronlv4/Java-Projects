/*---------------------------------------
Genuine author: Ron Levi, I.D.: 205508021
 Date: 02-01-2020
---------------------------------------*/
import java.util.Comparator;

public class AccountComparatorByName implements Comparator<BankAccount>{

	@Override
	public int compare(BankAccount account1, BankAccount account2) {
		// task 2a
		if (account1==null | account2 == null)
			throw new IllegalArgumentException("can not compare null objects");
		return account1.getName().compareTo(account2.getName());
	}

}
