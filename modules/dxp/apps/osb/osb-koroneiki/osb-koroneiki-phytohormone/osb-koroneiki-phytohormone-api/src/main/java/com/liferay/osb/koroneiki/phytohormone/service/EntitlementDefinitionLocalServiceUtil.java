/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phytohormone.service;

import com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for EntitlementDefinition. This utility wraps
 * <code>com.liferay.osb.koroneiki.phytohormone.service.impl.EntitlementDefinitionLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see EntitlementDefinitionLocalService
 * @generated
 */
public class EntitlementDefinitionLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.koroneiki.phytohormone.service.impl.EntitlementDefinitionLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

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
	public static EntitlementDefinition addEntitlementDefinition(
		EntitlementDefinition entitlementDefinition) {

		return getService().addEntitlementDefinition(entitlementDefinition);
	}

	public static EntitlementDefinition addEntitlementDefinition(
			long userId, long classNameId, String name, String description,
			String definition, int status)
		throws PortalException {

		return getService().addEntitlementDefinition(
			userId, classNameId, name, description, definition, status);
	}

	/**
	 * Creates a new entitlement definition with the primary key. Does not add the entitlement definition to the database.
	 *
	 * @param entitlementDefinitionId the primary key for the new entitlement definition
	 * @return the new entitlement definition
	 */
	public static EntitlementDefinition createEntitlementDefinition(
		long entitlementDefinitionId) {

		return getService().createEntitlementDefinition(
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
	public static EntitlementDefinition deleteEntitlementDefinition(
			EntitlementDefinition entitlementDefinition)
		throws PortalException {

		return getService().deleteEntitlementDefinition(entitlementDefinition);
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
	public static EntitlementDefinition deleteEntitlementDefinition(
			long entitlementDefinitionId)
		throws PortalException {

		return getService().deleteEntitlementDefinition(
			entitlementDefinitionId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.phytohormone.model.impl.EntitlementDefinitionModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.phytohormone.model.impl.EntitlementDefinitionModelImpl</code>.
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

	public static EntitlementDefinition fetchEntitlementDefinition(
		long entitlementDefinitionId) {

		return getService().fetchEntitlementDefinition(entitlementDefinitionId);
	}

	/**
	 * Returns the entitlement definition with the matching UUID and company.
	 *
	 * @param uuid the entitlement definition's UUID
	 * @param companyId the primary key of the company
	 * @return the matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	public static EntitlementDefinition
		fetchEntitlementDefinitionByUuidAndCompanyId(
			String uuid, long companyId) {

		return getService().fetchEntitlementDefinitionByUuidAndCompanyId(
			uuid, companyId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the entitlement definition with the primary key.
	 *
	 * @param entitlementDefinitionId the primary key of the entitlement definition
	 * @return the entitlement definition
	 * @throws PortalException if a entitlement definition with the primary key could not be found
	 */
	public static EntitlementDefinition getEntitlementDefinition(
			long entitlementDefinitionId)
		throws PortalException {

		return getService().getEntitlementDefinition(entitlementDefinitionId);
	}

	public static EntitlementDefinition getEntitlementDefinition(
			String entitlementDefinitionKey)
		throws PortalException {

		return getService().getEntitlementDefinition(entitlementDefinitionKey);
	}

	/**
	 * Returns the entitlement definition with the matching UUID and company.
	 *
	 * @param uuid the entitlement definition's UUID
	 * @param companyId the primary key of the company
	 * @return the matching entitlement definition
	 * @throws PortalException if a matching entitlement definition could not be found
	 */
	public static EntitlementDefinition
			getEntitlementDefinitionByUuidAndCompanyId(
				String uuid, long companyId)
		throws PortalException {

		return getService().getEntitlementDefinitionByUuidAndCompanyId(
			uuid, companyId);
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
	public static List<EntitlementDefinition> getEntitlementDefinitions(
		int start, int end) {

		return getService().getEntitlementDefinitions(start, end);
	}

	public static List<EntitlementDefinition> getEntitlementDefinitions(
		long classNameId, int status) {

		return getService().getEntitlementDefinitions(classNameId, status);
	}

	public static List<EntitlementDefinition> getEntitlementDefinitions(
		String className, int status) {

		return getService().getEntitlementDefinitions(className, status);
	}

	/**
	 * Returns the number of entitlement definitions.
	 *
	 * @return the number of entitlement definitions
	 */
	public static int getEntitlementDefinitionsCount() {
		return getService().getEntitlementDefinitionsCount();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
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

	public static List<EntitlementDefinition> search(
		long classNameId, String name, int start, int end) {

		return getService().search(classNameId, name, start, end);
	}

	public static int searchCount(long classNameId, String name) {
		return getService().searchCount(classNameId, name);
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
	public static EntitlementDefinition updateEntitlementDefinition(
		EntitlementDefinition entitlementDefinition) {

		return getService().updateEntitlementDefinition(entitlementDefinition);
	}

	public static EntitlementDefinition updateEntitlementDefinition(
			long entitlementDefinitionId, String description, String definition,
			int status)
		throws PortalException {

		return getService().updateEntitlementDefinition(
			entitlementDefinitionId, description, definition, status);
	}

	public static EntitlementDefinitionLocalService getService() {
		return _service;
	}

	public static void setService(EntitlementDefinitionLocalService service) {
		_service = service;
	}

	private static volatile EntitlementDefinitionLocalService _service;

}