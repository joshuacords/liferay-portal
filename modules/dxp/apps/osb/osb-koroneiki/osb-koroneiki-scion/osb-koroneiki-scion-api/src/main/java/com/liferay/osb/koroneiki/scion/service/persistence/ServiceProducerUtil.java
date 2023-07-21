/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.scion.service.persistence;

import com.liferay.osb.koroneiki.scion.model.ServiceProducer;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the service producer service. This utility wraps <code>com.liferay.osb.koroneiki.scion.service.persistence.impl.ServiceProducerPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ServiceProducerPersistence
 * @generated
 */
public class ServiceProducerUtil {

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
	public static void clearCache(ServiceProducer serviceProducer) {
		getPersistence().clearCache(serviceProducer);
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
	public static Map<Serializable, ServiceProducer> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ServiceProducer> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ServiceProducer> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ServiceProducer> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ServiceProducer> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ServiceProducer update(ServiceProducer serviceProducer) {
		return getPersistence().update(serviceProducer);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ServiceProducer update(
		ServiceProducer serviceProducer, ServiceContext serviceContext) {

		return getPersistence().update(serviceProducer, serviceContext);
	}

	/**
	 * Returns all the service producers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching service producers
	 */
	public static List<ServiceProducer> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the service producers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServiceProducerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of service producers
	 * @param end the upper bound of the range of service producers (not inclusive)
	 * @return the range of matching service producers
	 */
	public static List<ServiceProducer> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the service producers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServiceProducerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of service producers
	 * @param end the upper bound of the range of service producers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching service producers
	 */
	public static List<ServiceProducer> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<ServiceProducer> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the service producers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServiceProducerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of service producers
	 * @param end the upper bound of the range of service producers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching service producers
	 */
	public static List<ServiceProducer> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<ServiceProducer> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first service producer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service producer
	 * @throws NoSuchServiceProducerException if a matching service producer could not be found
	 */
	public static ServiceProducer findByUuid_First(
			String uuid, OrderByComparator<ServiceProducer> orderByComparator)
		throws com.liferay.osb.koroneiki.scion.exception.
			NoSuchServiceProducerException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first service producer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service producer, or <code>null</code> if a matching service producer could not be found
	 */
	public static ServiceProducer fetchByUuid_First(
		String uuid, OrderByComparator<ServiceProducer> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last service producer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service producer
	 * @throws NoSuchServiceProducerException if a matching service producer could not be found
	 */
	public static ServiceProducer findByUuid_Last(
			String uuid, OrderByComparator<ServiceProducer> orderByComparator)
		throws com.liferay.osb.koroneiki.scion.exception.
			NoSuchServiceProducerException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last service producer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service producer, or <code>null</code> if a matching service producer could not be found
	 */
	public static ServiceProducer fetchByUuid_Last(
		String uuid, OrderByComparator<ServiceProducer> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the service producers before and after the current service producer in the ordered set where uuid = &#63;.
	 *
	 * @param serviceProducerId the primary key of the current service producer
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next service producer
	 * @throws NoSuchServiceProducerException if a service producer with the primary key could not be found
	 */
	public static ServiceProducer[] findByUuid_PrevAndNext(
			long serviceProducerId, String uuid,
			OrderByComparator<ServiceProducer> orderByComparator)
		throws com.liferay.osb.koroneiki.scion.exception.
			NoSuchServiceProducerException {

		return getPersistence().findByUuid_PrevAndNext(
			serviceProducerId, uuid, orderByComparator);
	}

	/**
	 * Removes all the service producers where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of service producers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching service producers
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns all the service producers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching service producers
	 */
	public static List<ServiceProducer> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the service producers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServiceProducerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of service producers
	 * @param end the upper bound of the range of service producers (not inclusive)
	 * @return the range of matching service producers
	 */
	public static List<ServiceProducer> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the service producers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServiceProducerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of service producers
	 * @param end the upper bound of the range of service producers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching service producers
	 */
	public static List<ServiceProducer> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ServiceProducer> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the service producers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServiceProducerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of service producers
	 * @param end the upper bound of the range of service producers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching service producers
	 */
	public static List<ServiceProducer> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ServiceProducer> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first service producer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service producer
	 * @throws NoSuchServiceProducerException if a matching service producer could not be found
	 */
	public static ServiceProducer findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<ServiceProducer> orderByComparator)
		throws com.liferay.osb.koroneiki.scion.exception.
			NoSuchServiceProducerException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first service producer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service producer, or <code>null</code> if a matching service producer could not be found
	 */
	public static ServiceProducer fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<ServiceProducer> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last service producer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service producer
	 * @throws NoSuchServiceProducerException if a matching service producer could not be found
	 */
	public static ServiceProducer findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<ServiceProducer> orderByComparator)
		throws com.liferay.osb.koroneiki.scion.exception.
			NoSuchServiceProducerException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last service producer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service producer, or <code>null</code> if a matching service producer could not be found
	 */
	public static ServiceProducer fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<ServiceProducer> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the service producers before and after the current service producer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param serviceProducerId the primary key of the current service producer
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next service producer
	 * @throws NoSuchServiceProducerException if a service producer with the primary key could not be found
	 */
	public static ServiceProducer[] findByUuid_C_PrevAndNext(
			long serviceProducerId, String uuid, long companyId,
			OrderByComparator<ServiceProducer> orderByComparator)
		throws com.liferay.osb.koroneiki.scion.exception.
			NoSuchServiceProducerException {

		return getPersistence().findByUuid_C_PrevAndNext(
			serviceProducerId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the service producers where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of service producers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching service producers
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the service producer where authorizationUserId = &#63; or throws a <code>NoSuchServiceProducerException</code> if it could not be found.
	 *
	 * @param authorizationUserId the authorization user ID
	 * @return the matching service producer
	 * @throws NoSuchServiceProducerException if a matching service producer could not be found
	 */
	public static ServiceProducer findByAuthorizationUserId(
			long authorizationUserId)
		throws com.liferay.osb.koroneiki.scion.exception.
			NoSuchServiceProducerException {

		return getPersistence().findByAuthorizationUserId(authorizationUserId);
	}

	/**
	 * Returns the service producer where authorizationUserId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param authorizationUserId the authorization user ID
	 * @return the matching service producer, or <code>null</code> if a matching service producer could not be found
	 */
	public static ServiceProducer fetchByAuthorizationUserId(
		long authorizationUserId) {

		return getPersistence().fetchByAuthorizationUserId(authorizationUserId);
	}

	/**
	 * Returns the service producer where authorizationUserId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param authorizationUserId the authorization user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching service producer, or <code>null</code> if a matching service producer could not be found
	 */
	public static ServiceProducer fetchByAuthorizationUserId(
		long authorizationUserId, boolean useFinderCache) {

		return getPersistence().fetchByAuthorizationUserId(
			authorizationUserId, useFinderCache);
	}

	/**
	 * Removes the service producer where authorizationUserId = &#63; from the database.
	 *
	 * @param authorizationUserId the authorization user ID
	 * @return the service producer that was removed
	 */
	public static ServiceProducer removeByAuthorizationUserId(
			long authorizationUserId)
		throws com.liferay.osb.koroneiki.scion.exception.
			NoSuchServiceProducerException {

		return getPersistence().removeByAuthorizationUserId(
			authorizationUserId);
	}

	/**
	 * Returns the number of service producers where authorizationUserId = &#63;.
	 *
	 * @param authorizationUserId the authorization user ID
	 * @return the number of matching service producers
	 */
	public static int countByAuthorizationUserId(long authorizationUserId) {
		return getPersistence().countByAuthorizationUserId(authorizationUserId);
	}

	/**
	 * Caches the service producer in the entity cache if it is enabled.
	 *
	 * @param serviceProducer the service producer
	 */
	public static void cacheResult(ServiceProducer serviceProducer) {
		getPersistence().cacheResult(serviceProducer);
	}

	/**
	 * Caches the service producers in the entity cache if it is enabled.
	 *
	 * @param serviceProducers the service producers
	 */
	public static void cacheResult(List<ServiceProducer> serviceProducers) {
		getPersistence().cacheResult(serviceProducers);
	}

	/**
	 * Creates a new service producer with the primary key. Does not add the service producer to the database.
	 *
	 * @param serviceProducerId the primary key for the new service producer
	 * @return the new service producer
	 */
	public static ServiceProducer create(long serviceProducerId) {
		return getPersistence().create(serviceProducerId);
	}

	/**
	 * Removes the service producer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param serviceProducerId the primary key of the service producer
	 * @return the service producer that was removed
	 * @throws NoSuchServiceProducerException if a service producer with the primary key could not be found
	 */
	public static ServiceProducer remove(long serviceProducerId)
		throws com.liferay.osb.koroneiki.scion.exception.
			NoSuchServiceProducerException {

		return getPersistence().remove(serviceProducerId);
	}

	public static ServiceProducer updateImpl(ServiceProducer serviceProducer) {
		return getPersistence().updateImpl(serviceProducer);
	}

	/**
	 * Returns the service producer with the primary key or throws a <code>NoSuchServiceProducerException</code> if it could not be found.
	 *
	 * @param serviceProducerId the primary key of the service producer
	 * @return the service producer
	 * @throws NoSuchServiceProducerException if a service producer with the primary key could not be found
	 */
	public static ServiceProducer findByPrimaryKey(long serviceProducerId)
		throws com.liferay.osb.koroneiki.scion.exception.
			NoSuchServiceProducerException {

		return getPersistence().findByPrimaryKey(serviceProducerId);
	}

	/**
	 * Returns the service producer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param serviceProducerId the primary key of the service producer
	 * @return the service producer, or <code>null</code> if a service producer with the primary key could not be found
	 */
	public static ServiceProducer fetchByPrimaryKey(long serviceProducerId) {
		return getPersistence().fetchByPrimaryKey(serviceProducerId);
	}

	/**
	 * Returns all the service producers.
	 *
	 * @return the service producers
	 */
	public static List<ServiceProducer> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the service producers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServiceProducerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of service producers
	 * @param end the upper bound of the range of service producers (not inclusive)
	 * @return the range of service producers
	 */
	public static List<ServiceProducer> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the service producers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServiceProducerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of service producers
	 * @param end the upper bound of the range of service producers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of service producers
	 */
	public static List<ServiceProducer> findAll(
		int start, int end,
		OrderByComparator<ServiceProducer> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the service producers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServiceProducerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of service producers
	 * @param end the upper bound of the range of service producers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of service producers
	 */
	public static List<ServiceProducer> findAll(
		int start, int end,
		OrderByComparator<ServiceProducer> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the service producers from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of service producers.
	 *
	 * @return the number of service producers
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ServiceProducerPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(ServiceProducerPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile ServiceProducerPersistence _persistence;

}