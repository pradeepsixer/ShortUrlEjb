package com.shorturl.ejb;

import com.shorturl.datamodel.UserDetails;
import com.shorturl.ejb.interfaces.UserDetailsBeanLocal;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class UserDetailsBean
 */
@Stateless
@Local(UserDetailsBeanLocal.class)
public class UserDetailsBean implements UserDetailsBeanLocal {

	@PersistenceContext(unitName = "shortUrl-PU")
	private EntityManager em;

	/* (non-Javadoc)
	 * @see com.shorturl.ejb.interfaces.UserDetailsBeanLocal#getUserDetailsByEmailid(java.lang.String)
	 */
	@Override
    public UserDetails getUserDetailsByEmailid(String emailId) {
		List<UserDetails> userDetailsList = em.createNamedQuery(UserDetails.NamedQueries.FIND_BY_EMAILID, UserDetails.class)
				.setParameter(UserDetails.NamedParameters.EMAIL_ID, emailId).getResultList();
		if (userDetailsList.size() > 0) {
			return userDetailsList.get(0);
		}
		return null;
    }

	/* (non-Javadoc)
	 * @see com.shorturl.ejb.interfaces.UserDetailsBeanLocal#getUserIdByEmailId(java.lang.String)
	 */
	@Override
    public long getUserIdByEmailId(String emailId) {
    	List<Long> userIdList = em.createNamedQuery(UserDetails.NamedQueries.FIND_USER_ID_BY_EMAILID, Long.class)
    			.setParameter(UserDetails.NamedParameters.EMAIL_ID, emailId).getResultList();
    	if (userIdList.size() > 0) {
    		return userIdList.get(0);
    	}
    	return -1L;
    }
}
