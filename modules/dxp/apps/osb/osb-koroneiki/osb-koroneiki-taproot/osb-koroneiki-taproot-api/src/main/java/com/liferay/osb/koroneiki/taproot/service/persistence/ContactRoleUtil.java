/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.persistence;

import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the contact role service. This utility wraps <code>com.liferay.osb.koroneiki.taproot.service.persistence.impl.ContactRolePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContactRolePersistence
 * @generated
 */
public class ContactRoleUtil {

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
	public static void clearCache(ContactRole contactRole) {
		getPersistence().clearCache(contactRole);
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
	public static Map<Serializable, ContactRole> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ContactRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ContactRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ContactRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ContactRole> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ContactRole update(ContactRole contactRole) {
		return getPersistence().update(contactRole);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ContactRole update(
		ContactRole contactRole, ServiceContext serviceContext) {

		return getPersistence().update(contactRole, serviceContext);
	}

	/**
	 * Returns all the contact roles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching contact roles
	 */
	public static List<ContactRole> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the contact roles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactRoleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of contact roles
	 * @param end the upper bound of the range of contact roles (not inclusive)
	 * @return the range of matching contact roles
	 */
	public static List<ContactRole> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the contact roles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactRoleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of contact roles
	 * @param end the upper bound of the range of contact roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact roles
	 */
	public static List<ContactRole> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<ContactRole> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the contact roles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactRoleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of contact roles
	 * @param end the upper bound of the range of contact roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching contact roles
	 */
	public static List<ContactRole> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<ContactRole> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first contact role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact role
	 * @throws NoSuchContactRoleException if a matching contact role could not be found
	 */
	public static ContactRole findByUuid_First(
			String uuid, OrderByComparator<ContactRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactRoleException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first contact role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact role, or <code>null</code> if a matching contact role could not be found
	 */
	public static ContactRole fetchByUuid_First(
		String uuid, OrderByComparator<ContactRole> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last contact role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact role
	 * @throws NoSuchContactRoleException if a matching contact role could not be found
	 */
	public static ContactRole findByUuid_Last(
			String uuid, OrderByComparator<ContactRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactRoleException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last contact role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact role, or <code>null</code> if a matching contact role could not be found
	 */
	public static ContactRole fetchByUuid_Last(
		String uuid, OrderByComparator<ContactRole> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the contact roles before and after the current contact role in the ordered set where uuid = &#63;.
	 *
	 * @param contactRoleId the primary key of the current contact role
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact role
	 * @throws NoSuchContactRoleException if a contact role with the primary key could not be found
	 */
	public static ContactRole[] findByUuid_PrevAndNext(
			long contactRoleId, String uuid,
			OrderByComparator<ContactRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactRoleException {

		return getPersistence().findByUuid_PrevAndNext(
			contactRoleId, uuid, orderByComparator);
	}

	/**
	 * Returns all the contact roles that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching contact roles that the user has permission to view
	 */
	public static List<ContactRole> filterFindByUuid(String uuid) {
		return getPersistence().filterFindByUuid(uuid);
	}

	/**
	 * Returns a range of all the contact roles that the user has permission to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactRoleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of contact roles
	 * @param end the upper bound of the range of contact roles (not inclusive)
	 * @return the range of matching contact roles that the user has permission to view
	 */
	public static List<ContactRole> filterFindByUuid(
		String uuid, int start, int end) {

		return getPersistence().filterFindByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the contact roles that the user has permissions to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactRoleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of contact roles
	 * @param end the upper bound of the range of contact roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact roles that the user has permission to view
	 */
	public static List<ContactRole> filterFindByUuid(
		String uuid, int start, int end,
		OrderByComparator<ContactRole> orderByComparator) {

		return getPersistence().filterFindByUuid(
			uuid, start, end, orderByComparator);
	}

	/**
	 * Returns the contact roles before and after the current contact role in the ordered set of contact roles that the user has permission to view where uuid = &#63;.
	 *
	 * @param contactRoleId the primary key of the current contact role
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact role
	 * @throws NoSuchContactRoleException if a contact role with the primary key could not be found
	 */
	public static ContactRole[] filterFindByUuid_PrevAndNext(
			long contactRoleId, String uuid,
			OrderByComparator<ContactRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactRoleException {

		return getPersistence().filterFindByUuid_PrevAndNext(
			contactRoleId, uuid, orderByComparator);
	}

	/**
	 * Removes all the contact roles where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of contact roles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching contact roles
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the number of contact roles that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching contact roles that the user has permission to view
	 */
	public static int filterCountByUuid(String uuid) {
		return getPersistence().filterCountByUuid(uuid);
	}

	/**
	 * Returns all the contact roles where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching contact roles
	 */
	public static List<ContactRole> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the contact roles where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactRoleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of contact roles
	 * @param end the upper bound of the range of contact roles (not inclusive)
	 * @return the range of matching contact roles
	 */
	public static List<ContactRole> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the contact roles where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactRoleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of contact roles
	 * @param end the upper bound of the range of contact roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact roles
	 */
	public static List<ContactRole> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ContactRole> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the contact roles where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactRoleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of contact roles
	 * @param end the upper bound of the range of contact roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching contact roles
	 */
	public static List<ContactRole> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ContactRole> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first contact role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact role
	 * @throws NoSuchContactRoleException if a matching contact role could not be found
	 */
	public static ContactRole findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<ContactRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactRoleException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first contact role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact role, or <code>null</code> if a matching contact role could not be found
	 */
	public static ContactRole fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<ContactRole> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last contact role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact role
	 * @throws NoSuchContactRoleException if a matching contact role could not be found
	 */
	public static ContactRole findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<ContactRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactRoleException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last contact role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact role, or <code>null</code> if a matching contact role could not be found
	 */
	public static ContactRole fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<ContactRole> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the contact roles before and after the current contact role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param contactRoleId the primary key of the current contact role
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact role
	 * @throws NoSuchContactRoleException if a contact role with the primary key could not be found
	 */
	public static ContactRole[] findByUuid_C_PrevAndNext(
			long contactRoleId, String uuid, long companyId,
			OrderByComparator<ContactRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactRoleException {

		return getPersistence().findByUuid_C_PrevAndNext(
			contactRoleId, uuid, companyId, orderByComparator);
	}

	/**
	 * Returns all the contact roles that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching contact roles that the user has permission to view
	 */
	public static List<ContactRole> filterFindByUuid_C(
		String uuid, long companyId) {

		return getPersistence().filterFindByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the contact roles that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactRoleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of contact roles
	 * @param end the upper bound of the range of contact roles (not inclusive)
	 * @return the range of matching contact roles that the user has permission to view
	 */
	public static List<ContactRole> filterFindByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().filterFindByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the contact roles that the user has permissions to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactRoleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of contact roles
	 * @param end the upper bound of the range of contact roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact roles that the user has permission to view
	 */
	public static List<ContactRole> filterFindByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ContactRole> orderByComparator) {

		return getPersistence().filterFindByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the contact roles before and after the current contact role in the ordered set of contact roles that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param contactRoleId the primary key of the current contact role
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact role
	 * @throws NoSuchContactRoleException if a contact role with the primary key could not be found
	 */
	public static ContactRole[] filterFindByUuid_C_PrevAndNext(
			long contactRoleId, String uuid, long companyId,
			OrderByComparator<ContactRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactRoleException {

		return getPersistence().filterFindByUuid_C_PrevAndNext(
			contactRoleId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the contact roles where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of contact roles where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching contact roles
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of contact roles that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching contact roles that the user has permission to view
	 */
	public static int filterCountByUuid_C(String uuid, long companyId) {
		return getPersistence().filterCountByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the contact role where contactRoleKey = &#63; or throws a <code>NoSuchContactRoleException</code> if it could not be found.
	 *
	 * @param contactRoleKey the contact role key
	 * @return the matching contact role
	 * @throws NoSuchContactRoleException if a matching contact role could not be found
	 */
	public static ContactRole findByContactRoleKey(String contactRoleKey)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactRoleException {

		return getPersistence().findByContactRoleKey(contactRoleKey);
	}

	/**
	 * Returns the contact role where contactRoleKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param contactRoleKey the contact role key
	 * @return the matching contact role, or <code>null</code> if a matching contact role could not be found
	 */
	public static ContactRole fetchByContactRoleKey(String contactRoleKey) {
		return getPersistence().fetchByContactRoleKey(contactRoleKey);
	}

	/**
	 * Returns the contact role where contactRoleKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param contactRoleKey the contact role key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching contact role, or <code>null</code> if a matching contact role could not be found
	 */
	public static ContactRole fetchByContactRoleKey(
		String contactRoleKey, boolean useFinderCache) {

		return getPersistence().fetchByContactRoleKey(
			contactRoleKey, useFinderCache);
	}

	/**
	 * Removes the contact role where contactRoleKey = &#63; from the database.
	 *
	 * @param contactRoleKey the contact role key
	 * @return the contact role that was removed
	 */
	public static ContactRole removeByContactRoleKey(String contactRoleKey)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactRoleException {

		return getPersistence().removeByContactRoleKey(contactRoleKey);
	}

	/**
	 * Returns the number of contact roles where contactRoleKey = &#63;.
	 *
	 * @param contactRoleKey the contact role key
	 * @return the number of matching contact roles
	 */
	public static int countByContactRoleKey(String contactRoleKey) {
		return getPersistence().countByContactRoleKey(contactRoleKey);
	}

	/**
	 * Returns all the contact roles where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching contact roles
	 */
	public static List<ContactRole> findByType(String type) {
		return getPersistence().findByType(type);
	}

	/**
	 * Returns a range of all the contact roles where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactRoleModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of contact roles
	 * @param end the upper bound of the range of contact roles (not inclusive)
	 * @return the range of matching contact roles
	 */
	public static List<ContactRole> findByType(
		String type, int start, int end) {

		return getPersistence().findByType(type, start, end);
	}

	/**
	 * Returns an ordered range of all the contact roles where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactRoleModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of contact roles
	 * @param end the upper bound of the range of contact roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact roles
	 */
	public static List<ContactRole> findByType(
		String type, int start, int end,
		OrderByComparator<ContactRole> orderByComparator) {

		return getPersistence().findByType(type, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the contact roles where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactRoleModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of contact roles
	 * @param end the upper bound of the range of contact roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching contact roles
	 */
	public static List<ContactRole> findByType(
		String type, int start, int end,
		OrderByComparator<ContactRole> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByType(
			type, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first contact role in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact role
	 * @throws NoSuchContactRoleException if a matching contact role could not be found
	 */
	public static ContactRole findByType_First(
			String type, OrderByComparator<ContactRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactRoleException {

		return getPersistence().findByType_First(type, orderByComparator);
	}

	/**
	 * Returns the first contact role in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact role, or <code>null</code> if a matching contact role could not be found
	 */
	public static ContactRole fetchByType_First(
		String type, OrderByComparator<ContactRole> orderByComparator) {

		return getPersistence().fetchByType_First(type, orderByComparator);
	}

	/**
	 * Returns the last contact role in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact role
	 * @throws NoSuchContactRoleException if a matching contact role could not be found
	 */
	public static ContactRole findByType_Last(
			String type, OrderByComparator<ContactRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactRoleException {

		return getPersistence().findByType_Last(type, orderByComparator);
	}

	/**
	 * Returns the last contact role in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact role, or <code>null</code> if a matching contact role could not be found
	 */
	public static ContactRole fetchByType_Last(
		String type, OrderByComparator<ContactRole> orderByComparator) {

		return getPersistence().fetchByType_Last(type, orderByComparator);
	}

	/**
	 * Returns the contact roles before and after the current contact role in the ordered set where type = &#63;.
	 *
	 * @param contactRoleId the primary key of the current contact role
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact role
	 * @throws NoSuchContactRoleException if a contact role with the primary key could not be found
	 */
	public static ContactRole[] findByType_PrevAndNext(
			long contactRoleId, String type,
			OrderByComparator<ContactRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactRoleException {

		return getPersistence().findByType_PrevAndNext(
			contactRoleId, type, orderByComparator);
	}

	/**
	 * Returns all the contact roles that the user has permission to view where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching contact roles that the user has permission to view
	 */
	public static List<ContactRole> filterFindByType(String type) {
		return getPersistence().filterFindByType(type);
	}

	/**
	 * Returns a range of all the contact roles that the user has permission to view where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactRoleModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of contact roles
	 * @param end the upper bound of the range of contact roles (not inclusive)
	 * @return the range of matching contact roles that the user has permission to view
	 */
	public static List<ContactRole> filterFindByType(
		String type, int start, int end) {

		return getPersistence().filterFindByType(type, start, end);
	}

	/**
	 * Returns an ordered range of all the contact roles that the user has permissions to view where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactRoleModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of contact roles
	 * @param end the upper bound of the range of contact roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact roles that the user has permission to view
	 */
	public static List<ContactRole> filterFindByType(
		String type, int start, int end,
		OrderByComparator<ContactRole> orderByComparator) {

		return getPersistence().filterFindByType(
			type, start, end, orderByComparator);
	}

	/**
	 * Returns the contact roles before and after the current contact role in the ordered set of contact roles that the user has permission to view where type = &#63;.
	 *
	 * @param contactRoleId the primary key of the current contact role
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact role
	 * @throws NoSuchContactRoleException if a contact role with the primary key could not be found
	 */
	public static ContactRole[] filterFindByType_PrevAndNext(
			long contactRoleId, String type,
			OrderByComparator<ContactRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactRoleException {

		return getPersistence().filterFindByType_PrevAndNext(
			contactRoleId, type, orderByComparator);
	}

	/**
	 * Removes all the contact roles where type = &#63; from the database.
	 *
	 * @param type the type
	 */
	public static void removeByType(String type) {
		getPersistence().removeByType(type);
	}

	/**
	 * Returns the number of contact roles where type = &#63;.
	 *
	 * @param type the type
	 * @return the number of matching contact roles
	 */
	public static int countByType(String type) {
		return getPersistence().countByType(type);
	}

	/**
	 * Returns the number of contact roles that the user has permission to view where type = &#63;.
	 *
	 * @param type the type
	 * @return the number of matching contact roles that the user has permission to view
	 */
	public static int filterCountByType(String type) {
		return getPersistence().filterCountByType(type);
	}

	/**
	 * Returns the contact role where name = &#63; and type = &#63; or throws a <code>NoSuchContactRoleException</code> if it could not be found.
	 *
	 * @param name the name
	 * @param type the type
	 * @return the matching contact role
	 * @throws NoSuchContactRoleException if a matching contact role could not be found
	 */
	public static ContactRole findByN_T(String name, String type)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactRoleException {

		return getPersistence().findByN_T(name, type);
	}

	/**
	 * Returns the contact role where name = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @param type the type
	 * @return the matching contact role, or <code>null</code> if a matching contact role could not be found
	 */
	public static ContactRole fetchByN_T(String name, String type) {
		return getPersistence().fetchByN_T(name, type);
	}

	/**
	 * Returns the contact role where name = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param type the type
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching contact role, or <code>null</code> if a matching contact role could not be found
	 */
	public static ContactRole fetchByN_T(
		String name, String type, boolean useFinderCache) {

		return getPersistence().fetchByN_T(name, type, useFinderCache);
	}

	/**
	 * Removes the contact role where name = &#63; and type = &#63; from the database.
	 *
	 * @param name the name
	 * @param type the type
	 * @return the contact role that was removed
	 */
	public static ContactRole removeByN_T(String name, String type)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactRoleException {

		return getPersistence().removeByN_T(name, type);
	}

	/**
	 * Returns the number of contact roles where name = &#63; and type = &#63;.
	 *
	 * @param name the name
	 * @param type the type
	 * @return the number of matching contact roles
	 */
	public static int countByN_T(String name, String type) {
		return getPersistence().countByN_T(name, type);
	}

	/**
	 * Caches the contact role in the entity cache if it is enabled.
	 *
	 * @param contactRole the contact role
	 */
	public static void cacheResult(ContactRole contactRole) {
		getPersistence().cacheResult(contactRole);
	}

	/**
	 * Caches the contact roles in the entity cache if it is enabled.
	 *
	 * @param contactRoles the contact roles
	 */
	public static void cacheResult(List<ContactRole> contactRoles) {
		getPersistence().cacheResult(contactRoles);
	}

	/**
	 * Creates a new contact role with the primary key. Does not add the contact role to the database.
	 *
	 * @param contactRoleId the primary key for the new contact role
	 * @return the new contact role
	 */
	public static ContactRole create(long contactRoleId) {
		return getPersistence().create(contactRoleId);
	}

	/**
	 * Removes the contact role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param contactRoleId the primary key of the contact role
	 * @return the contact role that was removed
	 * @throws NoSuchContactRoleException if a contact role with the primary key could not be found
	 */
	public static ContactRole remove(long contactRoleId)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactRoleException {

		return getPersistence().remove(contactRoleId);
	}

	public static ContactRole updateImpl(ContactRole contactRole) {
		return getPersistence().updateImpl(contactRole);
	}

	/**
	 * Returns the contact role with the primary key or throws a <code>NoSuchContactRoleException</code> if it could not be found.
	 *
	 * @param contactRoleId the primary key of the contact role
	 * @return the contact role
	 * @throws NoSuchContactRoleException if a contact role with the primary key could not be found
	 */
	public static ContactRole findByPrimaryKey(long contactRoleId)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactRoleException {

		return getPersistence().findByPrimaryKey(contactRoleId);
	}

	/**
	 * Returns the contact role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param contactRoleId the primary key of the contact role
	 * @return the contact role, or <code>null</code> if a contact role with the primary key could not be found
	 */
	public static ContactRole fetchByPrimaryKey(long contactRoleId) {
		return getPersistence().fetchByPrimaryKey(contactRoleId);
	}

	/**
	 * Returns all the contact roles.
	 *
	 * @return the contact roles
	 */
	public static List<ContactRole> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the contact roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of contact roles
	 * @param end the upper bound of the range of contact roles (not inclusive)
	 * @return the range of contact roles
	 */
	public static List<ContactRole> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the contact roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of contact roles
	 * @param end the upper bound of the range of contact roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of contact roles
	 */
	public static List<ContactRole> findAll(
		int start, int end, OrderByComparator<ContactRole> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the contact roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of contact roles
	 * @param end the upper bound of the range of contact roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of contact roles
	 */
	public static List<ContactRole> findAll(
		int start, int end, OrderByComparator<ContactRole> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the contact roles from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of contact roles.
	 *
	 * @return the number of contact roles
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ContactRolePersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(ContactRolePersistence persistence) {
		_persistence = persistence;
	}

	private static volatile ContactRolePersistence _persistence;

}