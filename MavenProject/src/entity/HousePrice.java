package entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HousePrice {
	private static int[] HousePriceInt;
	private static int[][] houseRentPrice;
	public static void setupHouse(){
	int [][] houseRentPrice2  = new int [40][5];
	houseRentPrice = new int [40][5];
	//Rødovervej, blå
	houseRentPrice2 [1][0] = 50;
	houseRentPrice2 [1][1] = 250;
	houseRentPrice2 [1][2] = 750;
	houseRentPrice2 [1][3] = 2250;
	houseRentPrice2 [1][4] = 4000;
	houseRentPrice2 [1][5] = 6000;
	//Hvidovrevej, blå
	houseRentPrice2 [3][0] = 50;
	houseRentPrice2 [3][1] = 250;
	houseRentPrice2 [3][2] = 750;
	houseRentPrice2 [3][3] = 2250;
	houseRentPrice2 [3][4] = 4000;
	houseRentPrice2 [3][5] = 6000;
	//Roskildevej, lyserød
	houseRentPrice2 [6][0] = 100;
	houseRentPrice2 [6][1] = 600;
	houseRentPrice2 [6][2] = 1800;
	houseRentPrice2 [6][3] = 5400;
	houseRentPrice2 [6][4] = 8000;
	houseRentPrice2 [6][5] = 11000;
	//Valby Langgade, lyserød
	houseRentPrice2 [8][0] = 100;
	houseRentPrice2 [8][1] = 600;
	houseRentPrice2 [8][2] = 1800;
	houseRentPrice2 [8][3] = 5400;
	houseRentPrice2 [8][4] = 8000;
	houseRentPrice2 [8][5] = 11000;
	//Allégade, lyserød
	houseRentPrice2 [9][0] = 150;
	houseRentPrice2 [9][1] = 800;
	houseRentPrice2 [9][2] = 2000;
	houseRentPrice2 [9][3] = 6000;
	houseRentPrice2 [9][4] = 9000;
	houseRentPrice2 [9][5] = 12000;
	//Frederiksberg Allé, grøn
	houseRentPrice2 [11][0] = 200;
	houseRentPrice2 [11][1] = 1000;
	houseRentPrice2 [11][2] = 3000;
	houseRentPrice2 [11][3] = 9000;
	houseRentPrice2 [11][4] = 12500;
	houseRentPrice2 [11][5] = 15000;
	//Büllowsvej, grøn
	houseRentPrice2 [13][0] = 200;
	houseRentPrice2 [13][1] = 1000;
	houseRentPrice2 [13][2] = 3000;
	houseRentPrice2 [13][3] = 9000;
	houseRentPrice2 [13][4] = 12500;
	houseRentPrice2 [13][5] = 15000;
	//GL. Kongevej, grøn
	houseRentPrice2 [14][0] = 250;
	houseRentPrice2 [14][1] = 1250;
	houseRentPrice2 [14][2] = 3750;
	houseRentPrice2 [14][3] = 10000;
	houseRentPrice2 [14][4] = 14000;
	houseRentPrice2 [14][5] = 18000;
	//Bernstorffsvej, grå
	houseRentPrice2 [16][0] = 300;
	houseRentPrice2 [16][1] = 1400;
	houseRentPrice2 [16][2] = 4000;
	houseRentPrice2 [16][3] = 11000;
	houseRentPrice2 [16][4] = 15000;
	houseRentPrice2 [16][5] = 19000;
	//Hellerupvej, grå
	houseRentPrice2 [18][0] = 300;
	houseRentPrice2 [18][1] = 1400;
	houseRentPrice2 [18][2] = 4000;
	houseRentPrice2 [18][3] = 11000;
	houseRentPrice2 [18][4] = 15000;
	houseRentPrice2 [18][5] = 19000;
	//Strandvej, grå
	houseRentPrice2 [19][0] = 550;
	houseRentPrice2 [19][1] = 1600;
	houseRentPrice2 [19][2] = 4400;
	houseRentPrice2 [19][3] = 12000;
	houseRentPrice2 [19][4] = 16000;
	houseRentPrice2 [19][5] = 20000;
	//Trianglen, rød
	houseRentPrice2 [21][0] = 350;
	houseRentPrice2 [21][1] = 1800;
	houseRentPrice2 [21][2] = 5000;
	houseRentPrice2 [21][3] = 14000;
	houseRentPrice2 [21][4] = 17500;
	houseRentPrice2 [21][5] = 21000;
	//Østerbrogade, rød
	houseRentPrice2 [23][0] = 350;
	houseRentPrice2 [23][1] = 1800;
	houseRentPrice2 [23][2] = 5000;
	houseRentPrice2 [23][3] = 14000;
	houseRentPrice2 [23][4] = 17500;
	houseRentPrice2 [23][5] = 21000;
	//Grønningen, rød
	houseRentPrice2 [24][0] = 400;
	houseRentPrice2 [24][1] = 2000;
	houseRentPrice2 [24][2] = 6000;
	houseRentPrice2 [24][3] = 15000;
	houseRentPrice2 [24][4] = 18500;
	houseRentPrice2 [24][5] = 22000;
	//Bredegade, hvid
	houseRentPrice2 [26][0] = 450;
	houseRentPrice2 [26][1] = 2200;
	houseRentPrice2 [26][2] = 6600;
	houseRentPrice2 [26][3] = 16000;
	houseRentPrice2 [26][4] = 19500;
	houseRentPrice2 [26][5] = 23000;
	//Kgs. Nytorv, hvid
	houseRentPrice2 [27][0] = 450;
	houseRentPrice2 [27][1] = 2200;
	houseRentPrice2 [27][2] = 6600;
	houseRentPrice2 [27][3] = 16000;
	houseRentPrice2 [27][4] = 19500;
	houseRentPrice2 [27][5] = 23000;
	//Østergade, hvid
	houseRentPrice2 [29][0] = 500;
	houseRentPrice2 [29][1] = 2400;
	houseRentPrice2 [29][2] = 7200;
	houseRentPrice2 [29][3] = 17000;
	houseRentPrice2 [29][4] = 20500;
	houseRentPrice2 [29][5] = 24000;
	//Amagertorv, gul
	houseRentPrice2 [31][0] = 550;
	houseRentPrice2 [31][1] = 2600;
	houseRentPrice2 [31][2] = 7800;
	houseRentPrice2 [31][3] = 18000;
	houseRentPrice2 [31][4] = 22000;
	houseRentPrice2 [31][5] = 25000;
	//Vimmelskaftet, gul
	houseRentPrice2 [32][0] = 550;
	houseRentPrice2 [32][1] = 2600;
	houseRentPrice2 [32][2] = 7800;
	houseRentPrice2 [32][3] = 18000;
	houseRentPrice2 [32][4] = 22000;
	houseRentPrice2 [32][5] = 25000;
	//Nygade, gul
	houseRentPrice2 [34][0] = 600;
	houseRentPrice2 [34][1] = 3000;
	houseRentPrice2 [34][2] = 9000;
	houseRentPrice2 [34][3] = 20000;
	houseRentPrice2 [34][4] = 24000;
	houseRentPrice2 [34][5] = 28000;
	//Frederiksberggade, lilla
	houseRentPrice2 [37][0] = 700;
	houseRentPrice2 [37][1] = 3500;
	houseRentPrice2 [37][2] = 9000;
	houseRentPrice2 [37][3] = 20000;
	houseRentPrice2 [37][4] = 24000;
	houseRentPrice2 [37][5] = 28000;
	//Rådhuspladsen, lilla
	houseRentPrice2 [39][0] = 1000;
	houseRentPrice2 [39][1] = 4000;
	houseRentPrice2 [39][2] = 12000;
	houseRentPrice2 [39][3] = 28000;
	houseRentPrice2 [39][4] = 34000;
	houseRentPrice2 [39][5] = 40000;
	
	setHouseRentPrice(houseRentPrice2);

}
public static int[][] getHouseRentPrice() {
		return houseRentPrice;
	}
	public static void setHouseRentPrice(int[][] houseRentPrice) {
		HousePrice.houseRentPrice = houseRentPrice;
	}
//	Reads the prices of houses from a text document.
public static void readHousePrice() throws IOException {
	String file = "../HouseAndHotelPrices.txt";
	BufferedReader reader = new BufferedReader(new FileReader(file));
	String [] houseprices;
	houseprices = new String[40];
	int [] tempHousePriceInt;
	tempHousePriceInt = new int[40];

	for (int i = 0; i < 40; i++) {
		String currentLine = reader.readLine();
		houseprices[i] = currentLine;
		//						System.out.print(houseRentPrice[i]);
		tempHousePriceInt[i] = (Integer.parseInt(houseprices[i]));
		//						System.out.println(HousePriceInt[i]);
	}
	reader.close();
	setHousePriceInt(tempHousePriceInt);
}
public static int[] getHousePriceInt() {
	return HousePriceInt;
}
public static void setHousePriceInt(int[] housePriceInt) {
	HousePriceInt = housePriceInt;
}

}
