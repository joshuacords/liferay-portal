/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phytohormone.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link EntitlementDefinitionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see EntitlementDefinitionLocalService
 * @generated
 */
public class EntitlementDefinitionLocalServiceWrapper
	implements EntitlementDefinitionLocalService,
			   ServiceWrapper<EntitlementDefinitionLocalService> {

	public EntitlementDefinitionLocalServiceWrapper(
		EntitlementDefinitionLocalService entitlementDefinitionLocalService) {

		_entitlementDefinitionLocalService = entitlementDefinitionLocalService;
	}

	/**
	 * Adds the entitlement definition to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EntitlementDefinitionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param entitlementDefinition the entitlement definition
	 * @return the entitlement definition that was added
	 */
	@Override
	public com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition
		addEntitlementDefinition(
			com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition
				entitlementDefinition) {

		return _entitlementDefinitionLocalService.addEntitlementDefinition(
			entitlementDefinition);
	}

	@Override
	public com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition
			addEntitlementDefinition(
				long userId, long classNameId, String name, String description,
				String definition, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _entitlementDefinitionLocalService.addEntitlementDefinition(
			userId, classNameId, name, description, definition, status);
	}

	/**
	 * Creates a new entitlement definition with the primary key. Does not add the entitlement definition to the database.
	 *
	 * @param entitlementDefinitionId the primary key for the new entitlement definition
	 * @return the new entitlement definition
	 */
	@Override
	public com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition
		createEntitlementDefinition(long entitlementDefinitionId) {

		return _entitlementDefinitionLocalService.createEntitlementDefinition(
			entitlementDefinitionId);
	}

	/**
	 * Deletes the entitlement definition from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EntitlementDefinitionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param entitlementDefinition the entitlement definition
	 * @return the entitlement definition that was removed
	 * @throws PortalException
	 */
	@Override
	public com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition
			deleteEntitlementDefinition(
				com.liferay.osb.koroneiki.phytohormone.model.
					EntitlementDefinition entitlementDefinition)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _entitlementDefinitionLocalService.deleteEntitlementDefinition(
			entitlementDefinition);
	}

	/**
	 * Deletes the entitlement definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EntitlementDefinitionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param entitlementDefinitionId the primary key of the entitlement definition
	 * @return the entitlement definition that was removed
	 * @throws PortalException if a entitlement definition with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition
			deleteEntitlementDefinition(long entitlementDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _entitlementDefinitionLocalService.deleteEntitlementDefinition(
			entitlementDefinitionId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _entitlementDefinitionLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _entitlementDefinitionLocalService.dynamicQuery();
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

		return _entitlementDefinitionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.phytohormone.model.impl.EntitlementDefinitionModelImpl</code>.
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

		return _entitlementDefinitionLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.phytohormone.model.impl.EntitlementDefinitionModelImpl</code>.
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

		return _entitlementDefinitionLocalService.dynamicQuery(
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

		return _entitlementDefinitionLocalService.dynamicQueryCount(
			dynamicQuery);
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

		return _entitlementDefinitionLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition
		fetchEntitlementDefinition(long entitlementDefinitionId) {

		return _entitlementDefinitionLocalService.fetchEntitlementDefinition(
			entitlementDefinitionId);
	}

	/**
	 * Returns the entitlement definition with the matching UUID and company.
	 *
	 * @param uuid the entitlement definition's UUID
	 * @param companyId the primary key of the company
	 * @return the matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition
		fetchEntitlementDefinitionByUuidAndCompanyId(
			String uuid, long companyId) {

		return _entitlementDefinitionLocalService.
			fetchEntitlementDefinitionByUuidAndCompanyId(uuid, companyId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _entitlementDefinitionLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the entitlement definition with the primary key.
	 *
	 * @param entitlementDefinitionId the primary key of the entitlement definition
	 * @return the entitlement definition
	 * @throws PortalException if a entitlement definition with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition
			getEntitlementDefinition(long entitlementDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _entitlementDefinitionLocalService.getEntitlementDefinition(
			entitlementDefinitionId);
	}

	@Override
	public com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition
			getEntitlementDefinition(String entitlementDefinitionKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _entitlementDefinitionLocalService.getEntitlementDefinition(
			entitlementDefinitionKey);
	}

	/**
	 * Returns the entitlement definition with the matching UUID and company.
	 *
	 * @param uuid the entitlement definition's UUID
	 * @param companyId the primary key of the company
	 * @return the matching entitlement definition
	 * @throws PortalException if a matching entitlement definition could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition
			getEntitlementDefinitionByUuidAndCompanyId(
				String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _entitlementDefinitionLocalService.
			getEntitlementDefinitionByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of all the entitlement definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.phytohormone.model.impl.EntitlementDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of entitlement definitions
	 * @param end the upper bound of the range of entitlement definitions (not inclusive)
	 * @return the range of entitlement definitions
	 */
	@Override
	public java.util.List
		<com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition>
			getEntitlementDefinitions(int start, int end) {

		return _entitlementDefinitionLocalService.getEntitlementDefinitions(
			start, end);
	}

	@Override
	public java.util.List
		<com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition>
			getEntitlementDefinitions(long classNameId, int status) {

		return _entitlementDefinitionLocalService.getEntitlementDefinitions(
			classNameId, status);
	}

	@Override
	public java.util.List
		<com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition>
			getEntitlementDefinitions(String className, int status) {

		return _entitlementDefinitionLocalService.getEntitlementDefinitions(
			className, status);
	}

	/**
	 * Returns the number of entitlement definitions.
	 *
	 * @return the number of entitlement definitions
	 */
	@Override
	public int getEntitlementDefinitionsCount() {
		return _entitlementDefinitionLocalService.
			getEntitlementDefinitionsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _entitlementDefinitionLocalService.
			getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _entitlementDefinitionLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _entitlementDefinitionLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _entitlementDefinitionLocalService.getPersistedModel(
			primaryKeyObj);
	}

	@Override
	public java.util.List
		<com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition>
			search(long classNameId, String name, int start, int end) {

		return _entitlementDefinitionLocalService.search(
			classNameId, name, start, end);
	}

	@Override
	public int searchCount(long classNameId, String name) {
		return _entitlementDefinitionLocalService.searchCount(
			classNameId, name);
	}

	/**
	 * Updates the entitlement definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EntitlementDefinitionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param entitlementDefinition the entitlement definition
	 * @return the entitlement definition that was updated
	 */
	@Override
	public com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition
		updateEntitlementDefinition(
			com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition
				entitlementDefinition) {

		return _entitlementDefinitionLocalService.updateEntitlementDefinition(
			entitlementDefinition);
	}

	@Override
	public com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition
			updateEntitlementDefinition(
				long entitlementDefinitionId, String description,
				String definition, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _entitlementDefinitionLocalService.updateEntitlementDefinition(
			entitlementDefinitionId, description, definition, status);
	}

	@Override
	public EntitlementDefinitionLocalService getWrappedService() {
		return _entitlementDefinitionLocalService;
	}

	@Override
	public void setWrappedService(
		EntitlementDefinitionLocalService entitlementDefinitionLocalService) {

		_entitlementDefinitionLocalService = entitlementDefinitionLocalService;
	}

	private EntitlementDefinitionLocalService
		_entitlementDefinitionLocalService;

}