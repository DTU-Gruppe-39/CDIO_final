package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entity.TwoDice;

public class J_U_TwoDiceTest {

	@Before
	public void setUp() throws Exception {


	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		int[] c= {0,0,0,0,0,0,0,0,0,0,0};
		int numbrolls = 60000;
		TwoDice dice = new TwoDice();

		for(int i=0; i <= numbrolls; i++) {
			dice.roll();
			int værdi = dice.getDiceTotal();
			switch(værdi) {
			case 2: c[0]++;
			break;
			case 3: c[1]++;
			break;
			case 4: c[2]++;
			break;
			case 5: c[3]++;
			break;
			case 6: c[4]++;
			break;
			case 7: c[5]++;
			break;
			case 8: c[6]++;
			break;
			case 9: c[7]++;
			break;
			case 10: c[8]++;
			break;
			case 11: c[9]++;
			break;
			case 12: c[10]++;
			break;
			}
		}

		int[] d= {0,0,0,0,0,0,0,0,0,0,0};
		d[0] = numbrolls*1/36;
		d[1] = numbrolls*2/36;
		d[2] = numbrolls*3/36;
		d[3] = numbrolls*4/36;
		d[4] = numbrolls*5/36;
		d[5] = numbrolls*6/36;
		d[6] = numbrolls*5/36;
		d[7] = numbrolls*4/36;
		d[8] = numbrolls*3/36;
		d[9] = numbrolls*2/36;
		d[10] = numbrolls*1/36;


		int expect = numbrolls/36;
		for(int i= 0; i<=d.length-1;i++) {
			int diff = c[i] - d[i];

			boolean expected = true;
			boolean actual = (Math.abs(diff) < 400);
			assertEquals(expected, actual);
			System.out.println(c[i]);
		}

	}

}