package application;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.*;

public class AuctionTest {

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
	
	private User createAuthenticatedUser(String userType) throws Exception {
		String firstName;
		String lastName;
		String userEmail;
		String userName;
		String password;
		
		if (userType.equals("seller")) {
			firstName = "sellerfirst";
			lastName = "sellerlast";
			userEmail = "seller@yahoo.com";
			userName = "seller";
			password = "sellersecret";
		} else {
			firstName = "bidderfirst";
			lastName = "bidderlast";
			userEmail = "bidder@yahoo.com";
			userName = "bidder";
			password = "biddersecret";
		}
		
		User user = new User(firstName, lastName, userEmail, userName, password);
		Users users = new Users();
		users.register(user);
		users.logIn(userName, password);
		
		return user;
	}
	
	private Auction createAuction(User seller, String itemName, String itemCategory) throws Exception {
		return new Auction(seller.getUserName(), itemName, itemCategory);
	}

	@Test
	public void testAuctionLoggedInSeller() throws Exception {
		User seller = createAuthenticatedUser("seller");
		Auction auction = createAuction(seller, "Crazy Item Name", "");
		assertEquals(seller, auction.getSeller());
	}
	
	@Test
	public void testAuctionNotLoggedInSeller() {
		String firstName = "First";
		String lastName = "last";
		String userEmail = "xyz@yahoo.com";
		String userName = "someone";
		String password = "secret";
		
		User user = new User(firstName, lastName, userEmail, userName, password);
		Users users = new Users();
		users.register(user);
		
		try {
			new Auction(userName,"","");
		} catch (Exception e) {
			assertEquals("User not logged in", e.getMessage());
		}
	}
	
	@Test
	public void testAuctionOnStart() throws Exception {
		String firstName = "First";
		String lastName = "last";
		String userEmail = "xyz@yahoo.com";
		String userName = "someone";
		String password = "secret";
		
		User user = new User(firstName, lastName, userEmail, userName, password);
		Users users = new Users();
		users.register(user);
		users.logIn(userName, password);
		
		Auction auction = new Auction(userName,"","");
		
		auction.onStart();
		assertEquals("STARTED", auction.getState()); 
	}
	
	@Test
	public void testAuctionOnClose() throws Exception {
		String firstName = "First";
		String lastName = "last";
		String userEmail = "xyz@yahoo.com";
		String userName = "someone";
		String password = "secret";
		
		User user = new User(firstName, lastName, userEmail, userName, password);
		Users users = new Users();
		users.register(user);
		users.logIn(userName, password);
		
		Auction auction = new Auction(userName,"","");
		
		auction.onClose();
		assertEquals("CLOSED", auction.getState()); 
	}
	
	@Test
	public void testBidding() throws Exception {
		String sellerFirstName = "sellerfirst";
		String sellerLastName = "sellerlast";
		String sellerUserEmail = "seller@yahoo.com";
		String sellerUserName = "seller";
		String sellerPassword = "sellersecret";
		
		User seller = new User(sellerFirstName, sellerLastName, sellerUserEmail, sellerUserName, sellerPassword);
		Users users = new Users();
		users.register(seller);
		users.logIn(sellerUserName, sellerPassword);
		
		String bidderFirstName = "bidderfirst";
		String bidderLastName = "bidderlast";
		String bidderUserEmail = "bidder@yahoo.com";
		String bidderUserName = "bidder";
		String bidderPassword = "biddersecret";
		int bidPrice = 10;
		
		User bidder = new User(bidderFirstName, bidderLastName, bidderUserEmail, bidderUserName, bidderPassword);
		users.register(bidder);
		users.logIn(bidderUserName, bidderPassword);

		Auction auction = new Auction(sellerUserName,"","");
		auction.onStart();
		auction.bid(bidder, bidPrice);
		LinkedList<Bid> bids = auction.getBids();
		Bid lastBid = bids.getLast();
		assertEquals(bidderUserName, lastBid.getBidderName());
		assertEquals(bidPrice, lastBid.getPrice());
	}
	
	@Test
	public void testAuthenticateToBid() throws Exception {
		String sellerFirstName = "sellerfirst";
		String sellerLastName = "sellerlast";
		String sellerUserEmail = "seller@yahoo.com";
		String sellerUserName = "seller";
		String sellerPassword = "sellersecret";
		
		User seller = new User(sellerFirstName, sellerLastName, sellerUserEmail, sellerUserName, sellerPassword);
		Users users = new Users();
		users.register(seller);
		users.logIn(sellerUserName, sellerPassword);
		
		String bidderFirstName = "bidderfirst";
		String bidderLastName = "bidderlast";
		String bidderUserEmail = "bidder@yahoo.com";
		String bidderUserName = "bidder";
		String bidderPassword = "biddersecret";
		int bidPrice = 10;
		
		User bidder = new User(bidderFirstName, bidderLastName, bidderUserEmail, bidderUserName, bidderPassword);
		users.register(bidder);

		Auction auction = new Auction(sellerUserName,"","");
		auction.onStart();
		try {
			auction.bid(bidder, bidPrice);
		} catch (Exception e) {
			assertEquals("User not logged in", e.getMessage());
		}
	}
	
	@Test
	public void testAuctionNotStartedCantAcceptBids() throws Exception {
		String sellerFirstName = "sellerfirst";
		String sellerLastName = "sellerlast";
		String sellerUserEmail = "seller@yahoo.com";
		String sellerUserName = "seller";
		String sellerPassword = "sellersecret";
		
		User seller = new User(sellerFirstName, sellerLastName, sellerUserEmail, sellerUserName, sellerPassword);
		Users users = new Users();
		users.register(seller);
		users.logIn(sellerUserName, sellerPassword);
		
		String bidderFirstName = "bidderfirst";
		String bidderLastName = "bidderlast";
		String bidderUserEmail = "bidder@yahoo.com";
		String bidderUserName = "bidder";
		String bidderPassword = "biddersecret";
		int bidPrice = 10;
		
		User bidder = new User(bidderFirstName, bidderLastName, bidderUserEmail, bidderUserName, bidderPassword);
		users.register(bidder);
		users.logIn(bidderUserName, bidderPassword);
		
		Auction auction = new Auction(sellerUserName,"","");

		try{			
			auction.bid(bidder, bidPrice);
		} catch (Exception e){
			assertEquals("Auction NOT Started or Closed", e.getMessage());
		}
	}
	
	@Test
	public void testLowerBidDoesntBecomeHighBid() throws Exception {
		String sellerFirstName = "sellerfirst";
		String sellerLastName = "sellerlast";
		String sellerUserEmail = "seller@yahoo.com";
		String sellerUserName = "seller";
		String sellerPassword = "sellersecret";
		
		User seller = new User(sellerFirstName, sellerLastName, sellerUserEmail, sellerUserName, sellerPassword);
		Users users = new Users();
		users.register(seller);
		users.logIn(sellerUserName, sellerPassword);
		
		String bidderFirstName = "bidderfirst";
		String bidderLastName = "bidderlast";
		String bidderUserEmail = "bidder@yahoo.com";
		String bidderUserName = "bidder";
		String bidderPassword = "biddersecret";
		int lowPrice = 10;
		int highPrice = 20;
		
		User bidder = new User(bidderFirstName, bidderLastName, bidderUserEmail, bidderUserName, bidderPassword);
		users.register(bidder);
		users.logIn(bidderUserName, bidderPassword);
		
		Auction auction = new Auction(sellerUserName,"","");

		auction.onStart();
		auction.bid(bidder, highPrice);
		try{
			auction.bid(bidder, lowPrice);
		} catch (Exception e){
			assertEquals("Bid price must be higher then the current price", e.getMessage());
		}
	}
	
	@Test
	public void testHigerBidBecomesHighBid() throws Exception {
		String sellerFirstName = "sellerfirst";
		String sellerLastName = "sellerlast";
		String sellerUserEmail = "seller@yahoo.com";
		String sellerUserName = "seller";
		String sellerPassword = "sellersecret";
		
		User seller = new User(sellerFirstName, sellerLastName, sellerUserEmail, sellerUserName, sellerPassword);
		Users users = new Users();
		users.register(seller);
		users.logIn(sellerUserName, sellerPassword);
		
		String bidderFirstName = "bidderfirst";
		String bidderLastName = "bidderlast";
		String bidderUserEmail = "bidder@yahoo.com";
		String bidderUserName = "bidder";
		String bidderPassword = "biddersecret";
		int lowPrice = 10;
		int highPrice = 20;
		
		User bidder = new User(bidderFirstName, bidderLastName, bidderUserEmail, bidderUserName, bidderPassword);
		users.register(bidder);
		users.logIn(bidderUserName, bidderPassword);
		
		Auction auction = new Auction(sellerUserName,"","");

		auction.onStart();
		auction.bid(bidder, lowPrice);
		auction.bid(bidder, highPrice);
		LinkedList<Bid> bids = auction.getBids();
		
		assertEquals(2, bids.size());
		assertEquals(highPrice, bids.getLast().getPrice());
		assertEquals(lowPrice, bids.getFirst().getPrice());
	}
	
	@Test
	public void testAuctionClosedNoBidder() throws Exception {
		String sellerFirstName = "sellerfirst";
		String sellerLastName = "sellerlast";
		String sellerUserEmail = "seller@yahoo.com";
		String sellerUserName = "seller";
		String sellerPassword = "sellersecret";
		
		User seller = new User(sellerFirstName, sellerLastName, sellerUserEmail, sellerUserName, sellerPassword);
		Users users = new Users();
		users.register(seller);
		users.logIn(sellerUserName, sellerPassword);
		
		String itemName = "Crazy item name";
		
		Auction auction = new Auction(sellerUserName, itemName,"");

		auction.onStart();
		auction.onClose();
		
		PostOffice postoffice = PostOffice.getInstance();
		assertTrue(postoffice.doesLogContain(
				sellerUserEmail, "Sorry, your auction for ".concat(itemName).concat(" did not have any bidders.")));
		//assertEquals(1, postoffice.size());
	}
	
	@Test
	public void testAuctionClosedWithBidder() throws Exception {
		String sellerFirstName = "sellerfirst";
		String sellerLastName = "sellerlast";
		String sellerUserEmail = "seller@yahoo.com";
		String sellerUserName = "seller";
		String sellerPassword = "sellersecret";
		
		User seller = new User(sellerFirstName, sellerLastName, sellerUserEmail, sellerUserName, sellerPassword);
		Users users = new Users();
		users.register(seller);
		users.logIn(sellerUserName, sellerPassword);
		
		String bidderFirstName = "bidderfirst";
		String bidderLastName = "bidderlast";
		String bidderUserEmail = "bidder@yahoo.com";
		String bidderUserName = "bidder";
		String bidderPassword = "biddersecret";
		
		int lowPrice = 10;
		int highPrice = 20;
		
		User bidder = new User(bidderFirstName, bidderLastName, bidderUserEmail, bidderUserName, bidderPassword);
		users.register(bidder);
		users.logIn(bidderUserName, bidderPassword);
		
		String itemName = "Crazy item name";
		
		Auction auction = new Auction(sellerUserName,itemName,"");

		auction.onStart();
		auction.bid(bidder, lowPrice);
		auction.bid(bidder, highPrice);
		auction.onClose();
		
		PostOffice postoffice = PostOffice.getInstance();
		assertTrue(postoffice.doesLogContain(
				sellerUserEmail, "Your ".concat(itemName).concat(" auction sold to bidder ").concat(bidderUserEmail).concat(" for ") + highPrice + "."));
		assertTrue(postoffice.doesLogContain(
				bidderUserEmail, "Congratulations! You won an auction for a ".concat(itemName).concat(" from ").concat(sellerUserEmail).concat(" for ") + highPrice + "."));
	}
	
	@Test
	public void testAddFeesOnOtherCategory() throws Exception {
		User bidder = createAuthenticatedUser("bidder");
		User seller = createAuthenticatedUser("seller");
		int price = 100;
		String itemCategory = "something";
		String itemName = "Transaction fee auction";
		Auction auction = createAuction(seller, itemName,itemCategory);
		auction.onStart();
		auction.bid(bidder, price);
		auction.onClose();
		assertEquals(price * 1.02 + 10, auction.getTotalPrice(), .02);
	}
	
	@Test
	public void testAddFeesOnDownloadableSoftware() throws Exception {
		User bidder = createAuthenticatedUser("bidder");
		User seller = createAuthenticatedUser("seller");
		int price = 100;
		String itemCategory = "Downloadable Software";
		String itemName = "Transaction fee auction";
		Auction auction = createAuction(seller, itemName, itemCategory);
		auction.onStart();
		auction.bid(bidder, price);
		auction.onClose();
		assertEquals(price * 1.02, auction.getTotalPrice(), .02);
	}
	
	@Test
	public void testAddFeesOnCar() throws Exception {
		User bidder = createAuthenticatedUser("bidder");
		User seller = createAuthenticatedUser("seller");
		int price = 100;
		String itemCategory = "car";
		String itemName = "Transaction fee auction";
		Auction auction = createAuction(seller, itemName, itemCategory);
		auction.onStart();
		auction.bid(bidder, price);
		auction.onClose();
		assertEquals(price * 1.02 + 1000, auction.getTotalPrice(), .02);
	}
	
	@Test
	public void testAddFeesOnLuxuryCar() throws Exception {
		User bidder = createAuthenticatedUser("bidder");
		User seller = createAuthenticatedUser("seller");
		int price = 50000;
		String itemCategory = "car";
		String itemName = "Transaction fee auction";
		Auction auction = createAuction(seller, itemName, itemCategory);
		auction.onStart();
		auction.bid(bidder, price);
		auction.onClose();
		assertEquals(price + (price * .02) + (price * .04) + 1000, auction.getTotalPrice(), .02);
	}
	
	@Test
	public void testLogAllCarSales() throws Exception {
		AuctionLogger logger = AuctionLogger.getInstance();
		String logFileName = "/tmp/carSales";
		logger.clearLog(logFileName);
		
		User bidder = createAuthenticatedUser("bidder");
		User seller = createAuthenticatedUser("seller");
		int firstCarPrice = 50000;
		int secondCarPrice = 10000;
		
		String itemCategory = "car";
		String itemName1 = "Expensive";
		String itemName2 = "Cheap";
		
		Auction auction1 = createAuction(seller, itemName1, itemCategory);
		Auction auction2 = createAuction(seller, itemName2, itemCategory);
		auction1.onStart();
		auction2.onStart();
		auction1.bid(bidder, firstCarPrice);
		auction2.bid(bidder, secondCarPrice);
		auction1.onClose();
		auction2.onClose();
		
		assertTrue(logger.findMessage(logFileName, "Auction ".concat(itemName1).concat(" sold a car")));
		assertTrue(logger.findMessage(logFileName, "Auction ".concat(itemName2).concat(" sold a car")));
	}
	
	@Test
	public void testLogSalesOver10k() throws Exception {		
		AuctionLogger logger = AuctionLogger.getInstance();
		String logFileName = "/tmp/salesOver10k";
		logger.clearLog(logFileName);
		
		User bidder = createAuthenticatedUser("bidder");
		User seller = createAuthenticatedUser("seller");
		int firstPrice = 10000;
		int secondPrice = 100;
		
		String itemCategory1 = "car";
		String itemCategory2 = "drinks";
		String itemName1 = "Expensive";
		String itemName2 = "Cheap";
		
		Auction auction1 = createAuction(seller, itemName1, itemCategory1);
		Auction auction2 = createAuction(seller, itemName2, itemCategory2);
		auction1.onStart();
		auction2.onStart();
		auction1.bid(bidder, firstPrice);
		auction2.bid(bidder, secondPrice);
		auction1.onClose();
		auction2.onClose();
		assertTrue(logger.findMessage(logFileName,
				"Auction ".concat(itemName1).concat(" sold over 10k")));
		assertFalse(logger.findMessage(logFileName,
				"Auction ".concat(itemName2).concat(" sold over 10k")));
	}
	
	@Test
	public void testOffHoursLogging() throws Exception {		
		AuctionLogger logger = AuctionLogger.getInstance();
		String logFileName = "/tmp/offHours";
		logger.clearLog(logFileName);
		
		User bidder = createAuthenticatedUser("bidder");
		User seller = createAuthenticatedUser("seller");
		int firstPrice = 10000;
		int secondPrice = 100;
		
		String itemCategory1 = "car";
		String itemCategory2 = "drinks";
		String itemName1 = "Expensive";
		String itemName2 = "Cheap";
		
		Auction auction1 = createAuction(seller, itemName1, itemCategory1);
		Auction auction2 = createAuction(seller, itemName2, itemCategory2);
		auction1.onStart();
		auction2.onStart();
		auction1.bid(bidder, firstPrice);
		auction2.bid(bidder, secondPrice);
		OffHoursFactory.setMock(true);
		OffHoursFactory.setIsOffHours(true);
		auction1.onClose();
		OffHoursFactory.setIsOffHours(false);
		auction2.onClose();
		OffHoursFactory.setMock(false);
		
		assertTrue(logger.findMessage(logFileName,
				"Auction ".concat(itemName1).concat(" sold during off hours")));
		assertFalse(logger.findMessage(logFileName,
				"Auction ".concat(itemName2).concat(" sold during off hours")));
	}
	
	/*private class TestableOffHours implements Hours {

		@Override
		public boolean isOffHours() {
			return false;
		}
		
		
	}
	
	@Test
	public void testOffHoursLoggingShunt() throws Exception {
		//OffHours testOffHours = OffHours.getInstance();
		TestableOffHours testableOffHours = new TestableOffHours();
		
		
		
		
		
		AuctionLogger logger = AuctionLogger.getInstance();
		String logFileName = "/tmp/offHours";
		logger.clearLog(logFileName);
		
		User bidder = createAuthenticatedUser("bidder");
		User seller = createAuthenticatedUser("seller");
		int firstPrice = 10000;
		int secondPrice = 100;
		
		String itemCategory1 = "car";
		String itemCategory2 = "drinks";
		String itemName1 = "Expensive";
		String itemName2 = "Cheap";
		
		Auction auction1 = createAuction(seller, itemName1, itemCategory1);
		Auction auction2 = createAuction(seller, itemName2, itemCategory2);
		auction1.onStart();
		auction2.onStart();
		auction1.bid(bidder, firstPrice);
		auction2.bid(bidder, secondPrice);
		OffHoursFactory.setMock(true);
		auction1.onClose();
		OffHoursFactory.setMock(false);
		auction2.onClose();
		
		assertTrue(logger.findMessage(logFileName,
				"Auction ".concat(itemName1).concat(" sold during off hours")));
		assertFalse(logger.findMessage(logFileName,
				"Auction ".concat(itemName2).concat(" sold during off hours")));
	}*/
}
