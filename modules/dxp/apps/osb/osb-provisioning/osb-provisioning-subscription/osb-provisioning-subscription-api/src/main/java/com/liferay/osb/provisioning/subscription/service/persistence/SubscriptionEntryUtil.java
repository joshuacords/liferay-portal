/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.provisioning.subscription.service.persistence;

import com.liferay.osb.provisioning.subscription.model.SubscriptionEntry;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the subscription entry service. This utility wraps <code>com.liferay.osb.provisioning.subscription.service.persistence.impl.SubscriptionEntryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SubscriptionEntryPersistence
 * @generated
 */
public class SubscriptionEntryUtil {

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
	public static void clearCache(SubscriptionEntry subscriptionEntry) {
		getPersistence().clearCache(subscriptionEntry);
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
	public static Map<Serializable, SubscriptionEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<SubscriptionEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SubscriptionEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SubscriptionEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SubscriptionEntry> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SubscriptionEntry update(
		SubscriptionEntry subscriptionEntry) {

		return getPersistence().update(subscriptionEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SubscriptionEntry update(
		SubscriptionEntry subscriptionEntry, ServiceContext serviceContext) {

		return getPersistence().update(subscriptionEntry, serviceContext);
	}

	/**
	 * Returns all the subscription entries where contactUuid = &#63;.
	 *
	 * @param contactUuid the contact uuid
	 * @return the matching subscription entries
	 */
	public static List<SubscriptionEntry> findByContactUuid(
		String contactUuid) {

		return getPersistence().findByContactUuid(contactUuid);
	}

	/**
	 * Returns a range of all the subscription entries where contactUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param contactUuid the contact uuid
	 * @param start the lower bound of the range of subscription entries
	 * @param end the upper bound of the range of subscription entries (not inclusive)
	 * @return the range of matching subscription entries
	 */
	public static List<SubscriptionEntry> findByContactUuid(
		String contactUuid, int start, int end) {

		return getPersistence().findByContactUuid(contactUuid, start, end);
	}

	/**
	 * Returns an ordered range of all the subscription entries where contactUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param contactUuid the contact uuid
	 * @param start the lower bound of the range of subscription entries
	 * @param end the upper bound of the range of subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching subscription entries
	 */
	public static List<SubscriptionEntry> findByContactUuid(
		String contactUuid, int start, int end,
		OrderByComparator<SubscriptionEntry> orderByComparator) {

		return getPersistence().findByContactUuid(
			contactUuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the subscription entries where contactUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param contactUuid the contact uuid
	 * @param start the lower bound of the range of subscription entries
	 * @param end the upper bound of the range of subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching subscription entries
	 */
	public static List<SubscriptionEntry> findByContactUuid(
		String contactUuid, int start, int end,
		OrderByComparator<SubscriptionEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByContactUuid(
			contactUuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first subscription entry in the ordered set where contactUuid = &#63;.
	 *
	 * @param contactUuid the contact uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching subscription entry
	 * @throws NoSuchSubscriptionEntryException if a matching subscription entry could not be found
	 */
	public static SubscriptionEntry findByContactUuid_First(
			String contactUuid,
			OrderByComparator<SubscriptionEntry> orderByComparator)
		throws com.liferay.osb.provisioning.subscription.exception.
			NoSuchSubscriptionEntryException {

		return getPersistence().findByContactUuid_First(
			contactUuid, orderByComparator);
	}

	/**
	 * Returns the first subscription entry in the ordered set where contactUuid = &#63;.
	 *
	 * @param contactUuid the contact uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching subscription entry, or <code>null</code> if a matching subscription entry could not be found
	 */
	public static SubscriptionEntry fetchByContactUuid_First(
		String contactUuid,
		OrderByComparator<SubscriptionEntry> orderByComparator) {

		return getPersistence().fetchByContactUuid_First(
			contactUuid, orderByComparator);
	}

	/**
	 * Returns the last subscription entry in the ordered set where contactUuid = &#63;.
	 *
	 * @param contactUuid the contact uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching subscription entry
	 * @throws NoSuchSubscriptionEntryException if a matching subscription entry could not be found
	 */
	public static SubscriptionEntry findByContactUuid_Last(
			String contactUuid,
			OrderByComparator<SubscriptionEntry> orderByComparator)
		throws com.liferay.osb.provisioning.subscription.exception.
			NoSuchSubscriptionEntryException {

		return getPersistence().findByContactUuid_Last(
			contactUuid, orderByComparator);
	}

	/**
	 * Returns the last subscription entry in the ordered set where contactUuid = &#63;.
	 *
	 * @param contactUuid the contact uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching subscription entry, or <code>null</code> if a matching subscription entry could not be found
	 */
	public static SubscriptionEntry fetchByContactUuid_Last(
		String contactUuid,
		OrderByComparator<SubscriptionEntry> orderByComparator) {

		return getPersistence().fetchByContactUuid_Last(
			contactUuid, orderByComparator);
	}

	/**
	 * Returns the subscription entries before and after the current subscription entry in the ordered set where contactUuid = &#63;.
	 *
	 * @param subscriptionEntryId the primary key of the current subscription entry
	 * @param contactUuid the contact uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next subscription entry
	 * @throws NoSuchSubscriptionEntryException if a subscription entry with the primary key could not be found
	 */
	public static SubscriptionEntry[] findByContactUuid_PrevAndNext(
			long subscriptionEntryId, String contactUuid,
			OrderByComparator<SubscriptionEntry> orderByComparator)
		throws com.liferay.osb.provisioning.subscription.exception.
			NoSuchSubscriptionEntryException {

		return getPersistence().findByContactUuid_PrevAndNext(
			subscriptionEntryId, contactUuid, orderByComparator);
	}

	/**
	 * Removes all the subscription entries where contactUuid = &#63; from the database.
	 *
	 * @param contactUuid the contact uuid
	 */
	public static void removeByContactUuid(String contactUuid) {
		getPersistence().removeByContactUuid(contactUuid);
	}

	/**
	 * Returns the number of subscription entries where contactUuid = &#63;.
	 *
	 * @param contactUuid the contact uuid
	 * @return the number of matching subscription entries
	 */
	public static int countByContactUuid(String contactUuid) {
		return getPersistence().countByContactUuid(contactUuid);
	}

	/**
	 * Returns all the subscription entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching subscription entries
	 */
	public static List<SubscriptionEntry> findByC_C(
		long classNameId, long classPK) {

		return getPersistence().findByC_C(classNameId, classPK);
	}

	/**
	 * Returns a range of all the subscription entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of subscription entries
	 * @param end the upper bound of the range of subscription entries (not inclusive)
	 * @return the range of matching subscription entries
	 */
	public static List<SubscriptionEntry> findByC_C(
		long classNameId, long classPK, int start, int end) {

		return getPersistence().findByC_C(classNameId, classPK, start, end);
	}

	/**
	 * Returns an ordered range of all the subscription entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of subscription entries
	 * @param end the upper bound of the range of subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching subscription entries
	 */
	public static List<SubscriptionEntry> findByC_C(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<SubscriptionEntry> orderByComparator) {

		return getPersistence().findByC_C(
			classNameId, classPK, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the subscription entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of subscription entries
	 * @param end the upper bound of the range of subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching subscription entries
	 */
	public static List<SubscriptionEntry> findByC_C(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<SubscriptionEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_C(
			classNameId, classPK, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first subscription entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching subscription entry
	 * @throws NoSuchSubscriptionEntryException if a matching subscription entry could not be found
	 */
	public static SubscriptionEntry findByC_C_First(
			long classNameId, long classPK,
			OrderByComparator<SubscriptionEntry> orderByComparator)
		throws com.liferay.osb.provisioning.subscription.exception.
			NoSuchSubscriptionEntryException {

		return getPersistence().findByC_C_First(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the first subscription entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching subscription entry, or <code>null</code> if a matching subscription entry could not be found
	 */
	public static SubscriptionEntry fetchByC_C_First(
		long classNameId, long classPK,
		OrderByComparator<SubscriptionEntry> orderByComparator) {

		return getPersistence().fetchByC_C_First(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the last subscription entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching subscription entry
	 * @throws NoSuchSubscriptionEntryException if a matching subscription entry could not be found
	 */
	public static SubscriptionEntry findByC_C_Last(
			long classNameId, long classPK,
			OrderByComparator<SubscriptionEntry> orderByComparator)
		throws com.liferay.osb.provisioning.subscription.exception.
			NoSuchSubscriptionEntryException {

		return getPersistence().findByC_C_Last(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the last subscription entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching subscription entry, or <code>null</code> if a matching subscription entry could not be found
	 */
	public static SubscriptionEntry fetchByC_C_Last(
		long classNameId, long classPK,
		OrderByComparator<SubscriptionEntry> orderByComparator) {

		return getPersistence().fetchByC_C_Last(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the subscription entries before and after the current subscription entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param subscriptionEntryId the primary key of the current subscription entry
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next subscription entry
	 * @throws NoSuchSubscriptionEntryException if a subscription entry with the primary key could not be found
	 */
	public static SubscriptionEntry[] findByC_C_PrevAndNext(
			long subscriptionEntryId, long classNameId, long classPK,
			OrderByComparator<SubscriptionEntry> orderByComparator)
		throws com.liferay.osb.provisioning.subscription.exception.
			NoSuchSubscriptionEntryException {

		return getPersistence().findByC_C_PrevAndNext(
			subscriptionEntryId, classNameId, classPK, orderByComparator);
	}

	/**
	 * Removes all the subscription entries where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	public static void removeByC_C(long classNameId, long classPK) {
		getPersistence().removeByC_C(classNameId, classPK);
	}

	/**
	 * Returns the number of subscription entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching subscription entries
	 */
	public static int countByC_C(long classNameId, long classPK) {
		return getPersistence().countByC_C(classNameId, classPK);
	}

	/**
	 * Returns the subscription entry where classNameId = &#63; and classPK = &#63; and contactUuid = &#63; or throws a <code>NoSuchSubscriptionEntryException</code> if it could not be found.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param contactUuid the contact uuid
	 * @return the matching subscription entry
	 * @throws NoSuchSubscriptionEntryException if a matching subscription entry could not be found
	 */
	public static SubscriptionEntry findByC_C_CU(
			long classNameId, long classPK, String contactUuid)
		throws com.liferay.osb.provisioning.subscription.exception.
			NoSuchSubscriptionEntryException {

		return getPersistence().findByC_C_CU(classNameId, classPK, contactUuid);
	}

	/**
	 * Returns the subscription entry where classNameId = &#63; and classPK = &#63; and contactUuid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param contactUuid the contact uuid
	 * @return the matching subscription entry, or <code>null</code> if a matching subscription entry could not be found
	 */
	public static SubscriptionEntry fetchByC_C_CU(
		long classNameId, long classPK, String contactUuid) {

		return getPersistence().fetchByC_C_CU(
			classNameId, classPK, contactUuid);
	}

	/**
	 * Returns the subscription entry where classNameId = &#63; and classPK = &#63; and contactUuid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param contactUuid the contact uuid
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching subscription entry, or <code>null</code> if a matching subscription entry could not be found
	 */
	public static SubscriptionEntry fetchByC_C_CU(
		long classNameId, long classPK, String contactUuid,
		boolean useFinderCache) {

		return getPersistence().fetchByC_C_CU(
			classNameId, classPK, contactUuid, useFinderCache);
	}

	/**
	 * Removes the subscription entry where classNameId = &#63; and classPK = &#63; and contactUuid = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param contactUuid the contact uuid
	 * @return the subscription entry that was removed
	 */
	public static SubscriptionEntry removeByC_C_CU(
			long classNameId, long classPK, String contactUuid)
		throws com.liferay.osb.provisioning.subscription.exception.
			NoSuchSubscriptionEntryException {

		return getPersistence().removeByC_C_CU(
			classNameId, classPK, contactUuid);
	}

	/**
	 * Returns the number of subscription entries where classNameId = &#63; and classPK = &#63; and contactUuid = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param contactUuid the contact uuid
	 * @return the number of matching subscription entries
	 */
	public static int countByC_C_CU(
		long classNameId, long classPK, String contactUuid) {

		return getPersistence().countByC_C_CU(
			classNameId, classPK, contactUuid);
	}

	/**
	 * Caches the subscription entry in the entity cache if it is enabled.
	 *
	 * @param subscriptionEntry the subscription entry
	 */
	public static void cacheResult(SubscriptionEntry subscriptionEntry) {
		getPersistence().cacheResult(subscriptionEntry);
	}

	/**
	 * Caches the subscription entries in the entity cache if it is enabled.
	 *
	 * @param subscriptionEntries the subscription entries
	 */
	public static void cacheResult(
		List<SubscriptionEntry> subscriptionEntries) {

		getPersistence().cacheResult(subscriptionEntries);
	}

	/**
	 * Creates a new subscription entry with the primary key. Does not add the subscription entry to the database.
	 *
	 * @param subscriptionEntryId the primary key for the new subscription entry
	 * @return the new subscription entry
	 */
	public static SubscriptionEntry create(long subscriptionEntryId) {
		return getPersistence().create(subscriptionEntryId);
	}

	/**
	 * Removes the subscription entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param subscriptionEntryId the primary key of the subscription entry
	 * @return the subscription entry that was removed
	 * @throws NoSuchSubscriptionEntryException if a subscription entry with the primary key could not be found
	 */
	public static SubscriptionEntry remove(long subscriptionEntryId)
		throws com.liferay.osb.provisioning.subscription.exception.
			NoSuchSubscriptionEntryException {

		return getPersistence().remove(subscriptionEntryId);
	}

	public static SubscriptionEntry updateImpl(
		SubscriptionEntry subscriptionEntry) {

		return getPersistence().updateImpl(subscriptionEntry);
	}

	/**
	 * Returns the subscription entry with the primary key or throws a <code>NoSuchSubscriptionEntryException</code> if it could not be found.
	 *
	 * @param subscriptionEntryId the primary key of the subscription entry
	 * @return the subscription entry
	 * @throws NoSuchSubscriptionEntryException if a subscription entry with the primary key could not be found
	 */
	public static SubscriptionEntry findByPrimaryKey(long subscriptionEntryId)
		throws com.liferay.osb.provisioning.subscription.exception.
			NoSuchSubscriptionEntryException {

		return getPersistence().findByPrimaryKey(subscriptionEntryId);
	}

	/**
	 * Returns the subscription entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param subscriptionEntryId the primary key of the subscription entry
	 * @return the subscription entry, or <code>null</code> if a subscription entry with the primary key could not be found
	 */
	public static SubscriptionEntry fetchByPrimaryKey(
		long subscriptionEntryId) {

		return getPersistence().fetchByPrimaryKey(subscriptionEntryId);
	}

	/**
	 * Returns all the subscription entries.
	 *
	 * @return the subscription entries
	 */
	public static List<SubscriptionEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the subscription entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of subscription entries
	 * @param end the upper bound of the range of subscription entries (not inclusive)
	 * @return the range of subscription entries
	 */
	public static List<SubscriptionEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the subscription entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of subscription entries
	 * @param end the upper bound of the range of subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of subscription entries
	 */
	public static List<SubscriptionEntry> findAll(
		int start, int end,
		OrderByComparator<SubscriptionEntry> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the subscription entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of subscription entries
	 * @param end the upper bound of the range of subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of subscription entries
	 */
	public static List<SubscriptionEntry> findAll(
		int start, int end,
		OrderByComparator<SubscriptionEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the subscription entries from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of subscription entries.
	 *
	 * @return the number of subscription entries
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SubscriptionEntryPersistence getPersistence() {
		return _persistence;
	}

	private static volatile SubscriptionEntryPersistence _persistence;

}