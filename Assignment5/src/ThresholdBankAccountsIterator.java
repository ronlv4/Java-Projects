import java.util.Iterator;
import java.util.NoSuchElementException;


public class ThresholdBankAccountsIterator implements Iterator<BankAccount> {

	private BinaryTreeInOrderIterator<BankAccount> iterator;
    private BankAccount current;
    private int balanceThreshold;
    
    public ThresholdBankAccountsIterator(BankAccountsBinarySearchTree bankAccountsTree, int balanceThreshold) {
        // task 5c
        iterator = new BinaryTreeInOrderIterator<BankAccount>(bankAccountsTree.root);
        this.balanceThreshold = balanceThreshold;
        prepareNext();
    }
    private void prepareNext() {
        boolean foundAccount = false;
        while (iterator.hasNext() & !foundAccount) {
            BankAccount tempBankAccount = iterator.next();
            if (tempBankAccount.getBalance() >= balanceThreshold) {
                current = tempBankAccount;
                foundAccount = true;
            }
        }
        if (!foundAccount)
            current = null;
    }

    //Complete the following method
    @Override
    public boolean hasNext() {
        // task 5c
        return current!=null;
    }

    //Complete the following method
    @Override
    public BankAccount next() {
        // task 5c
        if (!hasNext()){
            throw new NoSuchElementException("No such element");
        }
        BankAccount toReturn = current;
        prepareNext();
        return toReturn;
    }
}
