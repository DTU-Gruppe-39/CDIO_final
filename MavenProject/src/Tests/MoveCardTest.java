package Tests;

import static org.junit.Assert.*; 

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Chancecards.Chancecard;
import Chancecards.MoveCard;
import gamelogic.*;
import entity.*;

public class MoveCardTest {
	private Player player;
	private Chancecard[] cards;


	
	@Before
	public void setUp() throws Exception {
	this.cards = new Chancecard[1];
	this.cards[0] = new MoveCard("Chance21", 30);
	this.player = new Player();
	this.player.setCurrentField(2);
	}

	
	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void test() {
		Chancecard draw = this.cards[0];
		this.player.setCurrentField(((MoveCard)draw).getAmount());
		int expected = 30;
		int actual = this.player.getCurrentField();		

		assertEquals(expected, actual);
	}

}
