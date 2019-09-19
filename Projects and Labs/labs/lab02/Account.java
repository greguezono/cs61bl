/**
 * This class represents a bank account whose current balance is a nonnegative
 * amount in US dollars.
 */
public class Account {

    public int balance;
    public Account parentAccount;

    /** Initialize an account with the given BALANCE. */

    public Account(int balance, Account other) {
      this.balance = balance;
      this.parentAccount = other;
    }

    public Account(int balance) {
        this.balance = balance;
        this.parentAccount = null;
    }

    /** Deposits AMOUNT into the current account. */
    public void deposit(int amount) {
        if (amount < 0) {
            System.out.println("Cannot deposit negative amount.");
        } else {
            balance += amount;
        }
    }

    /**
     * Subtract AMOUNT from the account if possible. If subtracting AMOUNT
     * would leave a negative balance, print an error message and leave the
     * balance unchanged.
     */
    public boolean withdraw(int amount) {
        // TODO
        if (amount < 0) {
            System.out.println("Cannot withdraw negative amount.");
            return false;
        } else if (balance < amount && parentAccount == null) {
            System.out.println("Insufficient funds");
            return false;
        } else if (balance < amount && parentAccount != null){
            amount -= balance;
            balance = 0;
            parentAccount.withdraw(amount);
            return true;
        } else {
            balance -= amount;
            return true;
        }
    }

    /**
     * Merge account OTHER into this account by removing all money from OTHER
     * and depositing it into this account.
     */
    public void merge(Account other) {
        // TODO
        int temp;
        temp = other.balance;
        other.withdraw(temp);
        this.deposit(temp);

    }
  /*public static void main(String[] args) {
    Account kathy = new Account (500);
    Account megan = new Account(100, kathy);
    megan.withdraw(50);
    System.out.println(megan.balance);
    megan.withdraw(150);
    System.out.println(megan.balance);
    System.out.println(kathy.balance);
    megan.withdraw(9001);
  }
  */
}
