package Tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entity.AccountBalance;

public class J_U_AccountBalanceTest {
	AccountBalance ac;
	@Before
	public void setUp() throws Exception {
		ac=new AccountBalance(1000);
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void test() {
		ac.newBalance(-2000);
		int Expected = 0;
		int Actual = ac.getBalance();
		Assert.assertEquals(Expected,Actual);
	}

	@Test
	public void test2() {
		ac.newBalance(-200);
		int Expected = 800;
		int Actual = ac.getBalance();
		Assert.assertEquals(Expected,Actual);
	}
	@Test
	public void test3() {
		ac.newBalance(-1000);
		int Expected = 0;
		int Actual = ac.getBalance();
		Assert.assertEquals(Expected,Actual);
	}
	@Test
	public void test4() {
		ac.newBalance(1000);
		int Expected = 2000;
		int Actual = ac.getBalance();
		Assert.assertEquals(Expected,Actual);
	}
	@Test
	public void test5() {
		ac.newBalance(-0);
		int Expected = 1000;
		int Actual = ac.getBalance();
		Assert.assertEquals(Expected,Actual);
	}
	@Test
	public void test6() {
		ac.newBalance(Integer.MIN_VALUE);
		int Expected = 0;
		int Actual = ac.getBalance();
		Assert.assertEquals(Expected,Actual);
	}
	@Test
	public void test7() {
		ac.setBalance(0);
		ac.newBalance(Integer.MAX_VALUE);
		int Expected = Integer.MAX_VALUE;
		int Actual = ac.getBalance();
		Assert.assertEquals(Expected,Actual);
	}
	@Test
	public void test8() {
		ac.setBalance(2999);
		int Expected = 2999;
		int Actual = ac.getBalance();
		Assert.assertEquals(Expected,Actual);
	}
	
	
}