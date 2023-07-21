/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.persistence;

import com.liferay.osb.koroneiki.taproot.exception.NoSuchContactRoleException;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the contact role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContactRoleUtil
 * @generated
 */
@ProviderType
public interface ContactRolePersistence extends BasePersistence<ContactRole> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ContactRoleUtil} to access the contact role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the contact roles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching contact roles
	 */
	public java.util.List<ContactRole> findByUuid(String uuid);

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
	public java.util.List<ContactRole> findByUuid(
		String uuid, int start, int end);

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
	public java.util.List<ContactRole> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactRole>
			orderByComparator);

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
	public java.util.List<ContactRole> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactRole>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first contact role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact role
	 * @throws NoSuchContactRoleException if a matching contact role could not be found
	 */
	public ContactRole findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<ContactRole>
				orderByComparator)
		throws NoSuchContactRoleException;

	/**
	 * Returns the first contact role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact role, or <code>null</code> if a matching contact role could not be found
	 */
	public ContactRole fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ContactRole>
			orderByComparator);

	/**
	 * Returns the last contact role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact role
	 * @throws NoSuchContactRoleException if a matching contact role could not be found
	 */
	public ContactRole findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<ContactRole>
				orderByComparator)
		throws NoSuchContactRoleException;

	/**
	 * Returns the last contact role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact role, or <code>null</code> if a matching contact role could not be found
	 */
	public ContactRole fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ContactRole>
			orderByComparator);

	/**
	 * Returns the contact roles before and after the current contact role in the ordered set where uuid = &#63;.
	 *
	 * @param contactRoleId the primary key of the current contact role
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact role
	 * @throws NoSuchContactRoleException if a contact role with the primary key could not be found
	 */
	public ContactRole[] findByUuid_PrevAndNext(
			long contactRoleId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<ContactRole>
				orderByComparator)
		throws NoSuchContactRoleException;

	/**
	 * Returns all the contact roles that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching contact roles that the user has permission to view
	 */
	public java.util.List<ContactRole> filterFindByUuid(String uuid);

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
	public java.util.List<ContactRole> filterFindByUuid(
		String uuid, int start, int end);

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
	public java.util.List<ContactRole> filterFindByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactRole>
			orderByComparator);

	/**
	 * Returns the contact roles before and after the current contact role in the ordered set of contact roles that the user has permission to view where uuid = &#63;.
	 *
	 * @param contactRoleId the primary key of the current contact role
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact role
	 * @throws NoSuchContactRoleException if a contact role with the primary key could not be found
	 */
	public ContactRole[] filterFindByUuid_PrevAndNext(
			long contactRoleId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<ContactRole>
				orderByComparator)
		throws NoSuchContactRoleException;

	/**
	 * Removes all the contact roles where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of contact roles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching contact roles
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the number of contact roles that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching contact roles that the user has permission to view
	 */
	public int filterCountByUuid(String uuid);

	/**
	 * Returns all the contact roles where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching contact roles
	 */
	public java.util.List<ContactRole> findByUuid_C(
		String uuid, long companyId);

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
	public java.util.List<ContactRole> findByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<ContactRole> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactRole>
			orderByComparator);

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
	public java.util.List<ContactRole> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactRole>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first contact role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact role
	 * @throws NoSuchContactRoleException if a matching contact role could not be found
	 */
	public ContactRole findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<ContactRole>
				orderByComparator)
		throws NoSuchContactRoleException;

	/**
	 * Returns the first contact role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact role, or <code>null</code> if a matching contact role could not be found
	 */
	public ContactRole fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ContactRole>
			orderByComparator);

	/**
	 * Returns the last contact role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact role
	 * @throws NoSuchContactRoleException if a matching contact role could not be found
	 */
	public ContactRole findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<ContactRole>
				orderByComparator)
		throws NoSuchContactRoleException;

	/**
	 * Returns the last contact role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact role, or <code>null</code> if a matching contact role could not be found
	 */
	public ContactRole fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ContactRole>
			orderByComparator);

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
	public ContactRole[] findByUuid_C_PrevAndNext(
			long contactRoleId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<ContactRole>
				orderByComparator)
		throws NoSuchContactRoleException;

	/**
	 * Returns all the contact roles that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching contact roles that the user has permission to view
	 */
	public java.util.List<ContactRole> filterFindByUuid_C(
		String uuid, long companyId);

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
	public java.util.List<ContactRole> filterFindByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<ContactRole> filterFindByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactRole>
			orderByComparator);

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
	public ContactRole[] filterFindByUuid_C_PrevAndNext(
			long contactRoleId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<ContactRole>
				orderByComparator)
		throws NoSuchContactRoleException;

	/**
	 * Removes all the contact roles where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of contact roles where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching contact roles
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of contact roles that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching contact roles that the user has permission to view
	 */
	public int filterCountByUuid_C(String uuid, long companyId);

	/**
	 * Returns the contact role where contactRoleKey = &#63; or throws a <code>NoSuchContactRoleException</code> if it could not be found.
	 *
	 * @param contactRoleKey the contact role key
	 * @return the matching contact role
	 * @throws NoSuchContactRoleException if a matching contact role could not be found
	 */
	public ContactRole findByContactRoleKey(String contactRoleKey)
		throws NoSuchContactRoleException;

	/**
	 * Returns the contact role where contactRoleKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param contactRoleKey the contact role key
	 * @return the matching contact role, or <code>null</code> if a matching contact role could not be found
	 */
	public ContactRole fetchByContactRoleKey(String contactRoleKey);

	/**
	 * Returns the contact role where contactRoleKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param contactRoleKey the contact role key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching contact role, or <code>null</code> if a matching contact role could not be found
	 */
	public ContactRole fetchByContactRoleKey(
		String contactRoleKey, boolean useFinderCache);

	/**
	 * Removes the contact role where contactRoleKey = &#63; from the database.
	 *
	 * @param contactRoleKey the contact role key
	 * @return the contact role that was removed
	 */
	public ContactRole removeByContactRoleKey(String contactRoleKey)
		throws NoSuchContactRoleException;

	/**
	 * Returns the number of contact roles where contactRoleKey = &#63;.
	 *
	 * @param contactRoleKey the contact role key
	 * @return the number of matching contact roles
	 */
	public int countByContactRoleKey(String contactRoleKey);

	/**
	 * Returns all the contact roles where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching contact roles
	 */
	public java.util.List<ContactRole> findByType(String type);

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
	public java.util.List<ContactRole> findByType(
		String type, int start, int end);

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
	public java.util.List<ContactRole> findByType(
		String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactRole>
			orderByComparator);

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
	public java.util.List<ContactRole> findByType(
		String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactRole>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first contact role in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact role
	 * @throws NoSuchContactRoleException if a matching contact role could not be found
	 */
	public ContactRole findByType_First(
			String type,
			com.liferay.portal.kernel.util.OrderByComparator<ContactRole>
				orderByComparator)
		throws NoSuchContactRoleException;

	/**
	 * Returns the first contact role in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact role, or <code>null</code> if a matching contact role could not be found
	 */
	public ContactRole fetchByType_First(
		String type,
		com.liferay.portal.kernel.util.OrderByComparator<ContactRole>
			orderByComparator);

	/**
	 * Returns the last contact role in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact role
	 * @throws NoSuchContactRoleException if a matching contact role could not be found
	 */
	public ContactRole findByType_Last(
			String type,
			com.liferay.portal.kernel.util.OrderByComparator<ContactRole>
				orderByComparator)
		throws NoSuchContactRoleException;

	/**
	 * Returns the last contact role in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact role, or <code>null</code> if a matching contact role could not be found
	 */
	public ContactRole fetchByType_Last(
		String type,
		com.liferay.portal.kernel.util.OrderByComparator<ContactRole>
			orderByComparator);

	/**
	 * Returns the contact roles before and after the current contact role in the ordered set where type = &#63;.
	 *
	 * @param contactRoleId the primary key of the current contact role
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact role
	 * @throws NoSuchContactRoleException if a contact role with the primary key could not be found
	 */
	public ContactRole[] findByType_PrevAndNext(
			long contactRoleId, String type,
			com.liferay.portal.kernel.util.OrderByComparator<ContactRole>
				orderByComparator)
		throws NoSuchContactRoleException;

	/**
	 * Returns all the contact roles that the user has permission to view where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching contact roles that the user has permission to view
	 */
	public java.util.List<ContactRole> filterFindByType(String type);

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
	public java.util.List<ContactRole> filterFindByType(
		String type, int start, int end);

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
	public java.util.List<ContactRole> filterFindByType(
		String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactRole>
			orderByComparator);

	/**
	 * Returns the contact roles before and after the current contact role in the ordered set of contact roles that the user has permission to view where type = &#63;.
	 *
	 * @param contactRoleId the primary key of the current contact role
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact role
	 * @throws NoSuchContactRoleException if a contact role with the primary key could not be found
	 */
	public ContactRole[] filterFindByType_PrevAndNext(
			long contactRoleId, String type,
			com.liferay.portal.kernel.util.OrderByComparator<ContactRole>
				orderByComparator)
		throws NoSuchContactRoleException;

	/**
	 * Removes all the contact roles where type = &#63; from the database.
	 *
	 * @param type the type
	 */
	public void removeByType(String type);

	/**
	 * Returns the number of contact roles where type = &#63;.
	 *
	 * @param type the type
	 * @return the number of matching contact roles
	 */
	public int countByType(String type);

	/**
	 * Returns the number of contact roles that the user has permission to view where type = &#63;.
	 *
	 * @param type the type
	 * @return the number of matching contact roles that the user has permission to view
	 */
	public int filterCountByType(String type);

	/**
	 * Returns the contact role where name = &#63; and type = &#63; or throws a <code>NoSuchContactRoleException</code> if it could not be found.
	 *
	 * @param name the name
	 * @param type the type
	 * @return the matching contact role
	 * @throws NoSuchContactRoleException if a matching contact role could not be found
	 */
	public ContactRole findByN_T(String name, String type)
		throws NoSuchContactRoleException;

	/**
	 * Returns the contact role where name = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @param type the type
	 * @return the matching contact role, or <code>null</code> if a matching contact role could not be found
	 */
	public ContactRole fetchByN_T(String name, String type);

	/**
	 * Returns the contact role where name = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param type the type
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching contact role, or <code>null</code> if a matching contact role could not be found
	 */
	public ContactRole fetchByN_T(
		String name, String type, boolean useFinderCache);

	/**
	 * Removes the contact role where name = &#63; and type = &#63; from the database.
	 *
	 * @param name the name
	 * @param type the type
	 * @return the contact role that was removed
	 */
	public ContactRole removeByN_T(String name, String type)
		throws NoSuchContactRoleException;

	/**
	 * Returns the number of contact roles where name = &#63; and type = &#63;.
	 *
	 * @param name the name
	 * @param type the type
	 * @return the number of matching contact roles
	 */
	public int countByN_T(String name, String type);

	/**
	 * Caches the contact role in the entity cache if it is enabled.
	 *
	 * @param contactRole the contact role
	 */
	public void cacheResult(ContactRole contactRole);

	/**
	 * Caches the contact roles in the entity cache if it is enabled.
	 *
	 * @param contactRoles the contact roles
	 */
	public void cacheResult(java.util.List<ContactRole> contactRoles);

	/**
	 * Creates a new contact role with the primary key. Does not add the contact role to the database.
	 *
	 * @param contactRoleId the primary key for the new contact role
	 * @return the new contact role
	 */
	public ContactRole create(long contactRoleId);

	/**
	 * Removes the contact role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param contactRoleId the primary key of the contact role
	 * @return the contact role that was removed
	 * @throws NoSuchContactRoleException if a contact role with the primary key could not be found
	 */
	public ContactRole remove(long contactRoleId)
		throws NoSuchContactRoleException;

	public ContactRole updateImpl(ContactRole contactRole);

	/**
	 * Returns the contact role with the primary key or throws a <code>NoSuchContactRoleException</code> if it could not be found.
	 *
	 * @param contactRoleId the primary key of the contact role
	 * @return the contact role
	 * @throws NoSuchContactRoleException if a contact role with the primary key could not be found
	 */
	public ContactRole findByPrimaryKey(long contactRoleId)
		throws NoSuchContactRoleException;

	/**
	 * Returns the contact role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param contactRoleId the primary key of the contact role
	 * @return the contact role, or <code>null</code> if a contact role with the primary key could not be found
	 */
	public ContactRole fetchByPrimaryKey(long contactRoleId);

	/**
	 * Returns all the contact roles.
	 *
	 * @return the contact roles
	 */
	public java.util.List<ContactRole> findAll();

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
	public java.util.List<ContactRole> findAll(int start, int end);

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
	public java.util.List<ContactRole> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactRole>
			orderByComparator);

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
	public java.util.List<ContactRole> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactRole>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the contact roles from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of contact roles.
	 *
	 * @return the number of contact roles
	 */
	public int countAll();

}