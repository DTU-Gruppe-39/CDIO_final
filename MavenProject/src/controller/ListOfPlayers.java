package controller;


import boundary.GUI_GUI;
import entity.Player;

public class ListOfPlayers {

	private static Player[] Players;

	public ListOfPlayers(int amountOfPlayers ) {
		Players= new Player[amountOfPlayers];
		for (int i = 1; i < amountOfPlayers; i++)
			Players[i]=new Player();
	} 

	public void addplayer(int amountOfPlayers){
		String[] names = GUI_GUI.getNames();
		for (int i=1; i <= amountOfPlayers; i++){
			Players[i].setName(names[(i - 1)]);
		}	
	}

	public Player[] getPlayers(){
		return Players;
	}

	public static Player getPlayers(int index){
		return Players[index];
	}

	//	public boolean isVinder(){
	//		for (int i=0;i<spillere.length;i++){
	//			if (spillere[i].isVinder())
	//				return true;
	//		}		
	//		return false;
	//	}
	//
	//	public int nextPlayer(int index){
	//		index = ++index % Players.length;
	//		return index;
	//	}
	public static void addFunds(int amountOfPlayers) {
		for(int i=1; i <= amountOfPlayers; i++) {
			Players[i].setNewBalance(30000);
		}
	}
}


