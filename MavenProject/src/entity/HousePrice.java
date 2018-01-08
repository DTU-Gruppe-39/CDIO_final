package entity;

public class HousePrice {
	HousePrice(){
		int[][] houseprice = new int [40][5];
		//Rødovervej, blå
		houseprice [1][0] = 50;
		houseprice [1][1] = 250;
		houseprice [1][2] = 750;
		houseprice [1][3] = 2250;
		houseprice [1][4] = 4000;
		houseprice [1][5] = 6000;
		//Hvidovrevej, blå
		houseprice [3][0] = 50;
		houseprice [3][1] = 250;
		houseprice [3][2] = 750;
		houseprice [3][3] = 2250;
		houseprice [3][4] = 4000;
		houseprice [3][5] = 6000;
		//Roskildevej, lyserød
		houseprice [6][0] = 100;
		houseprice [6][1] = 600;
		houseprice [6][2] = 1800;
		houseprice [6][3] = 5400;
		houseprice [6][4] = 8000;
		houseprice [6][5] = 11000;
		//Valby Langgade, lyserød
		houseprice [8][0] = 100;
		houseprice [8][1] = 600;
		houseprice [8][2] = 1800;
		houseprice [8][3] = 5400;
		houseprice [8][4] = 8000;
		houseprice [8][5] = 11000;
		//Allégade, lyserød
		houseprice [9][0] = 150;
		houseprice [9][1] = 800;
		houseprice [9][2] = 2000;
		houseprice [9][3] = 6000;
		houseprice [9][4] = 9000;
		houseprice [9][5] = 12000;
		//Frederiksberg Allé, grøn
		houseprice [11][0] = 200;
		houseprice [11][1] = 1000;
		houseprice [11][2] = 3000;
		houseprice [11][3] = 9000;
		houseprice [11][4] = 12500;
		houseprice [11][5] = 15000;
		//Büllowsvej, grøn
		houseprice [13][0] = 200;
		houseprice [13][1] = 1000;
		houseprice [13][2] = 3000;
		houseprice [13][3] = 9000;
		houseprice [13][4] = 12500;
		houseprice [13][5] = 15000;
		//GL. Kongevej, grøn
		houseprice [14][0] = 250;
		houseprice [14][1] = 1250;
		houseprice [14][2] = 3750;
		houseprice [14][3] = 10000;
		houseprice [14][4] = 14000;
		houseprice [14][5] = 18000;
		//Bernstorffsvej, grå
		houseprice [16][0] = 300;
		houseprice [16][1] = 1400;
		houseprice [16][2] = 4000;
		houseprice [16][3] = 11000;
		houseprice [16][4] = 15000;
		houseprice [16][5] = 19000;
		//Hellerupvej, grå
		houseprice [18][0] = 300;
		houseprice [18][1] = 1400;
		houseprice [18][2] = 4000;
		houseprice [18][3] = 11000;
		houseprice [18][4] = 15000;
		houseprice [18][5] = 19000;
		//Strandvej, grå
		houseprice [19][0] = 550;
		houseprice [19][1] = 1600;
		houseprice [19][2] = 4400;
		houseprice [19][3] = 12000;
		houseprice [19][4] = 16000;
		houseprice [19][5] = 20000;
		//Trianglen, rød
		houseprice [21][0] = 350;
		houseprice [21][1] = 1800;
		houseprice [21][2] = 5000;
		houseprice [21][3] = 14000;
		houseprice [21][4] = 17500;
		houseprice [21][5] = 21000;
		//Østerbrogade, rød
		houseprice [23][0] = 350;
		houseprice [23][1] = 1800;
		houseprice [23][2] = 5000;
		houseprice [23][3] = 14000;
		houseprice [23][4] = 17500;
		houseprice [23][5] = 21000;
		//Grønningen, rød
		houseprice [24][0] = 400;
		houseprice [24][1] = 2000;
		houseprice [24][2] = 6000;
		houseprice [24][3] = 15000;
		houseprice [24][4] = 18500;
		houseprice [24][5] = 22000;
		//Bredegade, hvid
		houseprice [26][0] = 450;
		houseprice [26][1] = 2200;
		houseprice [26][2] = 6600;
		houseprice [26][3] = 16000;
		houseprice [26][4] = 19500;
		houseprice [26][5] = 23000;
		//Kgs. Nytorv, hvid
		houseprice [27][0] = 450;
		houseprice [27][1] = 2200;
		houseprice [27][2] = 6600;
		houseprice [27][3] = 16000;
		houseprice [27][4] = 19500;
		houseprice [27][5] = 23000;
		//Østergade, hvid
		houseprice [29][0] = 500;
		houseprice [29][1] = 2400;
		houseprice [29][2] = 7200;
		houseprice [29][3] = 17000;
		houseprice [29][4] = 20500;
		houseprice [29][5] = 24000;
		//Amagertorv, gul
		houseprice [31][0] = 550;
		houseprice [31][1] = 2600;
		houseprice [31][2] = 7800;
		houseprice [31][3] = 18000;
		houseprice [31][4] = 22000;
		houseprice [31][5] = 25000;
		//Vimmelskaftet, gul
		houseprice [32][0] = 550;
		houseprice [32][1] = 2600;
		houseprice [32][2] = 7800;
		houseprice [32][3] = 18000;
		houseprice [32][4] = 22000;
		houseprice [32][5] = 25000;
		//Nygade, gul
		houseprice [34][0] = 600;
		houseprice [34][1] = 3000;
		houseprice [34][2] = 9000;
		houseprice [34][3] = 20000;
		houseprice [34][4] = 24000;
		houseprice [34][5] = 28000;
		//Frederiksberggade, lilla
		houseprice [37][0] = 700;
		houseprice [37][1] = 3500;
		houseprice [37][2] = 9000;
		houseprice [37][3] = 20000;
		houseprice [37][4] = 24000;
		houseprice [37][5] = 28000;
		//Rådhuspladsen, lilla
		houseprice [39][0] = 1000;
		houseprice [39][1] = 4000;
		houseprice [39][2] = 12000;
		houseprice [39][3] = 28000;
		houseprice [39][4] = 34000;
		houseprice [39][5] = 40000;
	
	}
}
