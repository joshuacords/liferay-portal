/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.scion.service.persistence;

import com.liferay.osb.koroneiki.scion.exception.NoSuchServiceProducerException;
import com.liferay.osb.koroneiki.scion.model.ServiceProducer;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the service producer service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ServiceProducerUtil
 * @generated
 */
@ProviderType
public interface ServiceProducerPersistence
	extends BasePersistence<ServiceProducer> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ServiceProducerUtil} to access the service producer persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the service producers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching service producers
	 */
	public java.util.List<ServiceProducer> findByUuid(String uuid);

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
	public java.util.List<ServiceProducer> findByUuid(
		String uuid, int start, int end);

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
	public java.util.List<ServiceProducer> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProducer>
			orderByComparator);

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
	public java.util.List<ServiceProducer> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProducer>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first service producer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service producer
	 * @throws NoSuchServiceProducerException if a matching service producer could not be found
	 */
	public ServiceProducer findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<ServiceProducer>
				orderByComparator)
		throws NoSuchServiceProducerException;

	/**
	 * Returns the first service producer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service producer, or <code>null</code> if a matching service producer could not be found
	 */
	public ServiceProducer fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProducer>
			orderByComparator);

	/**
	 * Returns the last service producer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service producer
	 * @throws NoSuchServiceProducerException if a matching service producer could not be found
	 */
	public ServiceProducer findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<ServiceProducer>
				orderByComparator)
		throws NoSuchServiceProducerException;

	/**
	 * Returns the last service producer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service producer, or <code>null</code> if a matching service producer could not be found
	 */
	public ServiceProducer fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProducer>
			orderByComparator);

	/**
	 * Returns the service producers before and after the current service producer in the ordered set where uuid = &#63;.
	 *
	 * @param serviceProducerId the primary key of the current service producer
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next service producer
	 * @throws NoSuchServiceProducerException if a service producer with the primary key could not be found
	 */
	public ServiceProducer[] findByUuid_PrevAndNext(
			long serviceProducerId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<ServiceProducer>
				orderByComparator)
		throws NoSuchServiceProducerException;

	/**
	 * Removes all the service producers where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of service producers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching service producers
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns all the service producers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching service producers
	 */
	public java.util.List<ServiceProducer> findByUuid_C(
		String uuid, long companyId);

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
	public java.util.List<ServiceProducer> findByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<ServiceProducer> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProducer>
			orderByComparator);

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
	public java.util.List<ServiceProducer> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProducer>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first service producer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service producer
	 * @throws NoSuchServiceProducerException if a matching service producer could not be found
	 */
	public ServiceProducer findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<ServiceProducer>
				orderByComparator)
		throws NoSuchServiceProducerException;

	/**
	 * Returns the first service producer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service producer, or <code>null</code> if a matching service producer could not be found
	 */
	public ServiceProducer fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProducer>
			orderByComparator);

	/**
	 * Returns the last service producer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service producer
	 * @throws NoSuchServiceProducerException if a matching service producer could not be found
	 */
	public ServiceProducer findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<ServiceProducer>
				orderByComparator)
		throws NoSuchServiceProducerException;

	/**
	 * Returns the last service producer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service producer, or <code>null</code> if a matching service producer could not be found
	 */
	public ServiceProducer fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProducer>
			orderByComparator);

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
	public ServiceProducer[] findByUuid_C_PrevAndNext(
			long serviceProducerId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<ServiceProducer>
				orderByComparator)
		throws NoSuchServiceProducerException;

	/**
	 * Removes all the service producers where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of service producers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching service producers
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns the service producer where authorizationUserId = &#63; or throws a <code>NoSuchServiceProducerException</code> if it could not be found.
	 *
	 * @param authorizationUserId the authorization user ID
	 * @return the matching service producer
	 * @throws NoSuchServiceProducerException if a matching service producer could not be found
	 */
	public ServiceProducer findByAuthorizationUserId(long authorizationUserId)
		throws NoSuchServiceProducerException;

	/**
	 * Returns the service producer where authorizationUserId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param authorizationUserId the authorization user ID
	 * @return the matching service producer, or <code>null</code> if a matching service producer could not be found
	 */
	public ServiceProducer fetchByAuthorizationUserId(long authorizationUserId);

	/**
	 * Returns the service producer where authorizationUserId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param authorizationUserId the authorization user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching service producer, or <code>null</code> if a matching service producer could not be found
	 */
	public ServiceProducer fetchByAuthorizationUserId(
		long authorizationUserId, boolean useFinderCache);

	/**
	 * Removes the service producer where authorizationUserId = &#63; from the database.
	 *
	 * @param authorizationUserId the authorization user ID
	 * @return the service producer that was removed
	 */
	public ServiceProducer removeByAuthorizationUserId(long authorizationUserId)
		throws NoSuchServiceProducerException;

	/**
	 * Returns the number of service producers where authorizationUserId = &#63;.
	 *
	 * @param authorizationUserId the authorization user ID
	 * @return the number of matching service producers
	 */
	public int countByAuthorizationUserId(long authorizationUserId);

	/**
	 * Caches the service producer in the entity cache if it is enabled.
	 *
	 * @param serviceProducer the service producer
	 */
	public void cacheResult(ServiceProducer serviceProducer);

	/**
	 * Caches the service producers in the entity cache if it is enabled.
	 *
	 * @param serviceProducers the service producers
	 */
	public void cacheResult(java.util.List<ServiceProducer> serviceProducers);

	/**
	 * Creates a new service producer with the primary key. Does not add the service producer to the database.
	 *
	 * @param serviceProducerId the primary key for the new service producer
	 * @return the new service producer
	 */
	public ServiceProducer create(long serviceProducerId);

	/**
	 * Removes the service producer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param serviceProducerId the primary key of the service producer
	 * @return the service producer that was removed
	 * @throws NoSuchServiceProducerException if a service producer with the primary key could not be found
	 */
	public ServiceProducer remove(long serviceProducerId)
		throws NoSuchServiceProducerException;

	public ServiceProducer updateImpl(ServiceProducer serviceProducer);

	/**
	 * Returns the service producer with the primary key or throws a <code>NoSuchServiceProducerException</code> if it could not be found.
	 *
	 * @param serviceProducerId the primary key of the service producer
	 * @return the service producer
	 * @throws NoSuchServiceProducerException if a service producer with the primary key could not be found
	 */
	public ServiceProducer findByPrimaryKey(long serviceProducerId)
		throws NoSuchServiceProducerException;

	/**
	 * Returns the service producer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param serviceProducerId the primary key of the service producer
	 * @return the service producer, or <code>null</code> if a service producer with the primary key could not be found
	 */
	public ServiceProducer fetchByPrimaryKey(long serviceProducerId);

	/**
	 * Returns all the service producers.
	 *
	 * @return the service producers
	 */
	public java.util.List<ServiceProducer> findAll();

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
	public java.util.List<ServiceProducer> findAll(int start, int end);

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
	public java.util.List<ServiceProducer> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProducer>
			orderByComparator);

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
	public java.util.List<ServiceProducer> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProducer>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the service producers from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of service producers.
	 *
	 * @return the number of service producers
	 */
	public int countAll();

}