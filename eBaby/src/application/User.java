package application;

/**
 * @author niralt
 *
 */
public class User {
	
	private String firstName;
	private String lastName;
	private String userEmail;
	private String userName;
	private String password;
	private Boolean authenticated;

	public User(String firstName, String lastName, String userEmail,
			String userName, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userEmail = userEmail;
		this.userName = userName;
		this.password = password;
		this.setAuthenticated(false);
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the authenticated
	 */
	public Boolean getAuthenticated() {
		return authenticated;
	}

	/**
	 * @param authenticated the authenticated to set
	 */
	public void setAuthenticated(Boolean authenticated) {
		this.authenticated = authenticated;
	}


}
