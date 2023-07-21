/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service;

import com.liferay.osb.koroneiki.taproot.model.ContactTeamRole;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for ContactTeamRole. This utility wraps
 * <code>com.liferay.osb.koroneiki.taproot.service.impl.ContactTeamRoleLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ContactTeamRoleLocalService
 * @generated
 */
public class ContactTeamRoleLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.koroneiki.taproot.service.impl.ContactTeamRoleLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the contact team role to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ContactTeamRoleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param contactTeamRole the contact team role
	 * @return the contact team role that was added
	 */
	public static ContactTeamRole addContactTeamRole(
		ContactTeamRole contactTeamRole) {

		return getService().addContactTeamRole(contactTeamRole);
	}

	public static ContactTeamRole addContactTeamRole(
			long contactId, long teamId, long contactRoleId)
		throws PortalException {

		return getService().addContactTeamRole(
			contactId, teamId, contactRoleId);
	}

	/**
	 * Creates a new contact team role with the primary key. Does not add the contact team role to the database.
	 *
	 * @param contactTeamRolePK the primary key for the new contact team role
	 * @return the new contact team role
	 */
	public static ContactTeamRole createContactTeamRole(
		com.liferay.osb.koroneiki.taproot.service.persistence.ContactTeamRolePK
			contactTeamRolePK) {

		return getService().createContactTeamRole(contactTeamRolePK);
	}

	public static void deleteAccountTeamContact(long accountId, long contactId)
		throws PortalException {

		getService().deleteAccountTeamContact(accountId, contactId);
	}

	/**
	 * Deletes the contact team role from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ContactTeamRoleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param contactTeamRole the contact team role
	 * @return the contact team role that was removed
	 */
	public static ContactTeamRole deleteContactTeamRole(
		ContactTeamRole contactTeamRole) {

		return getService().deleteContactTeamRole(contactTeamRole);
	}

	/**
	 * Deletes the contact team role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ContactTeamRoleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param contactTeamRolePK the primary key of the contact team role
	 * @return the contact team role that was removed
	 * @throws PortalException if a contact team role with the primary key could not be found
	 */
	public static ContactTeamRole deleteContactTeamRole(
			com.liferay.osb.koroneiki.taproot.service.persistence.
				ContactTeamRolePK contactTeamRolePK)
		throws PortalException {

		return getService().deleteContactTeamRole(contactTeamRolePK);
	}

	public static ContactTeamRole deleteContactTeamRole(
			long contactId, long teamId, long contactRoleId)
		throws PortalException {

		return getService().deleteContactTeamRole(
			contactId, teamId, contactRoleId);
	}

	public static void deleteContactTeamRoles(long contactId, long teamId)
		throws PortalException {

		getService().deleteContactTeamRoles(contactId, teamId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.taproot.model.impl.ContactTeamRoleModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.taproot.model.impl.ContactTeamRoleModelImpl</code>.
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

	public static ContactTeamRole fetchContactTeamRole(
		com.liferay.osb.koroneiki.taproot.service.persistence.ContactTeamRolePK
			contactTeamRolePK) {

		return getService().fetchContactTeamRole(contactTeamRolePK);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the contact team role with the primary key.
	 *
	 * @param contactTeamRolePK the primary key of the contact team role
	 * @return the contact team role
	 * @throws PortalException if a contact team role with the primary key could not be found
	 */
	public static ContactTeamRole getContactTeamRole(
			com.liferay.osb.koroneiki.taproot.service.persistence.
				ContactTeamRolePK contactTeamRolePK)
		throws PortalException {

		return getService().getContactTeamRole(contactTeamRolePK);
	}

	/**
	 * Returns a range of all the contact team roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.taproot.model.impl.ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @return the range of contact team roles
	 */
	public static List<ContactTeamRole> getContactTeamRoles(
		int start, int end) {

		return getService().getContactTeamRoles(start, end);
	}

	public static List<ContactTeamRole> getContactTeamRoles(long contactId) {
		return getService().getContactTeamRoles(contactId);
	}

	public static List<ContactTeamRole> getContactTeamRolesByTeamId(
		long teamId) {

		return getService().getContactTeamRolesByTeamId(teamId);
	}

	/**
	 * Returns the number of contact team roles.
	 *
	 * @return the number of contact team roles
	 */
	public static int getContactTeamRolesCount() {
		return getService().getContactTeamRolesCount();
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
	 * Updates the contact team role in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ContactTeamRoleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param contactTeamRole the contact team role
	 * @return the contact team role that was updated
	 */
	public static ContactTeamRole updateContactTeamRole(
		ContactTeamRole contactTeamRole) {

		return getService().updateContactTeamRole(contactTeamRole);
	}

	public static ContactTeamRoleLocalService getService() {
		return _service;
	}

	public static void setService(ContactTeamRoleLocalService service) {
		_service = service;
	}

	private static volatile ContactTeamRoleLocalService _service;

}