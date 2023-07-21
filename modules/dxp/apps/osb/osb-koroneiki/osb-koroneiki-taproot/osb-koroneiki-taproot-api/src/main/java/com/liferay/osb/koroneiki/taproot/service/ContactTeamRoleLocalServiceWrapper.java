/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContactTeamRoleLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContactTeamRoleLocalService
 * @generated
 */
public class ContactTeamRoleLocalServiceWrapper
	implements ContactTeamRoleLocalService,
			   ServiceWrapper<ContactTeamRoleLocalService> {

	public ContactTeamRoleLocalServiceWrapper(
		ContactTeamRoleLocalService contactTeamRoleLocalService) {

		_contactTeamRoleLocalService = contactTeamRoleLocalService;
	}

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
	@Override
	public com.liferay.osb.koroneiki.taproot.model.ContactTeamRole
		addContactTeamRole(
			com.liferay.osb.koroneiki.taproot.model.ContactTeamRole
				contactTeamRole) {

		return _contactTeamRoleLocalService.addContactTeamRole(contactTeamRole);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.ContactTeamRole
			addContactTeamRole(long contactId, long teamId, long contactRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactTeamRoleLocalService.addContactTeamRole(
			contactId, teamId, contactRoleId);
	}

	/**
	 * Creates a new contact team role with the primary key. Does not add the contact team role to the database.
	 *
	 * @param contactTeamRolePK the primary key for the new contact team role
	 * @return the new contact team role
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.ContactTeamRole
		createContactTeamRole(
			com.liferay.osb.koroneiki.taproot.service.persistence.
				ContactTeamRolePK contactTeamRolePK) {

		return _contactTeamRoleLocalService.createContactTeamRole(
			contactTeamRolePK);
	}

	@Override
	public void deleteAccountTeamContact(long accountId, long contactId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_contactTeamRoleLocalService.deleteAccountTeamContact(
			accountId, contactId);
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
	@Override
	public com.liferay.osb.koroneiki.taproot.model.ContactTeamRole
		deleteContactTeamRole(
			com.liferay.osb.koroneiki.taproot.model.ContactTeamRole
				contactTeamRole) {

		return _contactTeamRoleLocalService.deleteContactTeamRole(
			contactTeamRole);
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
	@Override
	public com.liferay.osb.koroneiki.taproot.model.ContactTeamRole
			deleteContactTeamRole(
				com.liferay.osb.koroneiki.taproot.service.persistence.
					ContactTeamRolePK contactTeamRolePK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactTeamRoleLocalService.deleteContactTeamRole(
			contactTeamRolePK);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.ContactTeamRole
			deleteContactTeamRole(
				long contactId, long teamId, long contactRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactTeamRoleLocalService.deleteContactTeamRole(
			contactId, teamId, contactRoleId);
	}

	@Override
	public void deleteContactTeamRoles(long contactId, long teamId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_contactTeamRoleLocalService.deleteContactTeamRoles(contactId, teamId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactTeamRoleLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _contactTeamRoleLocalService.dynamicQuery();
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

		return _contactTeamRoleLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _contactTeamRoleLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _contactTeamRoleLocalService.dynamicQuery(
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

		return _contactTeamRoleLocalService.dynamicQueryCount(dynamicQuery);
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

		return _contactTeamRoleLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.ContactTeamRole
		fetchContactTeamRole(
			com.liferay.osb.koroneiki.taproot.service.persistence.
				ContactTeamRolePK contactTeamRolePK) {

		return _contactTeamRoleLocalService.fetchContactTeamRole(
			contactTeamRolePK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _contactTeamRoleLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the contact team role with the primary key.
	 *
	 * @param contactTeamRolePK the primary key of the contact team role
	 * @return the contact team role
	 * @throws PortalException if a contact team role with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.ContactTeamRole
			getContactTeamRole(
				com.liferay.osb.koroneiki.taproot.service.persistence.
					ContactTeamRolePK contactTeamRolePK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactTeamRoleLocalService.getContactTeamRole(
			contactTeamRolePK);
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
	@Override
	public java.util.List
		<com.liferay.osb.koroneiki.taproot.model.ContactTeamRole>
			getContactTeamRoles(int start, int end) {

		return _contactTeamRoleLocalService.getContactTeamRoles(start, end);
	}

	@Override
	public java.util.List
		<com.liferay.osb.koroneiki.taproot.model.ContactTeamRole>
			getContactTeamRoles(long contactId) {

		return _contactTeamRoleLocalService.getContactTeamRoles(contactId);
	}

	@Override
	public java.util.List
		<com.liferay.osb.koroneiki.taproot.model.ContactTeamRole>
			getContactTeamRolesByTeamId(long teamId) {

		return _contactTeamRoleLocalService.getContactTeamRolesByTeamId(teamId);
	}

	/**
	 * Returns the number of contact team roles.
	 *
	 * @return the number of contact team roles
	 */
	@Override
	public int getContactTeamRolesCount() {
		return _contactTeamRoleLocalService.getContactTeamRolesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _contactTeamRoleLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _contactTeamRoleLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactTeamRoleLocalService.getPersistedModel(primaryKeyObj);
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
	@Override
	public com.liferay.osb.koroneiki.taproot.model.ContactTeamRole
		updateContactTeamRole(
			com.liferay.osb.koroneiki.taproot.model.ContactTeamRole
				contactTeamRole) {

		return _contactTeamRoleLocalService.updateContactTeamRole(
			contactTeamRole);
	}

	@Override
	public ContactTeamRoleLocalService getWrappedService() {
		return _contactTeamRoleLocalService;
	}

	@Override
	public void setWrappedService(
		ContactTeamRoleLocalService contactTeamRoleLocalService) {

		_contactTeamRoleLocalService = contactTeamRoleLocalService;
	}

	private ContactTeamRoleLocalService _contactTeamRoleLocalService;

}