/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.persistence;

import com.liferay.osb.koroneiki.taproot.exception.NoSuchContactTeamRoleException;
import com.liferay.osb.koroneiki.taproot.model.ContactTeamRole;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the contact team role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContactTeamRoleUtil
 * @generated
 */
@ProviderType
public interface ContactTeamRolePersistence
	extends BasePersistence<ContactTeamRole> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ContactTeamRoleUtil} to access the contact team role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the contact team roles where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @return the matching contact team roles
	 */
	public java.util.List<ContactTeamRole> findByContactId(long contactId);

	/**
	 * Returns a range of all the contact team roles where contactId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @return the range of matching contact team roles
	 */
	public java.util.List<ContactTeamRole> findByContactId(
		long contactId, int start, int end);

	/**
	 * Returns an ordered range of all the contact team roles where contactId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact team roles
	 */
	public java.util.List<ContactTeamRole> findByContactId(
		long contactId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactTeamRole>
			orderByComparator);

	/**
	 * Returns an ordered range of all the contact team roles where contactId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching contact team roles
	 */
	public java.util.List<ContactTeamRole> findByContactId(
		long contactId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactTeamRole>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first contact team role in the ordered set where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact team role
	 * @throws NoSuchContactTeamRoleException if a matching contact team role could not be found
	 */
	public ContactTeamRole findByContactId_First(
			long contactId,
			com.liferay.portal.kernel.util.OrderByComparator<ContactTeamRole>
				orderByComparator)
		throws NoSuchContactTeamRoleException;

	/**
	 * Returns the first contact team role in the ordered set where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact team role, or <code>null</code> if a matching contact team role could not be found
	 */
	public ContactTeamRole fetchByContactId_First(
		long contactId,
		com.liferay.portal.kernel.util.OrderByComparator<ContactTeamRole>
			orderByComparator);

	/**
	 * Returns the last contact team role in the ordered set where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact team role
	 * @throws NoSuchContactTeamRoleException if a matching contact team role could not be found
	 */
	public ContactTeamRole findByContactId_Last(
			long contactId,
			com.liferay.portal.kernel.util.OrderByComparator<ContactTeamRole>
				orderByComparator)
		throws NoSuchContactTeamRoleException;

	/**
	 * Returns the last contact team role in the ordered set where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact team role, or <code>null</code> if a matching contact team role could not be found
	 */
	public ContactTeamRole fetchByContactId_Last(
		long contactId,
		com.liferay.portal.kernel.util.OrderByComparator<ContactTeamRole>
			orderByComparator);

	/**
	 * Returns the contact team roles before and after the current contact team role in the ordered set where contactId = &#63;.
	 *
	 * @param contactTeamRolePK the primary key of the current contact team role
	 * @param contactId the contact ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact team role
	 * @throws NoSuchContactTeamRoleException if a contact team role with the primary key could not be found
	 */
	public ContactTeamRole[] findByContactId_PrevAndNext(
			ContactTeamRolePK contactTeamRolePK, long contactId,
			com.liferay.portal.kernel.util.OrderByComparator<ContactTeamRole>
				orderByComparator)
		throws NoSuchContactTeamRoleException;

	/**
	 * Removes all the contact team roles where contactId = &#63; from the database.
	 *
	 * @param contactId the contact ID
	 */
	public void removeByContactId(long contactId);

	/**
	 * Returns the number of contact team roles where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @return the number of matching contact team roles
	 */
	public int countByContactId(long contactId);

	/**
	 * Returns all the contact team roles where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @return the matching contact team roles
	 */
	public java.util.List<ContactTeamRole> findByTeamId(long teamId);

	/**
	 * Returns a range of all the contact team roles where teamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param teamId the team ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @return the range of matching contact team roles
	 */
	public java.util.List<ContactTeamRole> findByTeamId(
		long teamId, int start, int end);

	/**
	 * Returns an ordered range of all the contact team roles where teamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param teamId the team ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact team roles
	 */
	public java.util.List<ContactTeamRole> findByTeamId(
		long teamId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactTeamRole>
			orderByComparator);

	/**
	 * Returns an ordered range of all the contact team roles where teamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param teamId the team ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching contact team roles
	 */
	public java.util.List<ContactTeamRole> findByTeamId(
		long teamId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactTeamRole>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first contact team role in the ordered set where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact team role
	 * @throws NoSuchContactTeamRoleException if a matching contact team role could not be found
	 */
	public ContactTeamRole findByTeamId_First(
			long teamId,
			com.liferay.portal.kernel.util.OrderByComparator<ContactTeamRole>
				orderByComparator)
		throws NoSuchContactTeamRoleException;

	/**
	 * Returns the first contact team role in the ordered set where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact team role, or <code>null</code> if a matching contact team role could not be found
	 */
	public ContactTeamRole fetchByTeamId_First(
		long teamId,
		com.liferay.portal.kernel.util.OrderByComparator<ContactTeamRole>
			orderByComparator);

	/**
	 * Returns the last contact team role in the ordered set where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact team role
	 * @throws NoSuchContactTeamRoleException if a matching contact team role could not be found
	 */
	public ContactTeamRole findByTeamId_Last(
			long teamId,
			com.liferay.portal.kernel.util.OrderByComparator<ContactTeamRole>
				orderByComparator)
		throws NoSuchContactTeamRoleException;

	/**
	 * Returns the last contact team role in the ordered set where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact team role, or <code>null</code> if a matching contact team role could not be found
	 */
	public ContactTeamRole fetchByTeamId_Last(
		long teamId,
		com.liferay.portal.kernel.util.OrderByComparator<ContactTeamRole>
			orderByComparator);

	/**
	 * Returns the contact team roles before and after the current contact team role in the ordered set where teamId = &#63;.
	 *
	 * @param contactTeamRolePK the primary key of the current contact team role
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact team role
	 * @throws NoSuchContactTeamRoleException if a contact team role with the primary key could not be found
	 */
	public ContactTeamRole[] findByTeamId_PrevAndNext(
			ContactTeamRolePK contactTeamRolePK, long teamId,
			com.liferay.portal.kernel.util.OrderByComparator<ContactTeamRole>
				orderByComparator)
		throws NoSuchContactTeamRoleException;

	/**
	 * Removes all the contact team roles where teamId = &#63; from the database.
	 *
	 * @param teamId the team ID
	 */
	public void removeByTeamId(long teamId);

	/**
	 * Returns the number of contact team roles where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @return the number of matching contact team roles
	 */
	public int countByTeamId(long teamId);

	/**
	 * Returns all the contact team roles where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @return the matching contact team roles
	 */
	public java.util.List<ContactTeamRole> findByContactRoleId(
		long contactRoleId);

	/**
	 * Returns a range of all the contact team roles where contactRoleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactRoleId the contact role ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @return the range of matching contact team roles
	 */
	public java.util.List<ContactTeamRole> findByContactRoleId(
		long contactRoleId, int start, int end);

	/**
	 * Returns an ordered range of all the contact team roles where contactRoleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactRoleId the contact role ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact team roles
	 */
	public java.util.List<ContactTeamRole> findByContactRoleId(
		long contactRoleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactTeamRole>
			orderByComparator);

	/**
	 * Returns an ordered range of all the contact team roles where contactRoleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactRoleId the contact role ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching contact team roles
	 */
	public java.util.List<ContactTeamRole> findByContactRoleId(
		long contactRoleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactTeamRole>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first contact team role in the ordered set where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact team role
	 * @throws NoSuchContactTeamRoleException if a matching contact team role could not be found
	 */
	public ContactTeamRole findByContactRoleId_First(
			long contactRoleId,
			com.liferay.portal.kernel.util.OrderByComparator<ContactTeamRole>
				orderByComparator)
		throws NoSuchContactTeamRoleException;

	/**
	 * Returns the first contact team role in the ordered set where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact team role, or <code>null</code> if a matching contact team role could not be found
	 */
	public ContactTeamRole fetchByContactRoleId_First(
		long contactRoleId,
		com.liferay.portal.kernel.util.OrderByComparator<ContactTeamRole>
			orderByComparator);

	/**
	 * Returns the last contact team role in the ordered set where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact team role
	 * @throws NoSuchContactTeamRoleException if a matching contact team role could not be found
	 */
	public ContactTeamRole findByContactRoleId_Last(
			long contactRoleId,
			com.liferay.portal.kernel.util.OrderByComparator<ContactTeamRole>
				orderByComparator)
		throws NoSuchContactTeamRoleException;

	/**
	 * Returns the last contact team role in the ordered set where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact team role, or <code>null</code> if a matching contact team role could not be found
	 */
	public ContactTeamRole fetchByContactRoleId_Last(
		long contactRoleId,
		com.liferay.portal.kernel.util.OrderByComparator<ContactTeamRole>
			orderByComparator);

	/**
	 * Returns the contact team roles before and after the current contact team role in the ordered set where contactRoleId = &#63;.
	 *
	 * @param contactTeamRolePK the primary key of the current contact team role
	 * @param contactRoleId the contact role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact team role
	 * @throws NoSuchContactTeamRoleException if a contact team role with the primary key could not be found
	 */
	public ContactTeamRole[] findByContactRoleId_PrevAndNext(
			ContactTeamRolePK contactTeamRolePK, long contactRoleId,
			com.liferay.portal.kernel.util.OrderByComparator<ContactTeamRole>
				orderByComparator)
		throws NoSuchContactTeamRoleException;

	/**
	 * Removes all the contact team roles where contactRoleId = &#63; from the database.
	 *
	 * @param contactRoleId the contact role ID
	 */
	public void removeByContactRoleId(long contactRoleId);

	/**
	 * Returns the number of contact team roles where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @return the number of matching contact team roles
	 */
	public int countByContactRoleId(long contactRoleId);

	/**
	 * Returns all the contact team roles where contactId = &#63; and teamId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param teamId the team ID
	 * @return the matching contact team roles
	 */
	public java.util.List<ContactTeamRole> findByCI_TI(
		long contactId, long teamId);

	/**
	 * Returns a range of all the contact team roles where contactId = &#63; and teamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param teamId the team ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @return the range of matching contact team roles
	 */
	public java.util.List<ContactTeamRole> findByCI_TI(
		long contactId, long teamId, int start, int end);

	/**
	 * Returns an ordered range of all the contact team roles where contactId = &#63; and teamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param teamId the team ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact team roles
	 */
	public java.util.List<ContactTeamRole> findByCI_TI(
		long contactId, long teamId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactTeamRole>
			orderByComparator);

	/**
	 * Returns an ordered range of all the contact team roles where contactId = &#63; and teamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param teamId the team ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching contact team roles
	 */
	public java.util.List<ContactTeamRole> findByCI_TI(
		long contactId, long teamId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactTeamRole>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first contact team role in the ordered set where contactId = &#63; and teamId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact team role
	 * @throws NoSuchContactTeamRoleException if a matching contact team role could not be found
	 */
	public ContactTeamRole findByCI_TI_First(
			long contactId, long teamId,
			com.liferay.portal.kernel.util.OrderByComparator<ContactTeamRole>
				orderByComparator)
		throws NoSuchContactTeamRoleException;

	/**
	 * Returns the first contact team role in the ordered set where contactId = &#63; and teamId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact team role, or <code>null</code> if a matching contact team role could not be found
	 */
	public ContactTeamRole fetchByCI_TI_First(
		long contactId, long teamId,
		com.liferay.portal.kernel.util.OrderByComparator<ContactTeamRole>
			orderByComparator);

	/**
	 * Returns the last contact team role in the ordered set where contactId = &#63; and teamId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact team role
	 * @throws NoSuchContactTeamRoleException if a matching contact team role could not be found
	 */
	public ContactTeamRole findByCI_TI_Last(
			long contactId, long teamId,
			com.liferay.portal.kernel.util.OrderByComparator<ContactTeamRole>
				orderByComparator)
		throws NoSuchContactTeamRoleException;

	/**
	 * Returns the last contact team role in the ordered set where contactId = &#63; and teamId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact team role, or <code>null</code> if a matching contact team role could not be found
	 */
	public ContactTeamRole fetchByCI_TI_Last(
		long contactId, long teamId,
		com.liferay.portal.kernel.util.OrderByComparator<ContactTeamRole>
			orderByComparator);

	/**
	 * Returns the contact team roles before and after the current contact team role in the ordered set where contactId = &#63; and teamId = &#63;.
	 *
	 * @param contactTeamRolePK the primary key of the current contact team role
	 * @param contactId the contact ID
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact team role
	 * @throws NoSuchContactTeamRoleException if a contact team role with the primary key could not be found
	 */
	public ContactTeamRole[] findByCI_TI_PrevAndNext(
			ContactTeamRolePK contactTeamRolePK, long contactId, long teamId,
			com.liferay.portal.kernel.util.OrderByComparator<ContactTeamRole>
				orderByComparator)
		throws NoSuchContactTeamRoleException;

	/**
	 * Removes all the contact team roles where contactId = &#63; and teamId = &#63; from the database.
	 *
	 * @param contactId the contact ID
	 * @param teamId the team ID
	 */
	public void removeByCI_TI(long contactId, long teamId);

	/**
	 * Returns the number of contact team roles where contactId = &#63; and teamId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param teamId the team ID
	 * @return the number of matching contact team roles
	 */
	public int countByCI_TI(long contactId, long teamId);

	/**
	 * Caches the contact team role in the entity cache if it is enabled.
	 *
	 * @param contactTeamRole the contact team role
	 */
	public void cacheResult(ContactTeamRole contactTeamRole);

	/**
	 * Caches the contact team roles in the entity cache if it is enabled.
	 *
	 * @param contactTeamRoles the contact team roles
	 */
	public void cacheResult(java.util.List<ContactTeamRole> contactTeamRoles);

	/**
	 * Creates a new contact team role with the primary key. Does not add the contact team role to the database.
	 *
	 * @param contactTeamRolePK the primary key for the new contact team role
	 * @return the new contact team role
	 */
	public ContactTeamRole create(ContactTeamRolePK contactTeamRolePK);

	/**
	 * Removes the contact team role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param contactTeamRolePK the primary key of the contact team role
	 * @return the contact team role that was removed
	 * @throws NoSuchContactTeamRoleException if a contact team role with the primary key could not be found
	 */
	public ContactTeamRole remove(ContactTeamRolePK contactTeamRolePK)
		throws NoSuchContactTeamRoleException;

	public ContactTeamRole updateImpl(ContactTeamRole contactTeamRole);

	/**
	 * Returns the contact team role with the primary key or throws a <code>NoSuchContactTeamRoleException</code> if it could not be found.
	 *
	 * @param contactTeamRolePK the primary key of the contact team role
	 * @return the contact team role
	 * @throws NoSuchContactTeamRoleException if a contact team role with the primary key could not be found
	 */
	public ContactTeamRole findByPrimaryKey(ContactTeamRolePK contactTeamRolePK)
		throws NoSuchContactTeamRoleException;

	/**
	 * Returns the contact team role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param contactTeamRolePK the primary key of the contact team role
	 * @return the contact team role, or <code>null</code> if a contact team role with the primary key could not be found
	 */
	public ContactTeamRole fetchByPrimaryKey(
		ContactTeamRolePK contactTeamRolePK);

	/**
	 * Returns all the contact team roles.
	 *
	 * @return the contact team roles
	 */
	public java.util.List<ContactTeamRole> findAll();

	/**
	 * Returns a range of all the contact team roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @return the range of contact team roles
	 */
	public java.util.List<ContactTeamRole> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the contact team roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of contact team roles
	 */
	public java.util.List<ContactTeamRole> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactTeamRole>
			orderByComparator);

	/**
	 * Returns an ordered range of all the contact team roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of contact team roles
	 */
	public java.util.List<ContactTeamRole> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactTeamRole>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the contact team roles from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of contact team roles.
	 *
	 * @return the number of contact team roles
	 */
	public int countAll();

	public Set<String> getCompoundPKColumnNames();

}