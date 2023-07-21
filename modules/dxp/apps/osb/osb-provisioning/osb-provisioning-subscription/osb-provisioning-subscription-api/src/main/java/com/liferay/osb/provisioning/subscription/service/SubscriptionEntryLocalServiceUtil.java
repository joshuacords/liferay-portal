/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.subscription.service;

import com.liferay.osb.provisioning.subscription.model.SubscriptionEntry;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for SubscriptionEntry. This utility wraps
 * <code>com.liferay.osb.provisioning.subscription.service.impl.SubscriptionEntryLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see SubscriptionEntryLocalService
 * @generated
 */
public class SubscriptionEntryLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.provisioning.subscription.service.impl.SubscriptionEntryLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static SubscriptionEntry addSubscriptionEntry(
			long classNameId, long classPK, String contactUuid)
		throws Exception {

		return getService().addSubscriptionEntry(
			classNameId, classPK, contactUuid);
	}

	/**
	 * Adds the subscription entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SubscriptionEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param subscriptionEntry the subscription entry
	 * @return the subscription entry that was added
	 */
	public static SubscriptionEntry addSubscriptionEntry(
		SubscriptionEntry subscriptionEntry) {

		return getService().addSubscriptionEntry(subscriptionEntry);
	}

	/**
	 * Creates a new subscription entry with the primary key. Does not add the subscription entry to the database.
	 *
	 * @param subscriptionEntryId the primary key for the new subscription entry
	 * @return the new subscription entry
	 */
	public static SubscriptionEntry createSubscriptionEntry(
		long subscriptionEntryId) {

		return getService().createSubscriptionEntry(subscriptionEntryId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the subscription entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SubscriptionEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param subscriptionEntryId the primary key of the subscription entry
	 * @return the subscription entry that was removed
	 * @throws PortalException if a subscription entry with the primary key could not be found
	 */
	public static SubscriptionEntry deleteSubscriptionEntry(
			long subscriptionEntryId)
		throws PortalException {

		return getService().deleteSubscriptionEntry(subscriptionEntryId);
	}

	public static void deleteSubscriptionEntry(
			long classNameId, long classPK, String contactUuid)
		throws com.liferay.osb.provisioning.subscription.exception.
			NoSuchSubscriptionEntryException {

		getService().deleteSubscriptionEntry(classNameId, classPK, contactUuid);
	}

	/**
	 * Deletes the subscription entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SubscriptionEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param subscriptionEntry the subscription entry
	 * @return the subscription entry that was removed
	 */
	public static SubscriptionEntry deleteSubscriptionEntry(
		SubscriptionEntry subscriptionEntry) {

		return getService().deleteSubscriptionEntry(subscriptionEntry);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.provisioning.subscription.model.impl.SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.provisioning.subscription.model.impl.SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static SubscriptionEntry fetchSubscriptionEntry(
		long subscriptionEntryId) {

		return getService().fetchSubscriptionEntry(subscriptionEntryId);
	}

	public static SubscriptionEntry fetchSubscriptionEntry(
		long classNameId, long classPK, String contactUuid) {

		return getService().fetchSubscriptionEntry(
			classNameId, classPK, contactUuid);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns a range of all the subscription entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.provisioning.subscription.model.impl.SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of subscription entries
	 * @param end the upper bound of the range of subscription entries (not inclusive)
	 * @return the range of subscription entries
	 */
	public static List<SubscriptionEntry> getSubscriptionEntries(
		int start, int end) {

		return getService().getSubscriptionEntries(start, end);
	}

	public static List<SubscriptionEntry> getSubscriptionEntries(
		long classNameId, long classPK) {

		return getService().getSubscriptionEntries(classNameId, classPK);
	}

	public static List<SubscriptionEntry> getSubscriptionEntries(
		long classNameId, String contactUuid) {

		return getService().getSubscriptionEntries(classNameId, contactUuid);
	}

	public static List<SubscriptionEntry> getSubscriptionEntries(
		String contactUuid) {

		return getService().getSubscriptionEntries(contactUuid);
	}

	/**
	 * Returns the number of subscription entries.
	 *
	 * @return the number of subscription entries
	 */
	public static int getSubscriptionEntriesCount() {
		return getService().getSubscriptionEntriesCount();
	}

	/**
	 * Returns the subscription entry with the primary key.
	 *
	 * @param subscriptionEntryId the primary key of the subscription entry
	 * @return the subscription entry
	 * @throws PortalException if a subscription entry with the primary key could not be found
	 */
	public static SubscriptionEntry getSubscriptionEntry(
			long subscriptionEntryId)
		throws PortalException {

		return getService().getSubscriptionEntry(subscriptionEntryId);
	}

	public static SubscriptionEntry getSubscriptionEntry(
			long classNameId, long classPK, String contactUuid)
		throws com.liferay.osb.provisioning.subscription.exception.
			NoSuchSubscriptionEntryException {

		return getService().getSubscriptionEntry(
			classNameId, classPK, contactUuid);
	}

	/**
	 * Updates the subscription entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SubscriptionEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param subscriptionEntry the subscription entry
	 * @return the subscription entry that was updated
	 */
	public static SubscriptionEntry updateSubscriptionEntry(
		SubscriptionEntry subscriptionEntry) {

		return getService().updateSubscriptionEntry(subscriptionEntry);
	}

	public static SubscriptionEntryLocalService getService() {
		return _service;
	}

	public static void setService(SubscriptionEntryLocalService service) {
		_service = service;
	}

	private static volatile SubscriptionEntryLocalService _service;

}