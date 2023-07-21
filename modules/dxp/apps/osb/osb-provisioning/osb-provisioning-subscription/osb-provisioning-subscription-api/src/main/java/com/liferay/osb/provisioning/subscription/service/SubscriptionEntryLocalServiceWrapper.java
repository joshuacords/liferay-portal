/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.subscription.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SubscriptionEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SubscriptionEntryLocalService
 * @generated
 */
public class SubscriptionEntryLocalServiceWrapper
	implements ServiceWrapper<SubscriptionEntryLocalService>,
			   SubscriptionEntryLocalService {

	public SubscriptionEntryLocalServiceWrapper(
		SubscriptionEntryLocalService subscriptionEntryLocalService) {

		_subscriptionEntryLocalService = subscriptionEntryLocalService;
	}

	@Override
	public com.liferay.osb.provisioning.subscription.model.SubscriptionEntry
			addSubscriptionEntry(
				long classNameId, long classPK, String contactUuid)
		throws Exception {

		return _subscriptionEntryLocalService.addSubscriptionEntry(
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
	@Override
	public com.liferay.osb.provisioning.subscription.model.SubscriptionEntry
		addSubscriptionEntry(
			com.liferay.osb.provisioning.subscription.model.SubscriptionEntry
				subscriptionEntry) {

		return _subscriptionEntryLocalService.addSubscriptionEntry(
			subscriptionEntry);
	}

	/**
	 * Creates a new subscription entry with the primary key. Does not add the subscription entry to the database.
	 *
	 * @param subscriptionEntryId the primary key for the new subscription entry
	 * @return the new subscription entry
	 */
	@Override
	public com.liferay.osb.provisioning.subscription.model.SubscriptionEntry
		createSubscriptionEntry(long subscriptionEntryId) {

		return _subscriptionEntryLocalService.createSubscriptionEntry(
			subscriptionEntryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _subscriptionEntryLocalService.deletePersistedModel(
			persistedModel);
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
	@Override
	public com.liferay.osb.provisioning.subscription.model.SubscriptionEntry
			deleteSubscriptionEntry(long subscriptionEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _subscriptionEntryLocalService.deleteSubscriptionEntry(
			subscriptionEntryId);
	}

	@Override
	public void deleteSubscriptionEntry(
			long classNameId, long classPK, String contactUuid)
		throws com.liferay.osb.provisioning.subscription.exception.
			NoSuchSubscriptionEntryException {

		_subscriptionEntryLocalService.deleteSubscriptionEntry(
			classNameId, classPK, contactUuid);
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
	@Override
	public com.liferay.osb.provisioning.subscription.model.SubscriptionEntry
		deleteSubscriptionEntry(
			com.liferay.osb.provisioning.subscription.model.SubscriptionEntry
				subscriptionEntry) {

		return _subscriptionEntryLocalService.deleteSubscriptionEntry(
			subscriptionEntry);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _subscriptionEntryLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _subscriptionEntryLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _subscriptionEntryLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _subscriptionEntryLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _subscriptionEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _subscriptionEntryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.provisioning.subscription.model.SubscriptionEntry
		fetchSubscriptionEntry(long subscriptionEntryId) {

		return _subscriptionEntryLocalService.fetchSubscriptionEntry(
			subscriptionEntryId);
	}

	@Override
	public com.liferay.osb.provisioning.subscription.model.SubscriptionEntry
		fetchSubscriptionEntry(
			long classNameId, long classPK, String contactUuid) {

		return _subscriptionEntryLocalService.fetchSubscriptionEntry(
			classNameId, classPK, contactUuid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _subscriptionEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _subscriptionEntryLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _subscriptionEntryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _subscriptionEntryLocalService.getPersistedModel(primaryKeyObj);
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
	@Override
	public java.util.List
		<com.liferay.osb.provisioning.subscription.model.SubscriptionEntry>
			getSubscriptionEntries(int start, int end) {

		return _subscriptionEntryLocalService.getSubscriptionEntries(
			start, end);
	}

	@Override
	public java.util.List
		<com.liferay.osb.provisioning.subscription.model.SubscriptionEntry>
			getSubscriptionEntries(long classNameId, long classPK) {

		return _subscriptionEntryLocalService.getSubscriptionEntries(
			classNameId, classPK);
	}

	@Override
	public java.util.List
		<com.liferay.osb.provisioning.subscription.model.SubscriptionEntry>
			getSubscriptionEntries(long classNameId, String contactUuid) {

		return _subscriptionEntryLocalService.getSubscriptionEntries(
			classNameId, contactUuid);
	}

	@Override
	public java.util.List
		<com.liferay.osb.provisioning.subscription.model.SubscriptionEntry>
			getSubscriptionEntries(String contactUuid) {

		return _subscriptionEntryLocalService.getSubscriptionEntries(
			contactUuid);
	}

	/**
	 * Returns the number of subscription entries.
	 *
	 * @return the number of subscription entries
	 */
	@Override
	public int getSubscriptionEntriesCount() {
		return _subscriptionEntryLocalService.getSubscriptionEntriesCount();
	}

	/**
	 * Returns the subscription entry with the primary key.
	 *
	 * @param subscriptionEntryId the primary key of the subscription entry
	 * @return the subscription entry
	 * @throws PortalException if a subscription entry with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.provisioning.subscription.model.SubscriptionEntry
			getSubscriptionEntry(long subscriptionEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _subscriptionEntryLocalService.getSubscriptionEntry(
			subscriptionEntryId);
	}

	@Override
	public com.liferay.osb.provisioning.subscription.model.SubscriptionEntry
			getSubscriptionEntry(
				long classNameId, long classPK, String contactUuid)
		throws com.liferay.osb.provisioning.subscription.exception.
			NoSuchSubscriptionEntryException {

		return _subscriptionEntryLocalService.getSubscriptionEntry(
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
	@Override
	public com.liferay.osb.provisioning.subscription.model.SubscriptionEntry
		updateSubscriptionEntry(
			com.liferay.osb.provisioning.subscription.model.SubscriptionEntry
				subscriptionEntry) {

		return _subscriptionEntryLocalService.updateSubscriptionEntry(
			subscriptionEntry);
	}

	@Override
	public SubscriptionEntryLocalService getWrappedService() {
		return _subscriptionEntryLocalService;
	}

	@Override
	public void setWrappedService(
		SubscriptionEntryLocalService subscriptionEntryLocalService) {

		_subscriptionEntryLocalService = subscriptionEntryLocalService;
	}

	private SubscriptionEntryLocalService _subscriptionEntryLocalService;

}