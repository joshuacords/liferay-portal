/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.persistence;

import com.liferay.osb.koroneiki.taproot.exception.NoSuchContactAccountRoleException;
import com.liferay.osb.koroneiki.taproot.model.ContactAccountRole;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the contact account role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContactAccountRoleUtil
 * @generated
 */
@ProviderType
public interface ContactAccountRolePersistence
	extends BasePersistence<ContactAccountRole> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ContactAccountRoleUtil} to access the contact account role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the contact account roles where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @return the matching contact account roles
	 */
	public java.util.List<ContactAccountRole> findByContactId(long contactId);

	/**
	 * Returns a range of all the contact account roles where contactId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @return the range of matching contact account roles
	 */
	public java.util.List<ContactAccountRole> findByContactId(
		long contactId, int start, int end);

	/**
	 * Returns an ordered range of all the contact account roles where contactId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact account roles
	 */
	public java.util.List<ContactAccountRole> findByContactId(
		long contactId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactAccountRole>
			orderByComparator);

	/**
	 * Returns an ordered range of all the contact account roles where contactId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching contact account roles
	 */
	public java.util.List<ContactAccountRole> findByContactId(
		long contactId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactAccountRole>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first contact account role in the ordered set where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact account role
	 * @throws NoSuchContactAccountRoleException if a matching contact account role could not be found
	 */
	public ContactAccountRole findByContactId_First(
			long contactId,
			com.liferay.portal.kernel.util.OrderByComparator<ContactAccountRole>
				orderByComparator)
		throws NoSuchContactAccountRoleException;

	/**
	 * Returns the first contact account role in the ordered set where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact account role, or <code>null</code> if a matching contact account role could not be found
	 */
	public ContactAccountRole fetchByContactId_First(
		long contactId,
		com.liferay.portal.kernel.util.OrderByComparator<ContactAccountRole>
			orderByComparator);

	/**
	 * Returns the last contact account role in the ordered set where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact account role
	 * @throws NoSuchContactAccountRoleException if a matching contact account role could not be found
	 */
	public ContactAccountRole findByContactId_Last(
			long contactId,
			com.liferay.portal.kernel.util.OrderByComparator<ContactAccountRole>
				orderByComparator)
		throws NoSuchContactAccountRoleException;

	/**
	 * Returns the last contact account role in the ordered set where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact account role, or <code>null</code> if a matching contact account role could not be found
	 */
	public ContactAccountRole fetchByContactId_Last(
		long contactId,
		com.liferay.portal.kernel.util.OrderByComparator<ContactAccountRole>
			orderByComparator);

	/**
	 * Returns the contact account roles before and after the current contact account role in the ordered set where contactId = &#63;.
	 *
	 * @param contactAccountRolePK the primary key of the current contact account role
	 * @param contactId the contact ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact account role
	 * @throws NoSuchContactAccountRoleException if a contact account role with the primary key could not be found
	 */
	public ContactAccountRole[] findByContactId_PrevAndNext(
			ContactAccountRolePK contactAccountRolePK, long contactId,
			com.liferay.portal.kernel.util.OrderByComparator<ContactAccountRole>
				orderByComparator)
		throws NoSuchContactAccountRoleException;

	/**
	 * Removes all the contact account roles where contactId = &#63; from the database.
	 *
	 * @param contactId the contact ID
	 */
	public void removeByContactId(long contactId);

	/**
	 * Returns the number of contact account roles where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @return the number of matching contact account roles
	 */
	public int countByContactId(long contactId);

	/**
	 * Returns all the contact account roles where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the matching contact account roles
	 */
	public java.util.List<ContactAccountRole> findByAccountId(long accountId);

	/**
	 * Returns a range of all the contact account roles where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @return the range of matching contact account roles
	 */
	public java.util.List<ContactAccountRole> findByAccountId(
		long accountId, int start, int end);

	/**
	 * Returns an ordered range of all the contact account roles where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact account roles
	 */
	public java.util.List<ContactAccountRole> findByAccountId(
		long accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactAccountRole>
			orderByComparator);

	/**
	 * Returns an ordered range of all the contact account roles where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching contact account roles
	 */
	public java.util.List<ContactAccountRole> findByAccountId(
		long accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactAccountRole>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first contact account role in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact account role
	 * @throws NoSuchContactAccountRoleException if a matching contact account role could not be found
	 */
	public ContactAccountRole findByAccountId_First(
			long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<ContactAccountRole>
				orderByComparator)
		throws NoSuchContactAccountRoleException;

	/**
	 * Returns the first contact account role in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact account role, or <code>null</code> if a matching contact account role could not be found
	 */
	public ContactAccountRole fetchByAccountId_First(
		long accountId,
		com.liferay.portal.kernel.util.OrderByComparator<ContactAccountRole>
			orderByComparator);

	/**
	 * Returns the last contact account role in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact account role
	 * @throws NoSuchContactAccountRoleException if a matching contact account role could not be found
	 */
	public ContactAccountRole findByAccountId_Last(
			long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<ContactAccountRole>
				orderByComparator)
		throws NoSuchContactAccountRoleException;

	/**
	 * Returns the last contact account role in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact account role, or <code>null</code> if a matching contact account role could not be found
	 */
	public ContactAccountRole fetchByAccountId_Last(
		long accountId,
		com.liferay.portal.kernel.util.OrderByComparator<ContactAccountRole>
			orderByComparator);

	/**
	 * Returns the contact account roles before and after the current contact account role in the ordered set where accountId = &#63;.
	 *
	 * @param contactAccountRolePK the primary key of the current contact account role
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact account role
	 * @throws NoSuchContactAccountRoleException if a contact account role with the primary key could not be found
	 */
	public ContactAccountRole[] findByAccountId_PrevAndNext(
			ContactAccountRolePK contactAccountRolePK, long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<ContactAccountRole>
				orderByComparator)
		throws NoSuchContactAccountRoleException;

	/**
	 * Removes all the contact account roles where accountId = &#63; from the database.
	 *
	 * @param accountId the account ID
	 */
	public void removeByAccountId(long accountId);

	/**
	 * Returns the number of contact account roles where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the number of matching contact account roles
	 */
	public int countByAccountId(long accountId);

	/**
	 * Returns all the contact account roles where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @return the matching contact account roles
	 */
	public java.util.List<ContactAccountRole> findByContactRoleId(
		long contactRoleId);

	/**
	 * Returns a range of all the contact account roles where contactRoleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactRoleId the contact role ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @return the range of matching contact account roles
	 */
	public java.util.List<ContactAccountRole> findByContactRoleId(
		long contactRoleId, int start, int end);

	/**
	 * Returns an ordered range of all the contact account roles where contactRoleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactRoleId the contact role ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact account roles
	 */
	public java.util.List<ContactAccountRole> findByContactRoleId(
		long contactRoleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactAccountRole>
			orderByComparator);

	/**
	 * Returns an ordered range of all the contact account roles where contactRoleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactRoleId the contact role ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching contact account roles
	 */
	public java.util.List<ContactAccountRole> findByContactRoleId(
		long contactRoleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactAccountRole>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first contact account role in the ordered set where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact account role
	 * @throws NoSuchContactAccountRoleException if a matching contact account role could not be found
	 */
	public ContactAccountRole findByContactRoleId_First(
			long contactRoleId,
			com.liferay.portal.kernel.util.OrderByComparator<ContactAccountRole>
				orderByComparator)
		throws NoSuchContactAccountRoleException;

	/**
	 * Returns the first contact account role in the ordered set where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact account role, or <code>null</code> if a matching contact account role could not be found
	 */
	public ContactAccountRole fetchByContactRoleId_First(
		long contactRoleId,
		com.liferay.portal.kernel.util.OrderByComparator<ContactAccountRole>
			orderByComparator);

	/**
	 * Returns the last contact account role in the ordered set where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact account role
	 * @throws NoSuchContactAccountRoleException if a matching contact account role could not be found
	 */
	public ContactAccountRole findByContactRoleId_Last(
			long contactRoleId,
			com.liferay.portal.kernel.util.OrderByComparator<ContactAccountRole>
				orderByComparator)
		throws NoSuchContactAccountRoleException;

	/**
	 * Returns the last contact account role in the ordered set where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact account role, or <code>null</code> if a matching contact account role could not be found
	 */
	public ContactAccountRole fetchByContactRoleId_Last(
		long contactRoleId,
		com.liferay.portal.kernel.util.OrderByComparator<ContactAccountRole>
			orderByComparator);

	/**
	 * Returns the contact account roles before and after the current contact account role in the ordered set where contactRoleId = &#63;.
	 *
	 * @param contactAccountRolePK the primary key of the current contact account role
	 * @param contactRoleId the contact role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact account role
	 * @throws NoSuchContactAccountRoleException if a contact account role with the primary key could not be found
	 */
	public ContactAccountRole[] findByContactRoleId_PrevAndNext(
			ContactAccountRolePK contactAccountRolePK, long contactRoleId,
			com.liferay.portal.kernel.util.OrderByComparator<ContactAccountRole>
				orderByComparator)
		throws NoSuchContactAccountRoleException;

	/**
	 * Removes all the contact account roles where contactRoleId = &#63; from the database.
	 *
	 * @param contactRoleId the contact role ID
	 */
	public void removeByContactRoleId(long contactRoleId);

	/**
	 * Returns the number of contact account roles where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @return the number of matching contact account roles
	 */
	public int countByContactRoleId(long contactRoleId);

	/**
	 * Returns all the contact account roles where contactId = &#63; and accountId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @return the matching contact account roles
	 */
	public java.util.List<ContactAccountRole> findByCI_AI(
		long contactId, long accountId);

	/**
	 * Returns a range of all the contact account roles where contactId = &#63; and accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @return the range of matching contact account roles
	 */
	public java.util.List<ContactAccountRole> findByCI_AI(
		long contactId, long accountId, int start, int end);

	/**
	 * Returns an ordered range of all the contact account roles where contactId = &#63; and accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact account roles
	 */
	public java.util.List<ContactAccountRole> findByCI_AI(
		long contactId, long accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactAccountRole>
			orderByComparator);

	/**
	 * Returns an ordered range of all the contact account roles where contactId = &#63; and accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching contact account roles
	 */
	public java.util.List<ContactAccountRole> findByCI_AI(
		long contactId, long accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactAccountRole>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first contact account role in the ordered set where contactId = &#63; and accountId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact account role
	 * @throws NoSuchContactAccountRoleException if a matching contact account role could not be found
	 */
	public ContactAccountRole findByCI_AI_First(
			long contactId, long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<ContactAccountRole>
				orderByComparator)
		throws NoSuchContactAccountRoleException;

	/**
	 * Returns the first contact account role in the ordered set where contactId = &#63; and accountId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact account role, or <code>null</code> if a matching contact account role could not be found
	 */
	public ContactAccountRole fetchByCI_AI_First(
		long contactId, long accountId,
		com.liferay.portal.kernel.util.OrderByComparator<ContactAccountRole>
			orderByComparator);

	/**
	 * Returns the last contact account role in the ordered set where contactId = &#63; and accountId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact account role
	 * @throws NoSuchContactAccountRoleException if a matching contact account role could not be found
	 */
	public ContactAccountRole findByCI_AI_Last(
			long contactId, long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<ContactAccountRole>
				orderByComparator)
		throws NoSuchContactAccountRoleException;

	/**
	 * Returns the last contact account role in the ordered set where contactId = &#63; and accountId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact account role, or <code>null</code> if a matching contact account role could not be found
	 */
	public ContactAccountRole fetchByCI_AI_Last(
		long contactId, long accountId,
		com.liferay.portal.kernel.util.OrderByComparator<ContactAccountRole>
			orderByComparator);

	/**
	 * Returns the contact account roles before and after the current contact account role in the ordered set where contactId = &#63; and accountId = &#63;.
	 *
	 * @param contactAccountRolePK the primary key of the current contact account role
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact account role
	 * @throws NoSuchContactAccountRoleException if a contact account role with the primary key could not be found
	 */
	public ContactAccountRole[] findByCI_AI_PrevAndNext(
			ContactAccountRolePK contactAccountRolePK, long contactId,
			long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<ContactAccountRole>
				orderByComparator)
		throws NoSuchContactAccountRoleException;

	/**
	 * Removes all the contact account roles where contactId = &#63; and accountId = &#63; from the database.
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 */
	public void removeByCI_AI(long contactId, long accountId);

	/**
	 * Returns the number of contact account roles where contactId = &#63; and accountId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @return the number of matching contact account roles
	 */
	public int countByCI_AI(long contactId, long accountId);

	/**
	 * Caches the contact account role in the entity cache if it is enabled.
	 *
	 * @param contactAccountRole the contact account role
	 */
	public void cacheResult(ContactAccountRole contactAccountRole);

	/**
	 * Caches the contact account roles in the entity cache if it is enabled.
	 *
	 * @param contactAccountRoles the contact account roles
	 */
	public void cacheResult(
		java.util.List<ContactAccountRole> contactAccountRoles);

	/**
	 * Creates a new contact account role with the primary key. Does not add the contact account role to the database.
	 *
	 * @param contactAccountRolePK the primary key for the new contact account role
	 * @return the new contact account role
	 */
	public ContactAccountRole create(ContactAccountRolePK contactAccountRolePK);

	/**
	 * Removes the contact account role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param contactAccountRolePK the primary key of the contact account role
	 * @return the contact account role that was removed
	 * @throws NoSuchContactAccountRoleException if a contact account role with the primary key could not be found
	 */
	public ContactAccountRole remove(ContactAccountRolePK contactAccountRolePK)
		throws NoSuchContactAccountRoleException;

	public ContactAccountRole updateImpl(ContactAccountRole contactAccountRole);

	/**
	 * Returns the contact account role with the primary key or throws a <code>NoSuchContactAccountRoleException</code> if it could not be found.
	 *
	 * @param contactAccountRolePK the primary key of the contact account role
	 * @return the contact account role
	 * @throws NoSuchContactAccountRoleException if a contact account role with the primary key could not be found
	 */
	public ContactAccountRole findByPrimaryKey(
			ContactAccountRolePK contactAccountRolePK)
		throws NoSuchContactAccountRoleException;

	/**
	 * Returns the contact account role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param contactAccountRolePK the primary key of the contact account role
	 * @return the contact account role, or <code>null</code> if a contact account role with the primary key could not be found
	 */
	public ContactAccountRole fetchByPrimaryKey(
		ContactAccountRolePK contactAccountRolePK);

	/**
	 * Returns all the contact account roles.
	 *
	 * @return the contact account roles
	 */
	public java.util.List<ContactAccountRole> findAll();

	/**
	 * Returns a range of all the contact account roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @return the range of contact account roles
	 */
	public java.util.List<ContactAccountRole> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the contact account roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of contact account roles
	 */
	public java.util.List<ContactAccountRole> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactAccountRole>
			orderByComparator);

	/**
	 * Returns an ordered range of all the contact account roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of contact account roles
	 */
	public java.util.List<ContactAccountRole> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactAccountRole>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the contact account roles from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of contact account roles.
	 *
	 * @return the number of contact account roles
	 */
	public int countAll();

	public Set<String> getCompoundPKColumnNames();

}