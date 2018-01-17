package Tests;

import static org.junit.Assert.*;  

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import entity.*;
import controller.*;

public class tripleTurnJailTest {
	TwoDice die1 = new TwoDice();
	TwoDice die2 = new TwoDice();
	private Player player;
	private int sameDice = 0;

	
	@Before
	public void setUp() throws Exception {
		this.player = new Player();
		this.player.setBalance(10);
		this.player.setCurrentField(0);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {		
		int die1 = 1;
		int die2 = 1;
		for(int i = 0; i <= 3; i++) {
			if(die1 == die2) {
				this.sameDice++;
				if(sameDice == 3) {
					player.setJailed(true);
				this.sameDice = 0;
				}
				else 
					break;
			}
			else 
				break;
		}
		
		boolean expected = true;
		boolean actual = player.isJailed();		

		assertEquals(expected, actual);
	}
}


