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
public class PostOfficeTest {

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
	public void testSendEmail() {
		String address = "test@test.com";
		String message = "Test Message";
		
		PostOffice postOffice = PostOffice.getInstance();
		postOffice.sendEMail(address, message);
		
		assertTrue(postOffice.doesLogContain(address, message));
		assertEquals("<sendEMail address=\"".concat(address).concat("\" >").concat(message)
				.concat("</sendEmail>\n"), postOffice.findEmail(address, message));
	}

}
