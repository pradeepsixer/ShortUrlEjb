package com.shorturl.ejb.interfaces;

import com.shorturl.datamodel.UserDetails;

/**
 * Local View Interface for User details bean 
 * @author Pradeep Kumar
 */
public interface UserDetailsBeanLocal {
	public static final String JNDI_NAME = "java:global/ShortUrlEar/ShortUrlEjb/UserDetailsBean!com.shorturl.ejb.interfaces.UserDetailsBeanLocal";

	/**
	 * Get the {@link UserDetails User Details} by email id.
	 * @param emailId The email id for which the user details has to be retrieved
	 * @return {@link UserDetails}. If no user details exist for the given email id, then returns null.
	 */
	public UserDetails getUserDetailsByEmailid(String emailId);

	/**
	 * Get the User ID for the given email id
	 * @param emailId The email id for which the user id has to be retrieved
	 * @return The user id, if the user exists with the given email id, or else, -1
	 */
	public long getUserIdByEmailId(String emailId);
}
