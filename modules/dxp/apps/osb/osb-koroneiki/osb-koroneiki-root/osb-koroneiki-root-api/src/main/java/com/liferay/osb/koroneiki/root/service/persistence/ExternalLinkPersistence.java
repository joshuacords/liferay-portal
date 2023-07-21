/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.service.persistence;

import com.liferay.osb.koroneiki.root.exception.NoSuchExternalLinkException;
import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the external link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExternalLinkUtil
 * @generated
 */
@ProviderType
public interface ExternalLinkPersistence extends BasePersistence<ExternalLink> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ExternalLinkUtil} to access the external link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the external link where externalLinkKey = &#63; or throws a <code>NoSuchExternalLinkException</code> if it could not be found.
	 *
	 * @param externalLinkKey the external link key
	 * @return the matching external link
	 * @throws NoSuchExternalLinkException if a matching external link could not be found
	 */
	public ExternalLink findByExternalLinkKey(String externalLinkKey)
		throws NoSuchExternalLinkException;

	/**
	 * Returns the external link where externalLinkKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param externalLinkKey the external link key
	 * @return the matching external link, or <code>null</code> if a matching external link could not be found
	 */
	public ExternalLink fetchByExternalLinkKey(String externalLinkKey);

	/**
	 * Returns the external link where externalLinkKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param externalLinkKey the external link key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching external link, or <code>null</code> if a matching external link could not be found
	 */
	public ExternalLink fetchByExternalLinkKey(
		String externalLinkKey, boolean useFinderCache);

	/**
	 * Removes the external link where externalLinkKey = &#63; from the database.
	 *
	 * @param externalLinkKey the external link key
	 * @return the external link that was removed
	 */
	public ExternalLink removeByExternalLinkKey(String externalLinkKey)
		throws NoSuchExternalLinkException;

	/**
	 * Returns the number of external links where externalLinkKey = &#63;.
	 *
	 * @param externalLinkKey the external link key
	 * @return the number of matching external links
	 */
	public int countByExternalLinkKey(String externalLinkKey);

	/**
	 * Returns all the external links where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching external links
	 */
	public java.util.List<ExternalLink> findByC_C(
		long classNameId, long classPK);

	/**
	 * Returns a range of all the external links where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExternalLinkModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of external links
	 * @param end the upper bound of the range of external links (not inclusive)
	 * @return the range of matching external links
	 */
	public java.util.List<ExternalLink> findByC_C(
		long classNameId, long classPK, int start, int end);

	/**
	 * Returns an ordered range of all the external links where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExternalLinkModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of external links
	 * @param end the upper bound of the range of external links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching external links
	 */
	public java.util.List<ExternalLink> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ExternalLink>
			orderByComparator);

	/**
	 * Returns an ordered range of all the external links where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExternalLinkModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of external links
	 * @param end the upper bound of the range of external links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching external links
	 */
	public java.util.List<ExternalLink> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ExternalLink>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first external link in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching external link
	 * @throws NoSuchExternalLinkException if a matching external link could not be found
	 */
	public ExternalLink findByC_C_First(
			long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<ExternalLink>
				orderByComparator)
		throws NoSuchExternalLinkException;

	/**
	 * Returns the first external link in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching external link, or <code>null</code> if a matching external link could not be found
	 */
	public ExternalLink fetchByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<ExternalLink>
			orderByComparator);

	/**
	 * Returns the last external link in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching external link
	 * @throws NoSuchExternalLinkException if a matching external link could not be found
	 */
	public ExternalLink findByC_C_Last(
			long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<ExternalLink>
				orderByComparator)
		throws NoSuchExternalLinkException;

	/**
	 * Returns the last external link in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching external link, or <code>null</code> if a matching external link could not be found
	 */
	public ExternalLink fetchByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<ExternalLink>
			orderByComparator);

	/**
	 * Returns the external links before and after the current external link in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param externalLinkId the primary key of the current external link
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next external link
	 * @throws NoSuchExternalLinkException if a external link with the primary key could not be found
	 */
	public ExternalLink[] findByC_C_PrevAndNext(
			long externalLinkId, long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<ExternalLink>
				orderByComparator)
		throws NoSuchExternalLinkException;

	/**
	 * Removes all the external links where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	public void removeByC_C(long classNameId, long classPK);

	/**
	 * Returns the number of external links where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching external links
	 */
	public int countByC_C(long classNameId, long classPK);

	/**
	 * Returns all the external links where classNameId = &#63; and domain = &#63; and entityName = &#63; and entityId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param domain the domain
	 * @param entityName the entity name
	 * @param entityId the entity ID
	 * @return the matching external links
	 */
	public java.util.List<ExternalLink> findByC_D_EN_EI(
		long classNameId, String domain, String entityName, String entityId);

	/**
	 * Returns a range of all the external links where classNameId = &#63; and domain = &#63; and entityName = &#63; and entityId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExternalLinkModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param domain the domain
	 * @param entityName the entity name
	 * @param entityId the entity ID
	 * @param start the lower bound of the range of external links
	 * @param end the upper bound of the range of external links (not inclusive)
	 * @return the range of matching external links
	 */
	public java.util.List<ExternalLink> findByC_D_EN_EI(
		long classNameId, String domain, String entityName, String entityId,
		int start, int end);

	/**
	 * Returns an ordered range of all the external links where classNameId = &#63; and domain = &#63; and entityName = &#63; and entityId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExternalLinkModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param domain the domain
	 * @param entityName the entity name
	 * @param entityId the entity ID
	 * @param start the lower bound of the range of external links
	 * @param end the upper bound of the range of external links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching external links
	 */
	public java.util.List<ExternalLink> findByC_D_EN_EI(
		long classNameId, String domain, String entityName, String entityId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ExternalLink>
			orderByComparator);

	/**
	 * Returns an ordered range of all the external links where classNameId = &#63; and domain = &#63; and entityName = &#63; and entityId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExternalLinkModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param domain the domain
	 * @param entityName the entity name
	 * @param entityId the entity ID
	 * @param start the lower bound of the range of external links
	 * @param end the upper bound of the range of external links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching external links
	 */
	public java.util.List<ExternalLink> findByC_D_EN_EI(
		long classNameId, String domain, String entityName, String entityId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ExternalLink>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first external link in the ordered set where classNameId = &#63; and domain = &#63; and entityName = &#63; and entityId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param domain the domain
	 * @param entityName the entity name
	 * @param entityId the entity ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching external link
	 * @throws NoSuchExternalLinkException if a matching external link could not be found
	 */
	public ExternalLink findByC_D_EN_EI_First(
			long classNameId, String domain, String entityName, String entityId,
			com.liferay.portal.kernel.util.OrderByComparator<ExternalLink>
				orderByComparator)
		throws NoSuchExternalLinkException;

	/**
	 * Returns the first external link in the ordered set where classNameId = &#63; and domain = &#63; and entityName = &#63; and entityId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param domain the domain
	 * @param entityName the entity name
	 * @param entityId the entity ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching external link, or <code>null</code> if a matching external link could not be found
	 */
	public ExternalLink fetchByC_D_EN_EI_First(
		long classNameId, String domain, String entityName, String entityId,
		com.liferay.portal.kernel.util.OrderByComparator<ExternalLink>
			orderByComparator);

	/**
	 * Returns the last external link in the ordered set where classNameId = &#63; and domain = &#63; and entityName = &#63; and entityId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param domain the domain
	 * @param entityName the entity name
	 * @param entityId the entity ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching external link
	 * @throws NoSuchExternalLinkException if a matching external link could not be found
	 */
	public ExternalLink findByC_D_EN_EI_Last(
			long classNameId, String domain, String entityName, String entityId,
			com.liferay.portal.kernel.util.OrderByComparator<ExternalLink>
				orderByComparator)
		throws NoSuchExternalLinkException;

	/**
	 * Returns the last external link in the ordered set where classNameId = &#63; and domain = &#63; and entityName = &#63; and entityId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param domain the domain
	 * @param entityName the entity name
	 * @param entityId the entity ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching external link, or <code>null</code> if a matching external link could not be found
	 */
	public ExternalLink fetchByC_D_EN_EI_Last(
		long classNameId, String domain, String entityName, String entityId,
		com.liferay.portal.kernel.util.OrderByComparator<ExternalLink>
			orderByComparator);

	/**
	 * Returns the external links before and after the current external link in the ordered set where classNameId = &#63; and domain = &#63; and entityName = &#63; and entityId = &#63;.
	 *
	 * @param externalLinkId the primary key of the current external link
	 * @param classNameId the class name ID
	 * @param domain the domain
	 * @param entityName the entity name
	 * @param entityId the entity ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next external link
	 * @throws NoSuchExternalLinkException if a external link with the primary key could not be found
	 */
	public ExternalLink[] findByC_D_EN_EI_PrevAndNext(
			long externalLinkId, long classNameId, String domain,
			String entityName, String entityId,
			com.liferay.portal.kernel.util.OrderByComparator<ExternalLink>
				orderByComparator)
		throws NoSuchExternalLinkException;

	/**
	 * Removes all the external links where classNameId = &#63; and domain = &#63; and entityName = &#63; and entityId = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param domain the domain
	 * @param entityName the entity name
	 * @param entityId the entity ID
	 */
	public void removeByC_D_EN_EI(
		long classNameId, String domain, String entityName, String entityId);

	/**
	 * Returns the number of external links where classNameId = &#63; and domain = &#63; and entityName = &#63; and entityId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param domain the domain
	 * @param entityName the entity name
	 * @param entityId the entity ID
	 * @return the number of matching external links
	 */
	public int countByC_D_EN_EI(
		long classNameId, String domain, String entityName, String entityId);

	/**
	 * Returns the external link where classNameId = &#63; and classPK = &#63; and domain = &#63; and entityName = &#63; and entityId = &#63; or throws a <code>NoSuchExternalLinkException</code> if it could not be found.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param domain the domain
	 * @param entityName the entity name
	 * @param entityId the entity ID
	 * @return the matching external link
	 * @throws NoSuchExternalLinkException if a matching external link could not be found
	 */
	public ExternalLink findByC_C_D_EN_EI(
			long classNameId, long classPK, String domain, String entityName,
			String entityId)
		throws NoSuchExternalLinkException;

	/**
	 * Returns the external link where classNameId = &#63; and classPK = &#63; and domain = &#63; and entityName = &#63; and entityId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param domain the domain
	 * @param entityName the entity name
	 * @param entityId the entity ID
	 * @return the matching external link, or <code>null</code> if a matching external link could not be found
	 */
	public ExternalLink fetchByC_C_D_EN_EI(
		long classNameId, long classPK, String domain, String entityName,
		String entityId);

	/**
	 * Returns the external link where classNameId = &#63; and classPK = &#63; and domain = &#63; and entityName = &#63; and entityId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param domain the domain
	 * @param entityName the entity name
	 * @param entityId the entity ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching external link, or <code>null</code> if a matching external link could not be found
	 */
	public ExternalLink fetchByC_C_D_EN_EI(
		long classNameId, long classPK, String domain, String entityName,
		String entityId, boolean useFinderCache);

	/**
	 * Removes the external link where classNameId = &#63; and classPK = &#63; and domain = &#63; and entityName = &#63; and entityId = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param domain the domain
	 * @param entityName the entity name
	 * @param entityId the entity ID
	 * @return the external link that was removed
	 */
	public ExternalLink removeByC_C_D_EN_EI(
			long classNameId, long classPK, String domain, String entityName,
			String entityId)
		throws NoSuchExternalLinkException;

	/**
	 * Returns the number of external links where classNameId = &#63; and classPK = &#63; and domain = &#63; and entityName = &#63; and entityId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param domain the domain
	 * @param entityName the entity name
	 * @param entityId the entity ID
	 * @return the number of matching external links
	 */
	public int countByC_C_D_EN_EI(
		long classNameId, long classPK, String domain, String entityName,
		String entityId);

	/**
	 * Caches the external link in the entity cache if it is enabled.
	 *
	 * @param externalLink the external link
	 */
	public void cacheResult(ExternalLink externalLink);

	/**
	 * Caches the external links in the entity cache if it is enabled.
	 *
	 * @param externalLinks the external links
	 */
	public void cacheResult(java.util.List<ExternalLink> externalLinks);

	/**
	 * Creates a new external link with the primary key. Does not add the external link to the database.
	 *
	 * @param externalLinkId the primary key for the new external link
	 * @return the new external link
	 */
	public ExternalLink create(long externalLinkId);

	/**
	 * Removes the external link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param externalLinkId the primary key of the external link
	 * @return the external link that was removed
	 * @throws NoSuchExternalLinkException if a external link with the primary key could not be found
	 */
	public ExternalLink remove(long externalLinkId)
		throws NoSuchExternalLinkException;

	public ExternalLink updateImpl(ExternalLink externalLink);

	/**
	 * Returns the external link with the primary key or throws a <code>NoSuchExternalLinkException</code> if it could not be found.
	 *
	 * @param externalLinkId the primary key of the external link
	 * @return the external link
	 * @throws NoSuchExternalLinkException if a external link with the primary key could not be found
	 */
	public ExternalLink findByPrimaryKey(long externalLinkId)
		throws NoSuchExternalLinkException;

	/**
	 * Returns the external link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param externalLinkId the primary key of the external link
	 * @return the external link, or <code>null</code> if a external link with the primary key could not be found
	 */
	public ExternalLink fetchByPrimaryKey(long externalLinkId);

	/**
	 * Returns all the external links.
	 *
	 * @return the external links
	 */
	public java.util.List<ExternalLink> findAll();

	/**
	 * Returns a range of all the external links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExternalLinkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of external links
	 * @param end the upper bound of the range of external links (not inclusive)
	 * @return the range of external links
	 */
	public java.util.List<ExternalLink> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the external links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExternalLinkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of external links
	 * @param end the upper bound of the range of external links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of external links
	 */
	public java.util.List<ExternalLink> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ExternalLink>
			orderByComparator);

	/**
	 * Returns an ordered range of all the external links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExternalLinkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of external links
	 * @param end the upper bound of the range of external links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of external links
	 */
	public java.util.List<ExternalLink> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ExternalLink>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the external links from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of external links.
	 *
	 * @return the number of external links
	 */
	public int countAll();

}