package entity;

import gamelogic.Game;

public class Player {
	private String name;
	//	private TwoDice dice;
	private AccountBalance account;
	private boolean isDead;
	private boolean isWinner;
	private boolean isJailed;
	private int currentField = 0;
	private int netWorth;
	public int propertyValue;
	public int RoundsInJail = 0;


	public Player () {
		//		TwoDice dice = new TwoDice();
		this.account = new AccountBalance(0);
		//		this.dice = dice;
		isDead = false;
		isWinner = false;
		isJailed = false;
		netWorth = 0;
	}

	public int getCurrentField() {
		return currentField;
	}

	public void setCurrentField(int currentField) {
		this.currentField = currentField;
	}

	//	public TwoDice getDice() {
	//		return dice;
	//	}
	
	public int getRoundsInJail() {
		return this.RoundsInJail;
	}
	
	public void StayedInJail() {
		this.RoundsInJail++;
	}
	
	public void GotOutOfJail() {
		this.RoundsInJail = 0;
	}

	public int getBalance() {
		return this.account.balance;
	}

	public void setBalance(int balance) {
		this.account.setBalance(balance);
	}

	public void setNewBalance(int balance) {
		account.newBalance(balance);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public boolean isJailed() {
		return isJailed;
	}

	public void setJailed(boolean isJailed) {
		this.isJailed = isJailed;
	}

	public boolean isWinner() {
		return isWinner;
	}

	public void setWinner(boolean Winner) {
		this.isWinner = Winner;
	}

	public int getNetWorth() { //adds together players balance and property value
		this.netWorth = getBalance() + getPropertyValue();
		return this.netWorth;
	}
	public int getPropertyValue() { //method that gets a players property value
		for (int i=0; i<40; i++) {
			if (Game.getWhosTurn()==Game.getFields()[i][4]) {
				this.propertyValue += Game.getFields()[i][1];
			}
		}
		return this.propertyValue;

	}
}

