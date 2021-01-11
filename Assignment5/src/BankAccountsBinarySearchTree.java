/*---------------------------------------
 Genuine author: <name>, I.D.: <id number>
 Date: xx-xx-2020 
---------------------------------------*/
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BankAccountsBinarySearchTree extends BinarySearchTree<BankAccount>{

	public BankAccountsBinarySearchTree(Comparator<BankAccount> myComparator) {
		super(myComparator);
	}
	
	// Complete the following methods
	public void balance(){
		// task 5b
		Iterator<BankAccount> it = iterator();
		List<BankAccount> accounts = new LinkedList<>();
		while (it.hasNext()) {
			BankAccount temp = it.next();
			accounts.add(temp);
			remove(temp);
		}
		buildBalancedTree(accounts,0,accounts.size()-1);
	}
	
	private void buildBalancedTree(List<BankAccount> list, int low, int high){
		// task 5b
			insert(list.get((low+high)/2));
		if (low!=high){
			buildBalancedTree(list,low, (low+high)/2);
			buildBalancedTree(list,(low+high)/2+1, high);
		}
	}
}
