package application;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class BidTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBid() throws Exception {
		String bidderFirstName = "bidderfirst";
		String bidderLastName = "bidderlast";
		String bidderUserEmail = "bidder@yahoo.com";
		String bidderUserName = "bidder";
		String bidderPassword = "biddersecret";
		
		User bidder = new User(bidderFirstName, bidderLastName, bidderUserEmail, bidderUserName, bidderPassword);
		Users users = new Users();
		users.register(bidder);
		users.logIn(bidderUserName, bidderPassword);
		
		int price = 15;
		
		Bid bid = new Bid(bidderUserName, price);
		assertEquals(price, bid.getPrice());
		assertEquals(bidderUserName, bid.getBidderName());
	}

}
