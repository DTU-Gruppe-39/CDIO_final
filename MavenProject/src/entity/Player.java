package entity;

import gamelogic.Game;

public class Player {
	private String name;
	//	private TwoDice dice;
	private AccountBalance account;
	private boolean isDead;
	private boolean isWinner;
	private boolean isJailed;
	private int haveJailCard;
	private int currentField = 0;
	private int tax;
	private int netWorth;
	public int propertyValue;
	public int RoundsInJail = 0;
	public int ShippingCompaniesOwned = 0;


	public Player () {
		//		TwoDice dice = new TwoDice();
		this.account = new AccountBalance(0);
		//		this.dice = dice;
		isDead = false;
		isWinner = false;
		isJailed = false;

		this.haveJailCard = 0;

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
	
	public void setNewBalance(double balance) {
		account.newBalance((int)balance);
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


	public int getHaveJailCard() {
		return haveJailCard;
	}
	
	public void boughtShippingCompany() {
		this.ShippingCompaniesOwned += 1;
	}
	
	public int getShippingCompaniesOwned() {
		return this.ShippingCompaniesOwned;
	}
	
	public void CalculateTax() {
		tax = 0;
		tax = netWorth/10;
	}
	
	public int getTax() {
		return tax;
		
	}
	
	public void setHaveJailCard(int haveJailCard) {
		this.haveJailCard += haveJailCard;
	}
	
	public void setNetWorth() { //adds together players balance and property value
		this.netWorth = 0;
		this.netWorth = getBalance() + getPropertyValue();
	}
	
	public int getNetWorth() { //adds together players balance and property value
		return this.netWorth;
	}
	
	public void setPropertyValue() { //method that gets a players property value
		this.propertyValue = 0;
		for (int i=0; i<40; i++) {
			if (Game.getWhosTurn()==Game.getFields()[i][4]) {
				this.propertyValue += Game.getFields()[i][6];
				if(Game.getFields()[i][2]== 1 || Game.getFields()[i][2]==2) {
					this.propertyValue += (Game.getFields()[i][9]*1000);					
				}
				else if(Game.getFields()[i][2]== 3 || Game.getFields()[i][2]==4) {
					this.propertyValue += (Game.getFields()[i][9]*2000);					
				}
				else if(Game.getFields()[i][2]== 5 || Game.getFields()[i][2]==6) {
					this.propertyValue += (Game.getFields()[i][9]*3000);					
				}
				else if(Game.getFields()[i][2]== 7 || Game.getFields()[i][2]==8) {
					this.propertyValue += (Game.getFields()[i][9]*4000);					
				}
				else if (Game.getFields()[i][2]== 9 ||Game.getFields()[i][2]== 10) {
					System.out.println("Spilleren ejer et redderi eller et bryggeri");
				}
				else {
					System.out.println("Error in PropertyValue in Player class");
				}
			}
		}
	}
	public int getPropertyValue() {
		return this.propertyValue;
		
	}
}

