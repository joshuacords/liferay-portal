/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.scion.service;

import com.liferay.osb.koroneiki.scion.model.ServiceProducer;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for ServiceProducer. This utility wraps
 * <code>com.liferay.osb.koroneiki.scion.service.impl.ServiceProducerLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ServiceProducerLocalService
 * @generated
 */
public class ServiceProducerLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.koroneiki.scion.service.impl.ServiceProducerLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static ServiceProducer addServiceProducer(
			long userId, String name, String description)
		throws PortalException {

		return getService().addServiceProducer(userId, name, description);
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
	public static ServiceProducer addServiceProducer(
		ServiceProducer serviceProducer) {

		return getService().addServiceProducer(serviceProducer);
	}

	/**
	 * Creates a new service producer with the primary key. Does not add the service producer to the database.
	 *
	 * @param serviceProducerId the primary key for the new service producer
	 * @return the new service producer
	 */
	public static ServiceProducer createServiceProducer(
		long serviceProducerId) {

		return getService().createServiceProducer(serviceProducerId);
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
	public static ServiceProducer deleteServiceProducer(long serviceProducerId)
		throws PortalException {

		return getService().deleteServiceProducer(serviceProducerId);
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
	public static ServiceProducer deleteServiceProducer(
			ServiceProducer serviceProducer)
		throws PortalException {

		return getService().deleteServiceProducer(serviceProducer);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.scion.model.impl.ServiceProducerModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.scion.model.impl.ServiceProducerModelImpl</code>.
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

	public static ServiceProducer fetchAuthorizedServiceProducer(
		long authorizationUserId) {

		return getService().fetchAuthorizedServiceProducer(authorizationUserId);
	}

	public static ServiceProducer fetchServiceProducer(long serviceProducerId) {
		return getService().fetchServiceProducer(serviceProducerId);
	}

	/**
	 * Returns the service producer with the matching UUID and company.
	 *
	 * @param uuid the service producer's UUID
	 * @param companyId the primary key of the company
	 * @return the matching service producer, or <code>null</code> if a matching service producer could not be found
	 */
	public static ServiceProducer fetchServiceProducerByUuidAndCompanyId(
		String uuid, long companyId) {

		return getService().fetchServiceProducerByUuidAndCompanyId(
			uuid, companyId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static ServiceProducer getAuthorizedServiceProducer(
			long authorizationUserId)
		throws PortalException {

		return getService().getAuthorizedServiceProducer(authorizationUserId);
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
	 * Returns the service producer with the primary key.
	 *
	 * @param serviceProducerId the primary key of the service producer
	 * @return the service producer
	 * @throws PortalException if a service producer with the primary key could not be found
	 */
	public static ServiceProducer getServiceProducer(long serviceProducerId)
		throws PortalException {

		return getService().getServiceProducer(serviceProducerId);
	}

	/**
	 * Returns the service producer with the matching UUID and company.
	 *
	 * @param uuid the service producer's UUID
	 * @param companyId the primary key of the company
	 * @return the matching service producer
	 * @throws PortalException if a matching service producer could not be found
	 */
	public static ServiceProducer getServiceProducerByUuidAndCompanyId(
			String uuid, long companyId)
		throws PortalException {

		return getService().getServiceProducerByUuidAndCompanyId(
			uuid, companyId);
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
	public static List<ServiceProducer> getServiceProducers(
		int start, int end) {

		return getService().getServiceProducers(start, end);
	}

	/**
	 * Returns the number of service producers.
	 *
	 * @return the number of service producers
	 */
	public static int getServiceProducersCount() {
		return getService().getServiceProducersCount();
	}

	public static ServiceProducer updateServiceProducer(
			long serviceProducerId, String name, String description)
		throws PortalException {

		return getService().updateServiceProducer(
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
	public static ServiceProducer updateServiceProducer(
		ServiceProducer serviceProducer) {

		return getService().updateServiceProducer(serviceProducer);
	}

	public static ServiceProducerLocalService getService() {
		return _service;
	}

	public static void setService(ServiceProducerLocalService service) {
		_service = service;
	}

	private static volatile ServiceProducerLocalService _service;

}