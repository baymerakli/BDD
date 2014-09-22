package application;

import static org.junit.Assert.*;
import org.junit.*;


public class UsersTest {

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
	public void testRegisteredUser() throws Exception{
		String firstName = "First";
		String lastName = "last";
		String userEmail = "xyz@yahoo.com";
		String userName = "someone";
		String password = "secret";
		
		User user = new User(firstName, lastName, userEmail, userName, password);

		Users users = new Users();
		
		users.register(user);
		
		User registered = users.findByUserName(userName);
		assertEquals(firstName, registered.getFirstName());		
	}
	
	@Test
	public void testUnRegisteredUser() throws Exception{
		String userName = "fake";

		Users users = new Users();
		
		try{
			users.findByUserName(userName);
		} catch(Exception e) {
			assertEquals("User not found", e.getMessage());
		}		
	}

	@Test
	public void testLogInValidUser() throws Exception {
		String firstName = "First";
		String lastName = "last";
		String userEmail = "xyz@yahoo.com";
		String userName = "someone";
		String password = "secret";
		
		User user = new User(firstName, lastName, userEmail, userName, password);

		Users users = new Users();
		
		users.register(user);
		
		users.logIn(userName, password);
		assertTrue(user.getAuthenticated());
	}
	
	@Test
	public void testLogInInvalidUser() {
		String userName = "someone";
		String password = "secret";
		
		Users users = new Users();
		
		try{
			users.logIn(userName, password);
		} catch(Exception e) {
			assertEquals("User not found", e.getMessage());
		}		
	}
	
	@Test
	public void testLogInInvalidPassword() {
		String firstName = "First";
		String lastName = "last";
		String userEmail = "xyz@yahoo.com";
		String userName = "someone";
		String password = "secret";
		
		User user = new User(firstName, lastName, userEmail, userName, password);

		Users users = new Users();
		
		users.register(user);
		
		try {
			users.logIn(userName, "wrong pass");
		} catch(Exception e){
			assertEquals("Wrong Password", e.getMessage());
		}
		
		assertFalse(user.getAuthenticated());
	}
	
	@Test
	public void testLogOutValidUser() throws Exception {
		String firstName = "First";
		String lastName = "last";
		String userEmail = "xyz@yahoo.com";
		String userName = "someone";
		String password = "secret";
		
		User user = new User(firstName, lastName, userEmail, userName, password);
		Users users = new Users();
		users.register(user);
		
		users.logIn(userName, password);
		assertTrue(user.getAuthenticated());
		
		users.logOut(userName);
		assertFalse(user.getAuthenticated());
	}
	
	@Test
	public void testLogOutInvalidUser() {
		
		Users users = new Users();
		
		try {
			users.logOut("fakeUser");			
		} catch (Exception e){
			assertEquals("User not found", e.getMessage());
		}
	}
}
