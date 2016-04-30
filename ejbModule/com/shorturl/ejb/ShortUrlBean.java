package com.shorturl.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.shorturl.datamodel.ShortUrlDetails;
import com.shorturl.datamodel.ShortUrlDetails.NamedParameters;
import com.shorturl.datamodel.ShortUrlDetails.NamedQueries;
import com.shorturl.ejb.interfaces.ShortUrlBeanLocal;

/**
 * Session Bean implementation class ShortUrlBean
 */
@Stateless
@Local
public class ShortUrlBean implements ShortUrlBeanLocal {

	@PersistenceContext(unitName = "shortUrl-PU")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public ShortUrlBean() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shorturl.ejb.interfaces.ShortUrlBeanLocal#addLinkDetails()
	 */
	@Override
	public ShortUrlDetails addOrUpdateShortUrlDetails(final ShortUrlDetails linkDetails) {
		return em.merge(linkDetails);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shorturl.ejb.interfaces.ShortUrlBeanLocal#getShortUrlDetails(java.
	 * lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public ShortUrlDetails getShortUrlDetails(String shortUrl) {
		List<ShortUrlDetails> resultList = (List<ShortUrlDetails>) em.createNamedQuery(NamedQueries.FIND_BY_SHORTURL).setParameter(NamedParameters.SHORT_URL, shortUrl).getResultList();
		if (resultList.size() > 0) {
			return resultList.get(0);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shorturl.ejb.interfaces.ShortUrlBeanLocal#incrementViewCount(java.
	 * lang.String)
	 */
	@Override
	public void incrementViewCount(String shortUrl) {
		em.createNamedQuery(NamedQueries.INCREMENT_VIEW_COUNT)
			.setParameter(NamedParameters.SHORT_URL, shortUrl).executeUpdate();
	}

	/* (non-Javadoc)
	 * @see com.shorturl.ejb.interfaces.ShortUrlBeanLocal#incrementClickCount(java.lang.String)
	 */
	@Override
	public void incrementClickCount(String shortUrl) {
		em.createNamedQuery(NamedQueries.INCREMENT_ACTION_COMPLETED_COUNT)
			.setParameter(NamedParameters.SHORT_URL, shortUrl).executeUpdate();
	}
}
