package entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HousePrice {
	private static int[] HousePriceInt;
	private static int[][] houseRentPrice;
	public static void setupHouse(){
	houseRentPrice = new int [40][5];
	//Rødovervej, blå
	houseRentPrice [1][0] = 50;
	houseRentPrice [1][1] = 250;
	houseRentPrice [1][2] = 750;
	houseRentPrice [1][3] = 2250;
	houseRentPrice [1][4] = 4000;
	houseRentPrice [1][5] = 6000;
	//Hvidovrevej, blå
	houseRentPrice [3][0] = 50;
	houseRentPrice [3][1] = 250;
	houseRentPrice [3][2] = 750;
	houseRentPrice [3][3] = 2250;
	houseRentPrice [3][4] = 4000;
	houseRentPrice [3][5] = 6000;
	//Roskildevej, lyserød
	houseRentPrice [6][0] = 100;
	houseRentPrice [6][1] = 600;
	houseRentPrice [6][2] = 1800;
	houseRentPrice [6][3] = 5400;
	houseRentPrice [6][4] = 8000;
	houseRentPrice [6][5] = 11000;
	//Valby Langgade, lyserød
	houseRentPrice [8][0] = 100;
	houseRentPrice [8][1] = 600;
	houseRentPrice [8][2] = 1800;
	houseRentPrice [8][3] = 5400;
	houseRentPrice [8][4] = 8000;
	houseRentPrice [8][5] = 11000;
	//Allégade, lyserød
	houseRentPrice [9][0] = 150;
	houseRentPrice [9][1] = 800;
	houseRentPrice [9][2] = 2000;
	houseRentPrice [9][3] = 6000;
	houseRentPrice [9][4] = 9000;
	houseRentPrice [9][5] = 12000;
	//Frederiksberg Allé, grøn
	houseRentPrice [11][0] = 200;
	houseRentPrice [11][1] = 1000;
	houseRentPrice [11][2] = 3000;
	houseRentPrice [11][3] = 9000;
	houseRentPrice [11][4] = 12500;
	houseRentPrice [11][5] = 15000;
	//Büllowsvej, grøn
	houseRentPrice [13][0] = 200;
	houseRentPrice [13][1] = 1000;
	houseRentPrice [13][2] = 3000;
	houseRentPrice [13][3] = 9000;
	houseRentPrice [13][4] = 12500;
	houseRentPrice [13][5] = 15000;
	//GL. Kongevej, grøn
	houseRentPrice [14][0] = 250;
	houseRentPrice [14][1] = 1250;
	houseRentPrice [14][2] = 3750;
	houseRentPrice [14][3] = 10000;
	houseRentPrice [14][4] = 14000;
	houseRentPrice [14][5] = 18000;
	//Bernstorffsvej, grå
	houseRentPrice [16][0] = 300;
	houseRentPrice [16][1] = 1400;
	houseRentPrice [16][2] = 4000;
	houseRentPrice [16][3] = 11000;
	houseRentPrice [16][4] = 15000;
	houseRentPrice [16][5] = 19000;
	//Hellerupvej, grå
	houseRentPrice [18][0] = 300;
	houseRentPrice [18][1] = 1400;
	houseRentPrice [18][2] = 4000;
	houseRentPrice [18][3] = 11000;
	houseRentPrice [18][4] = 15000;
	houseRentPrice [18][5] = 19000;
	//Strandvej, grå
	houseRentPrice [19][0] = 550;
	houseRentPrice [19][1] = 1600;
	houseRentPrice [19][2] = 4400;
	houseRentPrice [19][3] = 12000;
	houseRentPrice [19][4] = 16000;
	houseRentPrice [19][5] = 20000;
	//Trianglen, rød
	houseRentPrice [21][0] = 350;
	houseRentPrice [21][1] = 1800;
	houseRentPrice [21][2] = 5000;
	houseRentPrice [21][3] = 14000;
	houseRentPrice [21][4] = 17500;
	houseRentPrice [21][5] = 21000;
	//Østerbrogade, rød
	houseRentPrice [23][0] = 350;
	houseRentPrice [23][1] = 1800;
	houseRentPrice [23][2] = 5000;
	houseRentPrice [23][3] = 14000;
	houseRentPrice [23][4] = 17500;
	houseRentPrice [23][5] = 21000;
	//Grønningen, rød
	houseRentPrice [24][0] = 400;
	houseRentPrice [24][1] = 2000;
	houseRentPrice [24][2] = 6000;
	houseRentPrice [24][3] = 15000;
	houseRentPrice [24][4] = 18500;
	houseRentPrice [24][5] = 22000;
	//Bredegade, hvid
	houseRentPrice [26][0] = 450;
	houseRentPrice [26][1] = 2200;
	houseRentPrice [26][2] = 6600;
	houseRentPrice [26][3] = 16000;
	houseRentPrice [26][4] = 19500;
	houseRentPrice [26][5] = 23000;
	//Kgs. Nytorv, hvid
	houseRentPrice [27][0] = 450;
	houseRentPrice [27][1] = 2200;
	houseRentPrice [27][2] = 6600;
	houseRentPrice [27][3] = 16000;
	houseRentPrice [27][4] = 19500;
	houseRentPrice [27][5] = 23000;
	//Østergade, hvid
	houseRentPrice [29][0] = 500;
	houseRentPrice [29][1] = 2400;
	houseRentPrice [29][2] = 7200;
	houseRentPrice [29][3] = 17000;
	houseRentPrice [29][4] = 20500;
	houseRentPrice [29][5] = 24000;
	//Amagertorv, gul
	houseRentPrice [31][0] = 550;
	houseRentPrice [31][1] = 2600;
	houseRentPrice [31][2] = 7800;
	houseRentPrice [31][3] = 18000;
	houseRentPrice [31][4] = 22000;
	houseRentPrice [31][5] = 25000;
	//Vimmelskaftet, gul
	houseRentPrice [32][0] = 550;
	houseRentPrice [32][1] = 2600;
	houseRentPrice [32][2] = 7800;
	houseRentPrice [32][3] = 18000;
	houseRentPrice [32][4] = 22000;
	houseRentPrice [32][5] = 25000;
	//Nygade, gul
	houseRentPrice [34][0] = 600;
	houseRentPrice [34][1] = 3000;
	houseRentPrice [34][2] = 9000;
	houseRentPrice [34][3] = 20000;
	houseRentPrice [34][4] = 24000;
	houseRentPrice [34][5] = 28000;
	//Frederiksberggade, lilla
	houseRentPrice [37][0] = 700;
	houseRentPrice [37][1] = 3500;
	houseRentPrice [37][2] = 9000;
	houseRentPrice [37][3] = 20000;
	houseRentPrice [37][4] = 24000;
	houseRentPrice [37][5] = 28000;
	//Rådhuspladsen, lilla
	houseRentPrice [39][0] = 1000;
	houseRentPrice [39][1] = 4000;
	houseRentPrice [39][2] = 12000;
	houseRentPrice [39][3] = 28000;
	houseRentPrice [39][4] = 34000;
	houseRentPrice [39][5] = 40000;

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
