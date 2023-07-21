/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TeamAccountRoleLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see TeamAccountRoleLocalService
 * @generated
 */
public class TeamAccountRoleLocalServiceWrapper
	implements ServiceWrapper<TeamAccountRoleLocalService>,
			   TeamAccountRoleLocalService {

	public TeamAccountRoleLocalServiceWrapper(
		TeamAccountRoleLocalService teamAccountRoleLocalService) {

		_teamAccountRoleLocalService = teamAccountRoleLocalService;
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.TeamAccountRole
			addTeamAccountRole(long teamId, long accountId, long teamRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamAccountRoleLocalService.addTeamAccountRole(
			teamId, accountId, teamRoleId);
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
	@Override
	public com.liferay.osb.koroneiki.taproot.model.TeamAccountRole
		addTeamAccountRole(
			com.liferay.osb.koroneiki.taproot.model.TeamAccountRole
				teamAccountRole) {

		return _teamAccountRoleLocalService.addTeamAccountRole(teamAccountRole);
	}

	/**
	 * Creates a new team account role with the primary key. Does not add the team account role to the database.
	 *
	 * @param teamAccountRolePK the primary key for the new team account role
	 * @return the new team account role
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.TeamAccountRole
		createTeamAccountRole(
			com.liferay.osb.koroneiki.taproot.service.persistence.
				TeamAccountRolePK teamAccountRolePK) {

		return _teamAccountRoleLocalService.createTeamAccountRole(
			teamAccountRolePK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamAccountRoleLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.TeamAccountRole
			deleteTeamAccountRole(long teamId, long accountId, long teamRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamAccountRoleLocalService.deleteTeamAccountRole(
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
	@Override
	public com.liferay.osb.koroneiki.taproot.model.TeamAccountRole
		deleteTeamAccountRole(
			com.liferay.osb.koroneiki.taproot.model.TeamAccountRole
				teamAccountRole) {

		return _teamAccountRoleLocalService.deleteTeamAccountRole(
			teamAccountRole);
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
	@Override
	public com.liferay.osb.koroneiki.taproot.model.TeamAccountRole
			deleteTeamAccountRole(
				com.liferay.osb.koroneiki.taproot.service.persistence.
					TeamAccountRolePK teamAccountRolePK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamAccountRoleLocalService.deleteTeamAccountRole(
			teamAccountRolePK);
	}

	@Override
	public void deleteTeamAccountRoles(long teamId, long accountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_teamAccountRoleLocalService.deleteTeamAccountRoles(teamId, accountId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _teamAccountRoleLocalService.dynamicQuery();
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

		return _teamAccountRoleLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _teamAccountRoleLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _teamAccountRoleLocalService.dynamicQuery(
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

		return _teamAccountRoleLocalService.dynamicQueryCount(dynamicQuery);
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

		return _teamAccountRoleLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.TeamAccountRole
		fetchTeamAccountRole(
			com.liferay.osb.koroneiki.taproot.service.persistence.
				TeamAccountRolePK teamAccountRolePK) {

		return _teamAccountRoleLocalService.fetchTeamAccountRole(
			teamAccountRolePK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _teamAccountRoleLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _teamAccountRoleLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _teamAccountRoleLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamAccountRoleLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the team account role with the primary key.
	 *
	 * @param teamAccountRolePK the primary key of the team account role
	 * @return the team account role
	 * @throws PortalException if a team account role with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.TeamAccountRole
			getTeamAccountRole(
				com.liferay.osb.koroneiki.taproot.service.persistence.
					TeamAccountRolePK teamAccountRolePK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamAccountRoleLocalService.getTeamAccountRole(
			teamAccountRolePK);
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
	@Override
	public java.util.List
		<com.liferay.osb.koroneiki.taproot.model.TeamAccountRole>
			getTeamAccountRoles(int start, int end) {

		return _teamAccountRoleLocalService.getTeamAccountRoles(start, end);
	}

	@Override
	public java.util.List
		<com.liferay.osb.koroneiki.taproot.model.TeamAccountRole>
			getTeamAccountRoles(long teamId) {

		return _teamAccountRoleLocalService.getTeamAccountRoles(teamId);
	}

	@Override
	public java.util.List
		<com.liferay.osb.koroneiki.taproot.model.TeamAccountRole>
			getTeamAccountRolesByAccountId(long accountId) {

		return _teamAccountRoleLocalService.getTeamAccountRolesByAccountId(
			accountId);
	}

	/**
	 * Returns the number of team account roles.
	 *
	 * @return the number of team account roles
	 */
	@Override
	public int getTeamAccountRolesCount() {
		return _teamAccountRoleLocalService.getTeamAccountRolesCount();
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
	@Override
	public com.liferay.osb.koroneiki.taproot.model.TeamAccountRole
		updateTeamAccountRole(
			com.liferay.osb.koroneiki.taproot.model.TeamAccountRole
				teamAccountRole) {

		return _teamAccountRoleLocalService.updateTeamAccountRole(
			teamAccountRole);
	}

	@Override
	public TeamAccountRoleLocalService getWrappedService() {
		return _teamAccountRoleLocalService;
	}

	@Override
	public void setWrappedService(
		TeamAccountRoleLocalService teamAccountRoleLocalService) {

		_teamAccountRoleLocalService = teamAccountRoleLocalService;
	}

	private TeamAccountRoleLocalService _teamAccountRoleLocalService;

}