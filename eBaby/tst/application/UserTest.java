package application;

import static org.junit.Assert.*;

import org.junit.*;

/**
 * 
 */

/**
 * @author niralt
 *
 */
public class UserTest {

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
	public void testUser() {
		
		String firstName = "First";
		String lastName = "last";
		String userEmail = "xyz@yahoo.com";
		String userName = "someone";
		String password = "secret";
		
		User user = new User(firstName, lastName, userEmail, userName, password);
		
		assertEquals(firstName, user.getFirstName());
		assertEquals(lastName, user.getLastName());
		assertEquals(userEmail, user.getUserEmail());
		assertEquals(userName, user.getUserName());
		assertEquals(password, user.getPassword());
		
		
	}

}
