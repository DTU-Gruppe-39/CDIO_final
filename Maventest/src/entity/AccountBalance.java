package entity;

public class AccountBalance {
	int balance = 0;

	public AccountBalance(int balance) {
		this.balance = balance;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public void newBalance (int balance) {
		if (this.balance + balance < 0) {
			this.balance = 0;
		} else {
			this.balance += balance;
		}
	}
}