package com.shorturl.ejb;

import static com.shorturl.datamodel.ShortUrlDetails.*;

import com.shorturl.datamodel.ShortUrlDetails;
import com.shorturl.ejb.interfaces.ShortUrlBeanLocal;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class ShortUrlBean
 */
@Stateless
@LocalBean
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
	public void addOrUpdateShortUrlDetails(final ShortUrlDetails linkDetails) {
		em.merge(linkDetails);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shorturl.ejb.interfaces.ShortUrlBeanLocal#getShortUrlDetails(java.
	 * lang.String)
	 */
	@Override
	public ShortUrlDetails getShortUrlDetails(String shortUrl) {
		return em.find(ShortUrlDetails.class, shortUrl);
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
		em.createNamedQuery(NamedQueries.INCREMENT_CLICK_COUNT)
			.setParameter(NamedParameters.SHORT_URL, shortUrl).executeUpdate();
	}
}
