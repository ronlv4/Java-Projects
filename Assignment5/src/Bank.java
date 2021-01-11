import javax.swing.*;

/*---------------------------------------
 Genuine author: <name>, I.D.: <id number>
 Date: xx-xx-2020 
---------------------------------------*/
/**
 * This class represents a bank management system.
 * bank has bank accounts you can deposit or withdrawal money to
 * it has name, number and balance and supports operations like deposit and withdraw.
 *
 * @author Ron Levi
 */

public class Bank {

	private BankAccountsBinarySearchTree namesTree;
	private BankAccountsBinarySearchTree accountNumbersTree;
	
	public Bank() {
		namesTree = new BankAccountsBinarySearchTree(new AccountComparatorByName());
		accountNumbersTree = new BankAccountsBinarySearchTree(new AccountComparatorByNumber());
	}
    /**
     * this function lookup for a bank account inside the bank system.
     * @param name is the name of the bank account to be searched for, contains only letters.
     * @return if found a bank accounts whose name matches the parameter returns the bank account
     * otherwise returns null
     */

	public BankAccount lookUp(String name){
		// create an Entry with the given name, a "dummy" accountNumber (1) and zero balance
		// This "dummy" accountNumber will be ignored when executing getData
		BankAccount lookFor = new BankAccount(name, 1, 0);
		return (BankAccount)namesTree.findData(lookFor);
	}
    /**
     * this function lookup for a bank account inside the bank system.
     * @param accountNumber is the number of the bank account to be searched for, contains only digits.
     * @return if found a bank accounts whose account number matches the parameter returns the bank account
     * otherwise returns null
     */
	public BankAccount lookUp(int accountNumber){
		// create an Entry with a "dummy" name, zero balance and the given accountNumber
		// This "dummy" name will be ignored when executing getData
		BankAccount lookFor = new BankAccount("dummy", accountNumber,0);
		return (BankAccount)accountNumbersTree.findData(lookFor);
	}
	
	// END OF Given code -----------------------------------
	
	// Complete the methods from here on
    /**
     * this function adds a new bank account to the bank's system.
     * @param newAccount is the account to be added.
     * @return false if newAccount already exists in the system (the account was not added).
     * otherwise returns true
     * otherwise returns null
     */

	public boolean add(BankAccount newAccount) {
		// task 6a
		if (lookUp(newAccount.getAccountNumber()) != null || lookUp(newAccount.getName()) != null)
			return false;
		else {
			namesTree.insert(newAccount);
			accountNumbersTree.insert(newAccount);
			return true;
		}
	}
    /**
     * this function removes a specified bank account from the bank's system.
     * @param name is the account's name to be removed.
     * @return false if no bank account by the parameter name was found.
     * if account was found, it is removed and true is being returned
     */

	public boolean delete(String name) {
		// this first line is given in the assignment file
		BankAccount toRemove = lookUp(name);
		// complete this:
		if (toRemove==null)
			return false;
		if (namesTree.contains(toRemove)) {
			namesTree.remove(toRemove);
			accountNumbersTree.remove(toRemove);
			return true;
		}
		return false;
		// task 6b
	}
    /**
     * this function removes a specified bank account from the bank's system.
     * @param accountNumber is the account's number to be removed.
     * @return false if no bank account by the parameter number was found.
     * if account was found, it is removed and true is being returned
     */
	
	public boolean delete(int accountNumber) {
		// this first line is given in the assignment file
		BankAccount toRemove = lookUp(accountNumber);
		// complete this:
		if (toRemove==null)
			return false;
		if (accountNumbersTree.contains(toRemove)) {
			namesTree.remove(toRemove);
			accountNumbersTree.remove(toRemove);
			return true;
		}
		return false;
		// task 6c
	}

    /**
     * this function deposit money to a specified bank account from the bank's system.
     * @param amount is the amount to be deposited, must be non negative number.
     * @param accountNumber is the account number to which amount will be deposited
     * @return false if account Number was not found, otherwise deposit money and returns true.
     * @throws RuntimeException if the total money in the account exceeds Integer.MAX_VALUE after depositing.
     */
	public boolean depositMoney(int amount, int accountNumber) {
		// task 6d
		BankAccount toDeposit = lookUp(accountNumber);
		if (toDeposit != null) {
			return toDeposit.depositMoney(amount);
		}
		return false;
	}
    /**
     * this function withdrawal money from a specified bank account from the bank's system.
     * @param amount is the amount to be withdrawaled, must be non negative number.
     * @param accountNumber is the account number from which amount will be withdrawaled.
     * @return false if account Number was not found, otherwise withdrawal money and returns true.
     */

	public boolean withdrawMoney(int amount, int accountNumber){
		// task 6e
		BankAccount toWithdraw = lookUp(accountNumber);
		if (toWithdraw != null) {
			return toWithdraw.withdrawMoney(amount);
		}
		return false;
	}	
}
