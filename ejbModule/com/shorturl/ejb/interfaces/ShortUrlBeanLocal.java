/**
 * 
 */
package com.shorturl.ejb.interfaces;

import com.shorturl.datamodel.ShortUrlDetails;

/**
 * Local Interface for Short URL Bean
 * @author Pradeep Kumar
 */
public interface ShortUrlBeanLocal {
	public static final String JNDI_NAME = "java:global/ShortUrlEar/ShortUrlEjb/ShortUrlBean!com.shorturl.ejb.ShortUrlBean";

	/**
	 * Create or Update the link details
	 * @param linkDetails {@link ShortUrlDetails Short URL Details}
	 */
	public void addOrUpdateShortUrlDetails(final ShortUrlDetails linkDetails);

	/**
	 * Get the {@link ShortUrlDetails Short URL Details} for the given Short URL
	 * @param shortUrl Short URL
	 * @return {@link ShortUrlDetails Short URL Details}
	 */
	public ShortUrlDetails getShortUrlDetails(final String shortUrl);

	/**
	 * Increment the View Count for Short Url
	 * <b>Note:</b> The caller of this method is responsible for synchronization.
	 * @param shortUrl The Short Url for which the view count should be incremented.
	 */
	public void incrementViewCount(final String shortUrl);

	/**
	 * Increment the Click Count for Short Url
	 * <b>Note:</b> The caller of this method is responsible for synchronization.
	 * @param shortUrl The Short Url for which the click count should be incremented.
	 */
	public void incrementClickCount(final String shortUrl);
}
