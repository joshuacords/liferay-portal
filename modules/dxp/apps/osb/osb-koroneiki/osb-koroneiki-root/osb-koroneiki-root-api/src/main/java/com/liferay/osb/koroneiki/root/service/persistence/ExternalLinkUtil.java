/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.service.persistence;

import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the external link service. This utility wraps <code>com.liferay.osb.koroneiki.root.service.persistence.impl.ExternalLinkPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExternalLinkPersistence
 * @generated
 */
public class ExternalLinkUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(ExternalLink externalLink) {
		getPersistence().clearCache(externalLink);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, ExternalLink> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ExternalLink> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ExternalLink> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ExternalLink> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ExternalLink> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ExternalLink update(ExternalLink externalLink) {
		return getPersistence().update(externalLink);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ExternalLink update(
		ExternalLink externalLink, ServiceContext serviceContext) {

		return getPersistence().update(externalLink, serviceContext);
	}

	/**
	 * Returns the external link where externalLinkKey = &#63; or throws a <code>NoSuchExternalLinkException</code> if it could not be found.
	 *
	 * @param externalLinkKey the external link key
	 * @return the matching external link
	 * @throws NoSuchExternalLinkException if a matching external link could not be found
	 */
	public static ExternalLink findByExternalLinkKey(String externalLinkKey)
		throws com.liferay.osb.koroneiki.root.exception.
			NoSuchExternalLinkException {

		return getPersistence().findByExternalLinkKey(externalLinkKey);
	}

	/**
	 * Returns the external link where externalLinkKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param externalLinkKey the external link key
	 * @return the matching external link, or <code>null</code> if a matching external link could not be found
	 */
	public static ExternalLink fetchByExternalLinkKey(String externalLinkKey) {
		return getPersistence().fetchByExternalLinkKey(externalLinkKey);
	}

	/**
	 * Returns the external link where externalLinkKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param externalLinkKey the external link key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching external link, or <code>null</code> if a matching external link could not be found
	 */
	public static ExternalLink fetchByExternalLinkKey(
		String externalLinkKey, boolean useFinderCache) {

		return getPersistence().fetchByExternalLinkKey(
			externalLinkKey, useFinderCache);
	}

	/**
	 * Removes the external link where externalLinkKey = &#63; from the database.
	 *
	 * @param externalLinkKey the external link key
	 * @return the external link that was removed
	 */
	public static ExternalLink removeByExternalLinkKey(String externalLinkKey)
		throws com.liferay.osb.koroneiki.root.exception.
			NoSuchExternalLinkException {

		return getPersistence().removeByExternalLinkKey(externalLinkKey);
	}

	/**
	 * Returns the number of external links where externalLinkKey = &#63;.
	 *
	 * @param externalLinkKey the external link key
	 * @return the number of matching external links
	 */
	public static int countByExternalLinkKey(String externalLinkKey) {
		return getPersistence().countByExternalLinkKey(externalLinkKey);
	}

	/**
	 * Returns all the external links where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching external links
	 */
	public static List<ExternalLink> findByC_C(long classNameId, long classPK) {
		return getPersistence().findByC_C(classNameId, classPK);
	}

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
	public static List<ExternalLink> findByC_C(
		long classNameId, long classPK, int start, int end) {

		return getPersistence().findByC_C(classNameId, classPK, start, end);
	}

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
	public static List<ExternalLink> findByC_C(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<ExternalLink> orderByComparator) {

		return getPersistence().findByC_C(
			classNameId, classPK, start, end, orderByComparator);
	}

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
	public static List<ExternalLink> findByC_C(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<ExternalLink> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_C(
			classNameId, classPK, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first external link in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching external link
	 * @throws NoSuchExternalLinkException if a matching external link could not be found
	 */
	public static ExternalLink findByC_C_First(
			long classNameId, long classPK,
			OrderByComparator<ExternalLink> orderByComparator)
		throws com.liferay.osb.koroneiki.root.exception.
			NoSuchExternalLinkException {

		return getPersistence().findByC_C_First(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the first external link in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching external link, or <code>null</code> if a matching external link could not be found
	 */
	public static ExternalLink fetchByC_C_First(
		long classNameId, long classPK,
		OrderByComparator<ExternalLink> orderByComparator) {

		return getPersistence().fetchByC_C_First(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the last external link in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching external link
	 * @throws NoSuchExternalLinkException if a matching external link could not be found
	 */
	public static ExternalLink findByC_C_Last(
			long classNameId, long classPK,
			OrderByComparator<ExternalLink> orderByComparator)
		throws com.liferay.osb.koroneiki.root.exception.
			NoSuchExternalLinkException {

		return getPersistence().findByC_C_Last(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the last external link in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching external link, or <code>null</code> if a matching external link could not be found
	 */
	public static ExternalLink fetchByC_C_Last(
		long classNameId, long classPK,
		OrderByComparator<ExternalLink> orderByComparator) {

		return getPersistence().fetchByC_C_Last(
			classNameId, classPK, orderByComparator);
	}

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
	public static ExternalLink[] findByC_C_PrevAndNext(
			long externalLinkId, long classNameId, long classPK,
			OrderByComparator<ExternalLink> orderByComparator)
		throws com.liferay.osb.koroneiki.root.exception.
			NoSuchExternalLinkException {

		return getPersistence().findByC_C_PrevAndNext(
			externalLinkId, classNameId, classPK, orderByComparator);
	}

	/**
	 * Removes all the external links where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	public static void removeByC_C(long classNameId, long classPK) {
		getPersistence().removeByC_C(classNameId, classPK);
	}

	/**
	 * Returns the number of external links where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching external links
	 */
	public static int countByC_C(long classNameId, long classPK) {
		return getPersistence().countByC_C(classNameId, classPK);
	}

	/**
	 * Returns all the external links where classNameId = &#63; and domain = &#63; and entityName = &#63; and entityId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param domain the domain
	 * @param entityName the entity name
	 * @param entityId the entity ID
	 * @return the matching external links
	 */
	public static List<ExternalLink> findByC_D_EN_EI(
		long classNameId, String domain, String entityName, String entityId) {

		return getPersistence().findByC_D_EN_EI(
			classNameId, domain, entityName, entityId);
	}

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
	public static List<ExternalLink> findByC_D_EN_EI(
		long classNameId, String domain, String entityName, String entityId,
		int start, int end) {

		return getPersistence().findByC_D_EN_EI(
			classNameId, domain, entityName, entityId, start, end);
	}

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
	public static List<ExternalLink> findByC_D_EN_EI(
		long classNameId, String domain, String entityName, String entityId,
		int start, int end, OrderByComparator<ExternalLink> orderByComparator) {

		return getPersistence().findByC_D_EN_EI(
			classNameId, domain, entityName, entityId, start, end,
			orderByComparator);
	}

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
	public static List<ExternalLink> findByC_D_EN_EI(
		long classNameId, String domain, String entityName, String entityId,
		int start, int end, OrderByComparator<ExternalLink> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_D_EN_EI(
			classNameId, domain, entityName, entityId, start, end,
			orderByComparator, useFinderCache);
	}

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
	public static ExternalLink findByC_D_EN_EI_First(
			long classNameId, String domain, String entityName, String entityId,
			OrderByComparator<ExternalLink> orderByComparator)
		throws com.liferay.osb.koroneiki.root.exception.
			NoSuchExternalLinkException {

		return getPersistence().findByC_D_EN_EI_First(
			classNameId, domain, entityName, entityId, orderByComparator);
	}

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
	public static ExternalLink fetchByC_D_EN_EI_First(
		long classNameId, String domain, String entityName, String entityId,
		OrderByComparator<ExternalLink> orderByComparator) {

		return getPersistence().fetchByC_D_EN_EI_First(
			classNameId, domain, entityName, entityId, orderByComparator);
	}

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
	public static ExternalLink findByC_D_EN_EI_Last(
			long classNameId, String domain, String entityName, String entityId,
			OrderByComparator<ExternalLink> orderByComparator)
		throws com.liferay.osb.koroneiki.root.exception.
			NoSuchExternalLinkException {

		return getPersistence().findByC_D_EN_EI_Last(
			classNameId, domain, entityName, entityId, orderByComparator);
	}

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
	public static ExternalLink fetchByC_D_EN_EI_Last(
		long classNameId, String domain, String entityName, String entityId,
		OrderByComparator<ExternalLink> orderByComparator) {

		return getPersistence().fetchByC_D_EN_EI_Last(
			classNameId, domain, entityName, entityId, orderByComparator);
	}

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
	public static ExternalLink[] findByC_D_EN_EI_PrevAndNext(
			long externalLinkId, long classNameId, String domain,
			String entityName, String entityId,
			OrderByComparator<ExternalLink> orderByComparator)
		throws com.liferay.osb.koroneiki.root.exception.
			NoSuchExternalLinkException {

		return getPersistence().findByC_D_EN_EI_PrevAndNext(
			externalLinkId, classNameId, domain, entityName, entityId,
			orderByComparator);
	}

	/**
	 * Removes all the external links where classNameId = &#63; and domain = &#63; and entityName = &#63; and entityId = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param domain the domain
	 * @param entityName the entity name
	 * @param entityId the entity ID
	 */
	public static void removeByC_D_EN_EI(
		long classNameId, String domain, String entityName, String entityId) {

		getPersistence().removeByC_D_EN_EI(
			classNameId, domain, entityName, entityId);
	}

	/**
	 * Returns the number of external links where classNameId = &#63; and domain = &#63; and entityName = &#63; and entityId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param domain the domain
	 * @param entityName the entity name
	 * @param entityId the entity ID
	 * @return the number of matching external links
	 */
	public static int countByC_D_EN_EI(
		long classNameId, String domain, String entityName, String entityId) {

		return getPersistence().countByC_D_EN_EI(
			classNameId, domain, entityName, entityId);
	}

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
	public static ExternalLink findByC_C_D_EN_EI(
			long classNameId, long classPK, String domain, String entityName,
			String entityId)
		throws com.liferay.osb.koroneiki.root.exception.
			NoSuchExternalLinkException {

		return getPersistence().findByC_C_D_EN_EI(
			classNameId, classPK, domain, entityName, entityId);
	}

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
	public static ExternalLink fetchByC_C_D_EN_EI(
		long classNameId, long classPK, String domain, String entityName,
		String entityId) {

		return getPersistence().fetchByC_C_D_EN_EI(
			classNameId, classPK, domain, entityName, entityId);
	}

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
	public static ExternalLink fetchByC_C_D_EN_EI(
		long classNameId, long classPK, String domain, String entityName,
		String entityId, boolean useFinderCache) {

		return getPersistence().fetchByC_C_D_EN_EI(
			classNameId, classPK, domain, entityName, entityId, useFinderCache);
	}

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
	public static ExternalLink removeByC_C_D_EN_EI(
			long classNameId, long classPK, String domain, String entityName,
			String entityId)
		throws com.liferay.osb.koroneiki.root.exception.
			NoSuchExternalLinkException {

		return getPersistence().removeByC_C_D_EN_EI(
			classNameId, classPK, domain, entityName, entityId);
	}

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
	public static int countByC_C_D_EN_EI(
		long classNameId, long classPK, String domain, String entityName,
		String entityId) {

		return getPersistence().countByC_C_D_EN_EI(
			classNameId, classPK, domain, entityName, entityId);
	}

	/**
	 * Caches the external link in the entity cache if it is enabled.
	 *
	 * @param externalLink the external link
	 */
	public static void cacheResult(ExternalLink externalLink) {
		getPersistence().cacheResult(externalLink);
	}

	/**
	 * Caches the external links in the entity cache if it is enabled.
	 *
	 * @param externalLinks the external links
	 */
	public static void cacheResult(List<ExternalLink> externalLinks) {
		getPersistence().cacheResult(externalLinks);
	}

	/**
	 * Creates a new external link with the primary key. Does not add the external link to the database.
	 *
	 * @param externalLinkId the primary key for the new external link
	 * @return the new external link
	 */
	public static ExternalLink create(long externalLinkId) {
		return getPersistence().create(externalLinkId);
	}

	/**
	 * Removes the external link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param externalLinkId the primary key of the external link
	 * @return the external link that was removed
	 * @throws NoSuchExternalLinkException if a external link with the primary key could not be found
	 */
	public static ExternalLink remove(long externalLinkId)
		throws com.liferay.osb.koroneiki.root.exception.
			NoSuchExternalLinkException {

		return getPersistence().remove(externalLinkId);
	}

	public static ExternalLink updateImpl(ExternalLink externalLink) {
		return getPersistence().updateImpl(externalLink);
	}

	/**
	 * Returns the external link with the primary key or throws a <code>NoSuchExternalLinkException</code> if it could not be found.
	 *
	 * @param externalLinkId the primary key of the external link
	 * @return the external link
	 * @throws NoSuchExternalLinkException if a external link with the primary key could not be found
	 */
	public static ExternalLink findByPrimaryKey(long externalLinkId)
		throws com.liferay.osb.koroneiki.root.exception.
			NoSuchExternalLinkException {

		return getPersistence().findByPrimaryKey(externalLinkId);
	}

	/**
	 * Returns the external link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param externalLinkId the primary key of the external link
	 * @return the external link, or <code>null</code> if a external link with the primary key could not be found
	 */
	public static ExternalLink fetchByPrimaryKey(long externalLinkId) {
		return getPersistence().fetchByPrimaryKey(externalLinkId);
	}

	/**
	 * Returns all the external links.
	 *
	 * @return the external links
	 */
	public static List<ExternalLink> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<ExternalLink> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<ExternalLink> findAll(
		int start, int end, OrderByComparator<ExternalLink> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<ExternalLink> findAll(
		int start, int end, OrderByComparator<ExternalLink> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the external links from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of external links.
	 *
	 * @return the number of external links
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ExternalLinkPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(ExternalLinkPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile ExternalLinkPersistence _persistence;

}