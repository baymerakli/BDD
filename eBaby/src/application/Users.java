package application;

import java.util.HashMap;
import java.util.Map;

/**
 * @author niralt
 *
 */
public class Users {
	
	private static Map<String, User> users = new HashMap<String, User>();
	
	public Users() {
//		users = new HashMap<String, User>();
	}

	public User findByUserName(String userName) throws Exception {
		User user = users.get(userName);
		if (user == null) {
			throw new Exception("User not found");
		}
		return user;
	}

	public void register(User user) {
		getUsers().put(user.getUserName(), user);
	}

	/**
	 * @return the users
	 */
	public Map<String, User> getUsers() {
		return users;
	}

	public void logIn(String userName, String password) throws Exception {
		User user = findByUserName(userName);
		
		if(!user.getPassword().equals(password)){
			throw new Exception("Wrong Password");
		}
		
		user.setAuthenticated(true);
	}

	public void logOut(String userName) throws Exception {
		User user = findByUserName(userName);
		user.setAuthenticated(false);		
	}

}
