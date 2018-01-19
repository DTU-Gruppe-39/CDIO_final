	package entity;

import java.util.Arrays;

public class TwoDice {

	private int die1;	//value of 1st die.
	private int die2;	//value of 2nd die.
	private int faces;
	private int[][] scriptedRun;
	private int run;

	public TwoDice() {
		die1 = 0;
		die2 = 0;	
		faces =6;
		// Add if you need 2 dice
	}
	
	
	public void roll() {		//Rolls both dice to a value between 1 and 6.
		//die1
//		double d1=(float)Math.random();		// [0 ; 1[
//		double d2 = d1*faces;				// [0 ; 6[
		//die1 = (int)Math.ceil(d2);			// [1-6] integer

		//die2
//		double d3=(float)Math.random();		// [0 ; 1[
//		double d4 = d3*faces;				// [0 ; 6[
		//die2 = (int)Math.ceil(d4);			// [1-6] integer

		die1 = scriptedRun[run][1]; 
		die2 = scriptedRun[run][2];
		run++;
		
	} 
	public int getdie1() {	//Returns value of die 1.
		return die1;
	}
	public int getdie2() {	//Returns value of die 2.
		return die2;
	}
	public int getDiceTotal() {	//Returns the values of the two dice added together.
		return die1 + die2;
	}
	
	public void setupRunOne() {
		int [][] run1  = new int [23][3];
		scriptedRun = new int [23][3];
		//Spiller1
		run1 [0][1] = 1;
		run1 [0][2] = 2;
	
		//
		run1 [1][1] = 2;
		run1 [1][2] = 2;
	
		
		//
		run1 [2][1] = 2;
		run1 [2][2] = 2;
		
		
		//
		run1 [3][1] = 2;
		run1 [3][2] = 2;

		//Spiller1
		run1 [4][1] = 6;
		run1 [4][2] = 6;

		//Spiller1
		run1 [5][1] = 6;
		run1 [5][2] = 6;

		//Spiller1
		run1 [6][1] = 5;
		run1 [6][2] = 6;

		//
		run1 [7][1] = 3;
		run1 [7][2] = 2;

		//Spiller1 Buy house and hotel
		run1 [8][1] = 2;
		run1 [8][2] = 1;

		//
		run1 [9][1] = 5;
		run1 [9][2] = 5;

		//
		run1 [10][1] = 6;
		run1 [10][2] = 4;

		//Spiller1
		run1 [11][1] = 3;
		run1 [11][2] = 1;

		//pay for Jail
		run1 [12][1] = 1;
		run1 [12][2] = 1;

		//
		run1 [13][1] = 6;
		run1 [13][2] = 6;

		//
		run1 [14][1] = 6;
		run1 [14][2] = 4;

		//Spiller1
		run1 [15][1] = 2;
		run1 [15][2] = 1;
	
		//Pay rent on rødovrevej
		run1 [16][1] = 5;
		run1 [16][2] = 2;

		//Spiller1 rent coca cola
		run1 [17][1] = 3;
		run1 [17][2] = 1;

		//Pay rent shipping
		run1 [18][1] = 3;
		run1 [18][2] = 1;

		//Spiller1
		run1 [19][1] = 2;
		run1 [19][2] = 1;

		//Pay rent shipping
		run1 [20][1] = 6;
		run1 [20][2] = 4;
		
		//Buy Hellerupvej
		run1 [21][1] = 1;
		run1 [21][2] = 2;
		
		//Die on Hellerupvej
		run1 [22][1] = 1;
		run1 [22][2] = 2;


		
		System.out.println(Arrays.deepToString(run1));
		setScriptedRun(run1);
	}
	
	public void setupRunChance() {
		int [][] run1  = new int [16][3];
		scriptedRun = new int [16][3];
		//Spiller1
		run1 [0][1] = 1;
		run1 [0][2] = 1;
	
		//Spiller1
		run1 [1][1] = 2;
		run1 [1][2] = 3;
	
		
		//Spiller2
		run1 [2][1] = 1;
		run1 [2][2] = 1;
		
		
		//Spiller2
		run1 [3][1] = 2;
		run1 [3][2] = 3;

		//Spiller3
		run1 [4][1] = 1;
		run1 [4][2] = 1;

		//Spiller3
		run1 [5][1] = 2;
		run1 [5][2] = 3;

		//Spiller4
		run1 [6][1] = 1;
		run1 [6][2] = 1;

		//Spiller4
		run1 [7][1] = 2;
		run1 [7][2] = 3;

		//Spiller5
		run1 [8][1] = 1;
		run1 [8][2] = 1;

		//Spiller5
		run1 [9][1] = 2;
		run1 [9][2] = 3;

		//Spiller6
		run1 [10][1] = 1;
		run1 [10][2] = 1;

		//Spiller6
		run1 [11][1] = 2;
		run1 [11][2] = 3;

		run1 [12][1] = 2;
		run1 [12][2] = 3;
		
		run1 [13][1] = 2;
		run1 [13][2] = 3;
		
		run1 [14][1] = 2;
		run1 [14][2] = 3;
		
		run1 [15][1] = 2;
		run1 [15][2] = 3;


		
		System.out.println(Arrays.deepToString(run1));
		setScriptedRun(run1);
	}


	public int[][] getScriptedRun() {
		return scriptedRun;
	}


	public void setScriptedRun(int[][] scriptedRun) {
		this.scriptedRun = scriptedRun;
	}
}