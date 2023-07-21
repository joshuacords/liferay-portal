/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phytohormone.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link EntitlementLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see EntitlementLocalService
 * @generated
 */
public class EntitlementLocalServiceWrapper
	implements EntitlementLocalService,
			   ServiceWrapper<EntitlementLocalService> {

	public EntitlementLocalServiceWrapper(
		EntitlementLocalService entitlementLocalService) {

		_entitlementLocalService = entitlementLocalService;
	}

	/**
	 * Adds the entitlement to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EntitlementLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param entitlement the entitlement
	 * @return the entitlement that was added
	 */
	@Override
	public com.liferay.osb.koroneiki.phytohormone.model.Entitlement
		addEntitlement(
			com.liferay.osb.koroneiki.phytohormone.model.Entitlement
				entitlement) {

		return _entitlementLocalService.addEntitlement(entitlement);
	}

	@Override
	public com.liferay.osb.koroneiki.phytohormone.model.Entitlement
			addEntitlement(
				long userId, long entitlementDefinitionId, long classNameId,
				long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _entitlementLocalService.addEntitlement(
			userId, entitlementDefinitionId, classNameId, classPK);
	}

	/**
	 * Creates a new entitlement with the primary key. Does not add the entitlement to the database.
	 *
	 * @param entitlementId the primary key for the new entitlement
	 * @return the new entitlement
	 */
	@Override
	public com.liferay.osb.koroneiki.phytohormone.model.Entitlement
		createEntitlement(long entitlementId) {

		return _entitlementLocalService.createEntitlement(entitlementId);
	}

	/**
	 * Deletes the entitlement from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EntitlementLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param entitlement the entitlement
	 * @return the entitlement that was removed
	 */
	@Override
	public com.liferay.osb.koroneiki.phytohormone.model.Entitlement
		deleteEntitlement(
			com.liferay.osb.koroneiki.phytohormone.model.Entitlement
				entitlement) {

		return _entitlementLocalService.deleteEntitlement(entitlement);
	}

	/**
	 * Deletes the entitlement with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EntitlementLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param entitlementId the primary key of the entitlement
	 * @return the entitlement that was removed
	 * @throws PortalException if a entitlement with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.phytohormone.model.Entitlement
			deleteEntitlement(long entitlementId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _entitlementLocalService.deleteEntitlement(entitlementId);
	}

	@Override
	public void deleteEntitlements(long classNameId, long classPK) {
		_entitlementLocalService.deleteEntitlements(classNameId, classPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _entitlementLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _entitlementLocalService.dynamicQuery();
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

		return _entitlementLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.phytohormone.model.impl.EntitlementModelImpl</code>.
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

		return _entitlementLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.phytohormone.model.impl.EntitlementModelImpl</code>.
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

		return _entitlementLocalService.dynamicQuery(
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

		return _entitlementLocalService.dynamicQueryCount(dynamicQuery);
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

		return _entitlementLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.koroneiki.phytohormone.model.Entitlement
		fetchEntitlement(long entitlementId) {

		return _entitlementLocalService.fetchEntitlement(entitlementId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _entitlementLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the entitlement with the primary key.
	 *
	 * @param entitlementId the primary key of the entitlement
	 * @return the entitlement
	 * @throws PortalException if a entitlement with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.phytohormone.model.Entitlement
			getEntitlement(long entitlementId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _entitlementLocalService.getEntitlement(entitlementId);
	}

	/**
	 * Returns a range of all the entitlements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.phytohormone.model.impl.EntitlementModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of entitlements
	 * @param end the upper bound of the range of entitlements (not inclusive)
	 * @return the range of entitlements
	 */
	@Override
	public java.util.List
		<com.liferay.osb.koroneiki.phytohormone.model.Entitlement>
			getEntitlements(int start, int end) {

		return _entitlementLocalService.getEntitlements(start, end);
	}

	@Override
	public java.util.List
		<com.liferay.osb.koroneiki.phytohormone.model.Entitlement>
			getEntitlements(
				long classNameId, long classPK, int start, int end) {

		return _entitlementLocalService.getEntitlements(
			classNameId, classPK, start, end);
	}

	@Override
	public java.util.List
		<com.liferay.osb.koroneiki.phytohormone.model.Entitlement>
			getEntitlements(
				String className, long classPK, int start, int end) {

		return _entitlementLocalService.getEntitlements(
			className, classPK, start, end);
	}

	/**
	 * Returns the number of entitlements.
	 *
	 * @return the number of entitlements
	 */
	@Override
	public int getEntitlementsCount() {
		return _entitlementLocalService.getEntitlementsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _entitlementLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _entitlementLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _entitlementLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the entitlement in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EntitlementLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param entitlement the entitlement
	 * @return the entitlement that was updated
	 */
	@Override
	public com.liferay.osb.koroneiki.phytohormone.model.Entitlement
		updateEntitlement(
			com.liferay.osb.koroneiki.phytohormone.model.Entitlement
				entitlement) {

		return _entitlementLocalService.updateEntitlement(entitlement);
	}

	@Override
	public EntitlementLocalService getWrappedService() {
		return _entitlementLocalService;
	}

	@Override
	public void setWrappedService(
		EntitlementLocalService entitlementLocalService) {

		_entitlementLocalService = entitlementLocalService;
	}

	private EntitlementLocalService _entitlementLocalService;

}