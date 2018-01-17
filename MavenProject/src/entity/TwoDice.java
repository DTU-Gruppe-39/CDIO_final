	package entity;

public class TwoDice {

	private int die1 = 0;	//value of 1st die.
	private int die2 = 0;	//value of 2nd die.
	private int faces;
	private int [][] testDice;
	public TwoDice() {
		this.die1 = 0;
		this.die2 = 0;	
		this.faces =6;
		// Add if you need 2 dice
	}
	public void setTestDice() {
		this.testDice = new int [36][2];
	testDice [1][1] = 1;
	testDice [1][2] = 1;
	testDice [2][1] = 3;
	testDice [2][2] = 2;
	
	testDice [3][1] = 1;
	testDice [3][2] = 1;
	testDice [4][1] = 3;
	testDice [4][2] = 2;
	
	testDice [5][1] = 1;
	testDice [5][2] = 1;
	testDice [6][1] = 3;
	testDice [6][2] = 2;
	
	testDice [7][1] = 1;
	testDice [7][2] = 1;
	testDice [8][1] = 3;
	testDice [8][2] = 2;
	
	testDice [9][1] = 1;
	testDice [9][2] = 1;
	testDice [10][1] = 3;
	testDice [10][2] = 2;
	
	testDice [11][1] = 1;
	testDice [11][2] = 1;
	testDice [12][1] = 3;
	testDice [12][2] = 2;
	
	testDice [13][1] = 5;
	testDice [13][2] = 5;
	testDice [14][1] = 3;
	testDice [14][2] = 2;
	
	testDice [15][1] = 5;
	testDice [15][2] = 5;
	testDice [16][1] = 3;
	testDice [16][2] = 2;
	
	testDice [17][1] = 5;
	testDice [17][2] = 5;
	testDice [18][1] = 3;
	testDice [18][2] = 2;
	
	testDice [19][1] = 5;
	testDice [19][2] = 5;
	testDice [20][1] = 3;
	testDice [20][2] = 2;
	
	testDice [21][1] = 5;
	testDice [21][2] = 5;
	testDice [22][1] = 3;
	testDice [22][2] = 2;
	
	testDice [23][1] = 5;
	testDice [23][2] = 5;
	testDice [24][1] = 3;
	testDice [24][2] = 2;
	
	testDice [25][1] = 6;
	testDice [25][2] = 5;
	
	testDice [26][1] = 6;
	testDice [26][2] = 5;
	
	testDice [27][1] = 6;
	testDice [27][2] = 5;
	
	testDice [28][1] = 6;
	testDice [28][2] = 5;
	
	testDice [29][1] = 6;
	testDice [29][2] = 5;
	
	testDice [30][1] = 6;
	testDice [30][2] = 5;
	
	testDice [31][1] = 1;
	testDice [31][2] = 2;
	
	testDice [32][1] = 1;
	testDice [32][2] = 2;
	
	testDice [33][1] = 1;
	testDice [33][2] = 2;
	
	testDice [34][1] = 1;
	testDice [34][2] = 2;
	
	testDice [35][1] = 1;
	testDice [35][2] = 2;
	
	testDice [36][1] = 1;
	testDice [36][2] = 2;
	}
	
	public void roll() {		//Rolls both dice to a value between 1 and 6.
		//die1
//		double d1=(float)Math.random();		// [0 ; 1[
//		double d2 = d1*1;				// [0 ; 6[
//		this.die1 = (int)Math.ceil(d2);			// [1-6] integer
		die1++;

		//die2
//		double d3=(float)Math.random();		// [0 ; 1[
//		double d4 = d3*0;				// [0 ; 6[
//		this.die2 = (int)Math.ceil(d4);			// [1-6] integer

	} 
	public int getdie1() {	//Returns value of die 1.
		return this.testDice[die1][1];
	}
	public int getdie2() {	//Returns value of die 2.
		return this.testDice[die1][2];
	}
	public int getDiceTotal() {	//Returns the values of the two dice added together.
		return getdie1() + getdie2();
	}
}