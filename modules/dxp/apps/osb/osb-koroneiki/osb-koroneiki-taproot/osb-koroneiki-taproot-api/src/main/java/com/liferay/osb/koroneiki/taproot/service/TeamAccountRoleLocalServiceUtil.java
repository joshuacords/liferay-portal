/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service;

import com.liferay.osb.koroneiki.taproot.model.TeamAccountRole;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for TeamAccountRole. This utility wraps
 * <code>com.liferay.osb.koroneiki.taproot.service.impl.TeamAccountRoleLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see TeamAccountRoleLocalService
 * @generated
 */
public class TeamAccountRoleLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.koroneiki.taproot.service.impl.TeamAccountRoleLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static TeamAccountRole addTeamAccountRole(
			long teamId, long accountId, long teamRoleId)
		throws PortalException {

		return getService().addTeamAccountRole(teamId, accountId, teamRoleId);
	}

	/**
	 * Adds the team account role to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TeamAccountRoleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param teamAccountRole the team account role
	 * @return the team account role that was added
	 */
	public static TeamAccountRole addTeamAccountRole(
		TeamAccountRole teamAccountRole) {

		return getService().addTeamAccountRole(teamAccountRole);
	}

	/**
	 * Creates a new team account role with the primary key. Does not add the team account role to the database.
	 *
	 * @param teamAccountRolePK the primary key for the new team account role
	 * @return the new team account role
	 */
	public static TeamAccountRole createTeamAccountRole(
		com.liferay.osb.koroneiki.taproot.service.persistence.TeamAccountRolePK
			teamAccountRolePK) {

		return getService().createTeamAccountRole(teamAccountRolePK);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static TeamAccountRole deleteTeamAccountRole(
			long teamId, long accountId, long teamRoleId)
		throws PortalException {

		return getService().deleteTeamAccountRole(
			teamId, accountId, teamRoleId);
	}

	/**
	 * Deletes the team account role from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TeamAccountRoleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param teamAccountRole the team account role
	 * @return the team account role that was removed
	 */
	public static TeamAccountRole deleteTeamAccountRole(
		TeamAccountRole teamAccountRole) {

		return getService().deleteTeamAccountRole(teamAccountRole);
	}

	/**
	 * Deletes the team account role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TeamAccountRoleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param teamAccountRolePK the primary key of the team account role
	 * @return the team account role that was removed
	 * @throws PortalException if a team account role with the primary key could not be found
	 */
	public static TeamAccountRole deleteTeamAccountRole(
			com.liferay.osb.koroneiki.taproot.service.persistence.
				TeamAccountRolePK teamAccountRolePK)
		throws PortalException {

		return getService().deleteTeamAccountRole(teamAccountRolePK);
	}

	public static void deleteTeamAccountRoles(long teamId, long accountId)
		throws PortalException {

		getService().deleteTeamAccountRoles(teamId, accountId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.taproot.model.impl.TeamAccountRoleModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.taproot.model.impl.TeamAccountRoleModelImpl</code>.
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

	public static TeamAccountRole fetchTeamAccountRole(
		com.liferay.osb.koroneiki.taproot.service.persistence.TeamAccountRolePK
			teamAccountRolePK) {

		return getService().fetchTeamAccountRole(teamAccountRolePK);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
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
	 * Returns the team account role with the primary key.
	 *
	 * @param teamAccountRolePK the primary key of the team account role
	 * @return the team account role
	 * @throws PortalException if a team account role with the primary key could not be found
	 */
	public static TeamAccountRole getTeamAccountRole(
			com.liferay.osb.koroneiki.taproot.service.persistence.
				TeamAccountRolePK teamAccountRolePK)
		throws PortalException {

		return getService().getTeamAccountRole(teamAccountRolePK);
	}

	/**
	 * Returns a range of all the team account roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.taproot.model.impl.TeamAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of team account roles
	 * @param end the upper bound of the range of team account roles (not inclusive)
	 * @return the range of team account roles
	 */
	public static List<TeamAccountRole> getTeamAccountRoles(
		int start, int end) {

		return getService().getTeamAccountRoles(start, end);
	}

	public static List<TeamAccountRole> getTeamAccountRoles(long teamId) {
		return getService().getTeamAccountRoles(teamId);
	}

	public static List<TeamAccountRole> getTeamAccountRolesByAccountId(
		long accountId) {

		return getService().getTeamAccountRolesByAccountId(accountId);
	}

	/**
	 * Returns the number of team account roles.
	 *
	 * @return the number of team account roles
	 */
	public static int getTeamAccountRolesCount() {
		return getService().getTeamAccountRolesCount();
	}

	/**
	 * Updates the team account role in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TeamAccountRoleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param teamAccountRole the team account role
	 * @return the team account role that was updated
	 */
	public static TeamAccountRole updateTeamAccountRole(
		TeamAccountRole teamAccountRole) {

		return getService().updateTeamAccountRole(teamAccountRole);
	}

	public static TeamAccountRoleLocalService getService() {
		return _service;
	}

	public static void setService(TeamAccountRoleLocalService service) {
		_service = service;
	}

	private static volatile TeamAccountRoleLocalService _service;

}