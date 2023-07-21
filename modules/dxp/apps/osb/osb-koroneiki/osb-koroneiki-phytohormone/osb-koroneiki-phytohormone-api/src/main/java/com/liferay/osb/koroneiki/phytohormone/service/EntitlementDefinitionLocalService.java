/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phytohormone.service;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for EntitlementDefinition. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see EntitlementDefinitionLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface EntitlementDefinitionLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.osb.koroneiki.phytohormone.service.impl.EntitlementDefinitionLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the entitlement definition local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link EntitlementDefinitionLocalServiceUtil} if injection and service tracking are not available.
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
	@Indexable(type = IndexableType.REINDEX)
	public EntitlementDefinition addEntitlementDefinition(
		EntitlementDefinition entitlementDefinition);

	public EntitlementDefinition addEntitlementDefinition(
			long userId, long classNameId, String name, String description,
			String definition, int status)
		throws PortalException;

	/**
	 * Creates a new entitlement definition with the primary key. Does not add the entitlement definition to the database.
	 *
	 * @param entitlementDefinitionId the primary key for the new entitlement definition
	 * @return the new entitlement definition
	 */
	@Transactional(enabled = false)
	public EntitlementDefinition createEntitlementDefinition(
		long entitlementDefinitionId);

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
	@Indexable(type = IndexableType.DELETE)
	public EntitlementDefinition deleteEntitlementDefinition(
			EntitlementDefinition entitlementDefinition)
		throws PortalException;

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
	@Indexable(type = IndexableType.DELETE)
	public EntitlementDefinition deleteEntitlementDefinition(
			long entitlementDefinitionId)
		throws PortalException;

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public EntitlementDefinition fetchEntitlementDefinition(
		long entitlementDefinitionId);

	/**
	 * Returns the entitlement definition with the matching UUID and company.
	 *
	 * @param uuid the entitlement definition's UUID
	 * @param companyId the primary key of the company
	 * @return the matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public EntitlementDefinition fetchEntitlementDefinitionByUuidAndCompanyId(
		String uuid, long companyId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	 * Returns the entitlement definition with the primary key.
	 *
	 * @param entitlementDefinitionId the primary key of the entitlement definition
	 * @return the entitlement definition
	 * @throws PortalException if a entitlement definition with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public EntitlementDefinition getEntitlementDefinition(
			long entitlementDefinitionId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public EntitlementDefinition getEntitlementDefinition(
			String entitlementDefinitionKey)
		throws PortalException;

	/**
	 * Returns the entitlement definition with the matching UUID and company.
	 *
	 * @param uuid the entitlement definition's UUID
	 * @param companyId the primary key of the company
	 * @return the matching entitlement definition
	 * @throws PortalException if a matching entitlement definition could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public EntitlementDefinition getEntitlementDefinitionByUuidAndCompanyId(
			String uuid, long companyId)
		throws PortalException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<EntitlementDefinition> getEntitlementDefinitions(
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<EntitlementDefinition> getEntitlementDefinitions(
		long classNameId, int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<EntitlementDefinition> getEntitlementDefinitions(
		String className, int status);

	/**
	 * Returns the number of entitlement definitions.
	 *
	 * @return the number of entitlement definitions
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getEntitlementDefinitionsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	/**
	 * @throws PortalException
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<EntitlementDefinition> search(
		long classNameId, String name, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(long classNameId, String name);

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
	@Indexable(type = IndexableType.REINDEX)
	public EntitlementDefinition updateEntitlementDefinition(
		EntitlementDefinition entitlementDefinition);

	public EntitlementDefinition updateEntitlementDefinition(
			long entitlementDefinitionId, String description, String definition,
			int status)
		throws PortalException;

}