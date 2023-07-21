/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.scion.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ServiceProducerLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ServiceProducerLocalService
 * @generated
 */
public class ServiceProducerLocalServiceWrapper
	implements ServiceProducerLocalService,
			   ServiceWrapper<ServiceProducerLocalService> {

	public ServiceProducerLocalServiceWrapper(
		ServiceProducerLocalService serviceProducerLocalService) {

		_serviceProducerLocalService = serviceProducerLocalService;
	}

	@Override
	public com.liferay.osb.koroneiki.scion.model.ServiceProducer
			addServiceProducer(long userId, String name, String description)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _serviceProducerLocalService.addServiceProducer(
			userId, name, description);
	}

	/**
	 * Adds the service producer to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ServiceProducerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param serviceProducer the service producer
	 * @return the service producer that was added
	 */
	@Override
	public com.liferay.osb.koroneiki.scion.model.ServiceProducer
		addServiceProducer(
			com.liferay.osb.koroneiki.scion.model.ServiceProducer
				serviceProducer) {

		return _serviceProducerLocalService.addServiceProducer(serviceProducer);
	}

	/**
	 * Creates a new service producer with the primary key. Does not add the service producer to the database.
	 *
	 * @param serviceProducerId the primary key for the new service producer
	 * @return the new service producer
	 */
	@Override
	public com.liferay.osb.koroneiki.scion.model.ServiceProducer
		createServiceProducer(long serviceProducerId) {

		return _serviceProducerLocalService.createServiceProducer(
			serviceProducerId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _serviceProducerLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the service producer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ServiceProducerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param serviceProducerId the primary key of the service producer
	 * @return the service producer that was removed
	 * @throws PortalException if a service producer with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.scion.model.ServiceProducer
			deleteServiceProducer(long serviceProducerId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _serviceProducerLocalService.deleteServiceProducer(
			serviceProducerId);
	}

	/**
	 * Deletes the service producer from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ServiceProducerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param serviceProducer the service producer
	 * @return the service producer that was removed
	 * @throws PortalException
	 */
	@Override
	public com.liferay.osb.koroneiki.scion.model.ServiceProducer
			deleteServiceProducer(
				com.liferay.osb.koroneiki.scion.model.ServiceProducer
					serviceProducer)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _serviceProducerLocalService.deleteServiceProducer(
			serviceProducer);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _serviceProducerLocalService.dynamicQuery();
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

		return _serviceProducerLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.scion.model.impl.ServiceProducerModelImpl</code>.
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

		return _serviceProducerLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.scion.model.impl.ServiceProducerModelImpl</code>.
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

		return _serviceProducerLocalService.dynamicQuery(
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

		return _serviceProducerLocalService.dynamicQueryCount(dynamicQuery);
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

		return _serviceProducerLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.koroneiki.scion.model.ServiceProducer
		fetchAuthorizedServiceProducer(long authorizationUserId) {

		return _serviceProducerLocalService.fetchAuthorizedServiceProducer(
			authorizationUserId);
	}

	@Override
	public com.liferay.osb.koroneiki.scion.model.ServiceProducer
		fetchServiceProducer(long serviceProducerId) {

		return _serviceProducerLocalService.fetchServiceProducer(
			serviceProducerId);
	}

	/**
	 * Returns the service producer with the matching UUID and company.
	 *
	 * @param uuid the service producer's UUID
	 * @param companyId the primary key of the company
	 * @return the matching service producer, or <code>null</code> if a matching service producer could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.scion.model.ServiceProducer
		fetchServiceProducerByUuidAndCompanyId(String uuid, long companyId) {

		return _serviceProducerLocalService.
			fetchServiceProducerByUuidAndCompanyId(uuid, companyId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _serviceProducerLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.osb.koroneiki.scion.model.ServiceProducer
			getAuthorizedServiceProducer(long authorizationUserId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _serviceProducerLocalService.getAuthorizedServiceProducer(
			authorizationUserId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _serviceProducerLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _serviceProducerLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _serviceProducerLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the service producer with the primary key.
	 *
	 * @param serviceProducerId the primary key of the service producer
	 * @return the service producer
	 * @throws PortalException if a service producer with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.scion.model.ServiceProducer
			getServiceProducer(long serviceProducerId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _serviceProducerLocalService.getServiceProducer(
			serviceProducerId);
	}

	/**
	 * Returns the service producer with the matching UUID and company.
	 *
	 * @param uuid the service producer's UUID
	 * @param companyId the primary key of the company
	 * @return the matching service producer
	 * @throws PortalException if a matching service producer could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.scion.model.ServiceProducer
			getServiceProducerByUuidAndCompanyId(String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _serviceProducerLocalService.
			getServiceProducerByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of all the service producers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.scion.model.impl.ServiceProducerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of service producers
	 * @param end the upper bound of the range of service producers (not inclusive)
	 * @return the range of service producers
	 */
	@Override
	public java.util.List<com.liferay.osb.koroneiki.scion.model.ServiceProducer>
		getServiceProducers(int start, int end) {

		return _serviceProducerLocalService.getServiceProducers(start, end);
	}

	/**
	 * Returns the number of service producers.
	 *
	 * @return the number of service producers
	 */
	@Override
	public int getServiceProducersCount() {
		return _serviceProducerLocalService.getServiceProducersCount();
	}

	@Override
	public com.liferay.osb.koroneiki.scion.model.ServiceProducer
			updateServiceProducer(
				long serviceProducerId, String name, String description)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _serviceProducerLocalService.updateServiceProducer(
			serviceProducerId, name, description);
	}

	/**
	 * Updates the service producer in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ServiceProducerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param serviceProducer the service producer
	 * @return the service producer that was updated
	 */
	@Override
	public com.liferay.osb.koroneiki.scion.model.ServiceProducer
		updateServiceProducer(
			com.liferay.osb.koroneiki.scion.model.ServiceProducer
				serviceProducer) {

		return _serviceProducerLocalService.updateServiceProducer(
			serviceProducer);
	}

	@Override
	public ServiceProducerLocalService getWrappedService() {
		return _serviceProducerLocalService;
	}

	@Override
	public void setWrappedService(
		ServiceProducerLocalService serviceProducerLocalService) {

		_serviceProducerLocalService = serviceProducerLocalService;
	}

	private ServiceProducerLocalService _serviceProducerLocalService;

}