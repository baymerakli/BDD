/**
 * 
 */
package application;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author niralt
 *
 */
public class AuctionLoggerTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAuctionLog() {
		String fileName = "testfile";
		String message = "test message";
		
		AuctionLogger logger = AuctionLogger.getInstance();
		
		logger.log(fileName, message);
		assertTrue(logger.findMessage(fileName, message));
		assertEquals(message, logger.returnMessage(fileName, message));
	}
}
